package com.example.custompets.stats;

import org.bukkit.entity.Player;

public class StrengthStat implements Stat {
    @Override
    public StatType getType() {
        return StatType.STRENGTH;
    }

    @Override
    public void apply(Player player, double value) {
        // Strength handled when calculating damage
    }
}
