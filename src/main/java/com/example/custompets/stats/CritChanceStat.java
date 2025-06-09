package com.example.custompets.stats;

import org.bukkit.entity.Player;

public class CritChanceStat implements Stat {
    @Override
    public StatType getType() {
        return StatType.CRIT_CHANCE;
    }

    @Override
    public void apply(Player player, double value) {
        // Used in damage calculations
    }
}
