package com.example.custompets.stats;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

/**
 * Manages loading and saving stat formulas and values.
 */
public class StatsManager {
    private final Plugin plugin;
    private FileConfiguration formulasConfig;
    private final Map<StatType, Stat> stats = new EnumMap<>(StatType.class);

    public StatsManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerStat(Stat stat) {
        stats.put(stat.getType(), stat);
    }

    public Stat getStat(StatType type) {
        return stats.get(type);
    }

    public void loadFormulas() {
        File file = new File(plugin.getDataFolder(), "statformeln.yml");
        if (!file.exists()) {
            plugin.saveResource("statformeln.yml", false);
        }
        formulasConfig = YamlConfiguration.loadConfiguration(file);
    }

    public double getFormulaValue(StatType type) {
        return formulasConfig.getDouble(type.name(), 0.0);
    }

    public void setFormulaValue(StatType type, double value) {
        formulasConfig.set(type.name(), value);
        try {
            formulasConfig.save(new File(plugin.getDataFolder(), "statformeln.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Plugin getPlugin() {
        return plugin;
    }
}
