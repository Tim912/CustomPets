package com.example.custompets.stats;

import org.bukkit.entity.Player;

public class SeaCreatureChanceStat implements Stat {
    @Override
    public StatType getType() {
        return StatType.SEA_CREATURE_CHANCE;
    }

    @Override
    public void apply(Player player, double value) {
        // Used in fishing events
    }
}
