package com.example.custompets.stats;

import org.bukkit.entity.Player;

public class MendingStat implements Stat {
    @Override
    public StatType getType() {
        return StatType.MENDING;
    }

    @Override
    public void apply(Player player, double value) {
        // Healing others modifier handled elsewhere
    }
}
