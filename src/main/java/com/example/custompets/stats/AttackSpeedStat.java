package com.example.custompets.stats;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class AttackSpeedStat implements Stat {
    @Override
    public StatType getType() {
        return StatType.ATTACK_SPEED;
    }

    @Override
    public void apply(Player player, double value) {
        AttributeInstance attr = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        if (attr != null) {
            attr.setBaseValue(value);
        }
    }
}
