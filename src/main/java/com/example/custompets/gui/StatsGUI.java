package com.example.custompets.gui;


import com.example.custompets.CustomPetsPlugin;

import com.example.custompets.StatsUtil;
import com.example.custompets.stats.PlayerStats;
import com.example.custompets.stats.StatType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates the stats GUI which groups stats into categories.
 */
public class StatsGUI {

    /**
     * Represents a group of related stats shown in the GUI.
     */
    public enum StatsCategory {
        COMBAT("Combat Stats", Material.DIAMOND_SWORD,
                StatType.HEALTH, StatType.DEFENSE, StatType.STRENGTH,
                StatType.CRIT_CHANCE, StatType.CRIT_DAMAGE,
                StatType.ATTACK_SPEED, StatType.FEROCITY),
        GENERAL("General Stats", Material.BOOK,
                StatType.SPEED, StatType.INTELLIGENCE,
                StatType.VITALITY, StatType.MENDING),
        FISHING("Fishing Stats", Material.FISHING_ROD,
                StatType.SEA_CREATURE_CHANCE, StatType.FISHING_SPEED),
        MINING("Mining Stats", Material.IRON_PICKAXE,
                StatType.MINING_SPEED, StatType.MINING_FORTUNE, StatType.PRISTINE),
        FARMING("Farming Stats", Material.WHEAT,
                StatType.FARMING_FORTUNE),
        FORAGING("Foraging Stats", Material.OAK_LOG,
                StatType.FORAGING_FORTUNE);

        private final String display;
        private final Material icon;
        private final StatType[] stats;

        StatsCategory(String display, Material icon, StatType... stats) {
            this.display = display;
            this.icon = icon;
            this.stats = stats;
        }

        public String getDisplay() {
            return display;
        }

        public Material getIcon() {
            return icon;
        }

        public StatType[] getStats() {
            return stats;
        }
    }

    private static int getInventorySize() {
        int slots = ((StatsCategory.values().length - 1) / 9 + 1) * 9;
        return Math.max(9, slots);
    }

    /**
     * Opens the GUI and keeps it updated while it is open.
     */
    public static void open(CustomPetsPlugin plugin, Player player) {
        Inventory inv = Bukkit.createInventory(null, getInventorySize(), "Player Stats");
        populate(player, inv);
        player.openInventory(inv);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.getOpenInventory().getTopInventory().equals(inv)) {
                    cancel();
                    return;
                }
                populate(player, inv);
            }
        }.runTaskTimer(plugin, 20L, 20L);
    }

    private static void populate(Player player, Inventory inv) {
        PlayerStats stats = StatsUtil.getStats(player);
        if (stats == null) {
            return;
        }
        int slot = 0;
        for (StatsCategory category : StatsCategory.values()) {
            ItemStack item = new ItemStack(category.getIcon());
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§a" + category.getDisplay());
            List<String> lore = new ArrayList<>();
            for (StatType type : category.getStats()) {
                double value = stats.get(type);
                lore.add(format(type) + ": " + value);
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(slot++, item);
        }
    }

    private static String format(StatType type) {
        return type.name().toLowerCase().replace('_', ' ');

    }
}
