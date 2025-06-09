package com.example.custompets.stats;

import org.bukkit.entity.Player;

public class ForagingFortuneStat implements Stat {
    @Override
    public StatType getType() {
        return StatType.FORAGING_FORTUNE;
    }

    @Override
    public void apply(Player player, double value) {
        // Used for wood drops
    }
}
