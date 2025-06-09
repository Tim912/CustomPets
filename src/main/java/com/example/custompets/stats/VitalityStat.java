package com.example.custompets.stats;

import org.bukkit.entity.Player;

public class VitalityStat implements Stat {
    @Override
    public StatType getType() {
        return StatType.VITALITY;
    }

    @Override
    public void apply(Player player, double value) {
        // Healing received modifier handled elsewhere
    }
}
