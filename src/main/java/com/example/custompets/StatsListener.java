package com.example.custompets;

import com.example.custompets.stats.PlayerStats;
import com.example.custompets.stats.StatType;
import com.example.custompets.stats.StatsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
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
    }
}
