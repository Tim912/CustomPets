package com.example.custompets.stats;

import org.bukkit.entity.Player;

public class FerocityStat implements Stat {
    @Override
    public StatType getType() {
        return StatType.FEROCITY;
    }

    @Override
    public void apply(Player player, double value) {
        // Additional hit chance handled in damage events
    }
}
