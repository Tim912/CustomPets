package com.example.custompets.gui;

import com.example.custompets.StatsUtil;
import com.example.custompets.stats.PlayerStats;
import com.example.custompets.stats.StatType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class StatsGUI {
    public static Inventory create(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, "Player Stats");
        PlayerStats stats = StatsUtil.getStats(player);
        if (stats == null) {
            return inv;
        }
        ItemStack book = new ItemStack(Material.BOOK);
        ItemMeta meta = book.getItemMeta();
        meta.setDisplayName("§aStats");
        java.util.List<String> lore = new java.util.ArrayList<>();
        for (StatType type : StatType.values()) {
            double value = stats.get(type);
            lore.add(type.name() + ": " + value);
        }
        meta.setLore(lore);
        book.setItemMeta(meta);
        inv.setItem(4, book);
        return inv;
    }
}
