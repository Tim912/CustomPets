package com.example.custompets.stats;

import org.bukkit.entity.Player;

public class MiningSpeedStat implements Stat {
    @Override
    public StatType getType() {
        return StatType.MINING_SPEED;
    }

    @Override
    public void apply(Player player, double value) {
        // Used to modify block break speed
    }
}
