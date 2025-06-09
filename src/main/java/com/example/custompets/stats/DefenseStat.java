package com.example.custompets.stats;

import org.bukkit.entity.Player;

public class DefenseStat implements Stat {
    @Override
    public StatType getType() {
        return StatType.DEFENSE;
    }

    @Override
    public void apply(Player player, double value) {
        // Defense is applied via event listener on damage
        // Actual damage reduction handled elsewhere
    }
}
