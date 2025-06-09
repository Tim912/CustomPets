package com.example.custompets.stats;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class SpeedStat implements Stat {
    @Override
    public StatType getType() {
        return StatType.SPEED;
    }

    @Override
    public void apply(Player player, double value) {
        AttributeInstance attr = player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        if (attr != null) {
            attr.setBaseValue(value);
        }
    }
}
