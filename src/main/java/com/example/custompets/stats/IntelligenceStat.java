package com.example.custompets.stats;

import org.bukkit.entity.Player;

public class IntelligenceStat implements Stat {
    @Override
    public StatType getType() {
        return StatType.INTELLIGENCE;
    }

    @Override
    public void apply(Player player, double value) {
        // Custom mana systems would use this value
    }
}
