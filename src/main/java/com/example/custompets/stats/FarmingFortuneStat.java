package com.example.custompets.stats;

import org.bukkit.entity.Player;

public class FarmingFortuneStat implements Stat {
    @Override
    public StatType getType() {
        return StatType.FARMING_FORTUNE;
    }

    @Override
    public void apply(Player player, double value) {
        // Used for crop drop events
    }
}
