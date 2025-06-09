package com.example.custompets;

import com.example.custompets.stats.PlayerStats;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public final class StatsUtil {
    private StatsUtil() {}

    public static PlayerStats getStats(Player player) {
        for (MetadataValue value : player.getMetadata("playerstats")) {
            Object obj = value.value();
            if (obj instanceof PlayerStats) {
                return (PlayerStats) obj;
            }
        }
        return null;
    }

    public static void setStats(Player player, PlayerStats stats, Plugin plugin) {
        player.setMetadata("playerstats", new FixedMetadataValue(plugin, stats));
    }
}
