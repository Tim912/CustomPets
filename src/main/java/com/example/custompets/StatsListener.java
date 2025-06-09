package com.example.custompets;

import com.example.custompets.stats.PlayerStats;
import com.example.custompets.stats.StatType;
import com.example.custompets.stats.StatsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerJoinEvent;

/** Utility methods for getting and setting player stats. */
import static com.example.custompets.StatsUtil.getStats;
import static com.example.custompets.StatsUtil.setStats;

public class StatsListener implements Listener {
    private final StatsManager statsManager;

    public StatsListener(StatsManager statsManager) {
        this.statsManager = statsManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerStats playerStats = new PlayerStats();
        for (StatType type : StatType.values()) {
            double value = statsManager.getFormulaValue(type);
            playerStats.set(type, value);
            if (statsManager.getStat(type) != null) {
                statsManager.getStat(type).apply(player, value);
            }
        }
        setStats(player, playerStats, statsManager.getPlugin());
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        PlayerStats stats = getStats(player);
        if (stats == null) return;
        double defense = stats.get(StatType.DEFENSE);
        double reduction = defense / 100.0;
        event.setDamage(event.getDamage() * (1 - reduction));
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) {
            return;
        }
        PlayerStats stats = getStats(player);
        if (stats == null) return;
        double strength = stats.get(StatType.STRENGTH);
        double critChance = stats.get(StatType.CRIT_CHANCE);
        double critDamage = stats.get(StatType.CRIT_DAMAGE);
        double damage = event.getDamage() + strength;
        if (Math.random() < critChance / 100.0) {
            damage += critDamage;
        }
        event.setDamage(damage);
        double ferocity = stats.get(StatType.FEROCITY);
        if (Math.random() < ferocity / 100.0) {
            event.getEntity().damage(damage, player);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        PlayerStats stats = getStats(player);
        if (stats == null) return;
        var type = event.getBlock().getType();

        boolean ore = type.name().endsWith("_ORE") || type.name().equals("NETHER_QUARTZ_ORE") || type.name().equals("ANCIENT_DEBRIS");
        boolean crop = type == org.bukkit.Material.WHEAT || type == org.bukkit.Material.CARROTS || type == org.bukkit.Material.POTATOES ||
                type == org.bukkit.Material.BEETROOTS || type == org.bukkit.Material.NETHER_WART || type == org.bukkit.Material.COCOA ||
                type == org.bukkit.Material.PUMPKIN || type == org.bukkit.Material.MELON || type == org.bukkit.Material.SUGAR_CANE;
        boolean log = type.name().endsWith("_LOG") || type.name().endsWith("_STEM");

        double fortune = 0.0;
        if (ore) fortune = stats.get(StatType.MINING_FORTUNE);
        else if (crop) fortune = stats.get(StatType.FARMING_FORTUNE);
        else if (log) fortune = stats.get(StatType.FORAGING_FORTUNE);

        double pristine = ore ? stats.get(StatType.PRISTINE) : 0.0;

        if (fortune <= 0 && pristine <= 0) return;

        var drops = event.getBlock().getDrops(player.getInventory().getItemInMainHand());
        if (drops.isEmpty()) return;

        event.setDropItems(false);
        event.getBlock().setType(org.bukkit.Material.AIR);
        var loc = event.getBlock().getLocation();

        int extra = (int) (fortune / 100);
        if (Math.random() < (fortune % 100) / 100.0) extra++;
        for (var drop : drops) {
            drop.setAmount(drop.getAmount() + extra);
            event.getBlock().getWorld().dropItemNaturally(loc, drop);
            if (ore && Math.random() < pristine / 100.0) {
                event.getBlock().getWorld().dropItemNaturally(loc, drop.clone());
            }
        }
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        PlayerStats stats = getStats(player);
        if (stats == null) return;
        if (event.getState() == PlayerFishEvent.State.FISHING) {
            int level = (int) stats.get(StatType.FISHING_SPEED);
            if (level > 0) {
                player.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.LUCK, 600, level - 1, true, false));
            }
        }
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            double chance = stats.get(StatType.SEA_CREATURE_CHANCE);
            if (Math.random() < chance / 100.0) {
                event.getHook().getWorld().spawnEntity(event.getHook().getLocation(), org.bukkit.entity.EntityType.GUARDIAN);
            }
        }
    }

    @EventHandler
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        PlayerStats stats = getStats(player);
        if (stats == null) return;
        double vitality = stats.get(StatType.VITALITY);
        event.setAmount(event.getAmount() * (1 + vitality / 100.0));
    }

    @EventHandler
    public void onPotionSplash(PotionSplashEvent event) {
        if (!(event.getPotion().getShooter() instanceof Player player)) return;
        PlayerStats stats = getStats(player);
        if (stats == null) return;
        double mending = stats.get(StatType.MENDING);
        if (mending <= 0) return;
        for (var entity : event.getAffectedEntities()) {
            double intensity = event.getIntensity(entity);
            event.setIntensity(entity, intensity * (1 + mending / 100.0));
        }
    }
}
