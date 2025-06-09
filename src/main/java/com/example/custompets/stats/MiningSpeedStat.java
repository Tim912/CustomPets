package com.example.custompets.stats;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class MiningSpeedStat implements Stat {
    @Override
    public StatType getType() {
        return StatType.MINING_SPEED;
    }

    @Override
    public void apply(Player player, double value) {

        if (value <= 0) return;
        int amplifier = (int) value - 1;
        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, amplifier, true, false));

    }
}
