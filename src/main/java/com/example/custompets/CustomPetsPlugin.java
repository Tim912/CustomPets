package com.example.custompets;

import com.example.custompets.stats.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

public final class CustomPetsPlugin extends JavaPlugin {
    private StatsManager statsManager;

    @Override
    public void onEnable() {
        statsManager = new StatsManager(this);
        statsManager.registerStat(new HealthStat());
        statsManager.registerStat(new DefenseStat());
        statsManager.registerStat(new StrengthStat());
        statsManager.registerStat(new CritChanceStat());
        statsManager.registerStat(new CritDamageStat());
        statsManager.registerStat(new IntelligenceStat());
        statsManager.registerStat(new SpeedStat());
        statsManager.registerStat(new AttackSpeedStat());
        statsManager.registerStat(new FerocityStat());
        statsManager.registerStat(new VitalityStat());
        statsManager.registerStat(new MendingStat());
        statsManager.registerStat(new SeaCreatureChanceStat());
        statsManager.registerStat(new FishingSpeedStat());
        statsManager.registerStat(new PristineStat());
        statsManager.registerStat(new MiningSpeedStat());
        statsManager.registerStat(new MiningFortuneStat());
        statsManager.registerStat(new FarmingFortuneStat());
        statsManager.registerStat(new ForagingFortuneStat());
        statsManager.loadFormulas();

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new StatsListener(statsManager), this);

        getCommand("stats").setExecutor(new com.example.custompets.command.StatsCommand(this));

        getLogger().info("CustomPets enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("CustomPets disabled!");
    }

    public StatsManager getStatsManager() {
        return statsManager;
    }
}
