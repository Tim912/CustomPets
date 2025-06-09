package com.example.custompets.stats;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class HealthStat implements Stat {
    @Override
    public StatType getType() {
        return StatType.HEALTH;
    }

    @Override
    public void apply(Player player, double value) {
        AttributeInstance attr = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (attr != null) {
            attr.setBaseValue(value);
        }
    }
}
