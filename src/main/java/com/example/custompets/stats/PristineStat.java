package com.example.custompets.stats;

import org.bukkit.entity.Player;

public class PristineStat implements Stat {
    @Override
    public StatType getType() {
        return StatType.PRISTINE;
    }

    @Override
    public void apply(Player player, double value) {
        // Used in mining events
    }
}
