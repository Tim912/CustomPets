package com.example.custompets.stats;

import org.bukkit.entity.Player;

public class FishingSpeedStat implements Stat {
    @Override
    public StatType getType() {
        return StatType.FISHING_SPEED;
    }

    @Override
    public void apply(Player player, double value) {
        // Used in fishing events
    }
}
