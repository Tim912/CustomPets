package com.example.custompets.stats;

import java.util.EnumMap;
import java.util.Map;

/**
 * Holds the values for all stats of a player.
 */
public class PlayerStats {
    private final Map<StatType, Double> values = new EnumMap<>(StatType.class);

    public double get(StatType type) {
        return values.getOrDefault(type, 0.0);
    }

    public void set(StatType type, double value) {
        values.put(type, value);
    }

    public Map<StatType, Double> getValues() {
        return values;
    }
}
