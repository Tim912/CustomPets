package com.example.custompets.stats;

import org.bukkit.entity.Player;

public class CritDamageStat implements Stat {
    @Override
    public StatType getType() {
        return StatType.CRIT_DAMAGE;
    }

    @Override
    public void apply(Player player, double value) {
        // Used in damage calculations
    }
}
