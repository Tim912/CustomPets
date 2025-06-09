package com.example.custompets.stats;

import org.bukkit.entity.Player;

/**
 * Represents a player stat that can be applied to the player.
 */
public interface Stat {
    StatType getType();

    /**
     * Apply the stat value to the player.
     * This is called whenever the stat is updated.
     *
     * @param player player to apply stat to
     * @param value  value of the stat
     */
    void apply(Player player, double value);
}
