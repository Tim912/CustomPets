package com.example.custompets;

import org.bukkit.plugin.java.JavaPlugin;

public final class CustomPetsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("CustomPets enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("CustomPets disabled!");
    }
}
