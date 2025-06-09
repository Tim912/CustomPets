package com.example.custompets.stats;

import org.bukkit.entity.Player;

public class MiningFortuneStat implements Stat {
    @Override
    public StatType getType() {
        return StatType.MINING_FORTUNE;
    }

    @Override
    public void apply(Player player, double value) {
        // Drop chance modifications handled in block break events
    }
}
