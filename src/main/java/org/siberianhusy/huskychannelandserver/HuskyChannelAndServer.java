package org.siberianhusy.huskychannelandserver;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.siberianhusy.huskychannelandserver.commands.Commands;
import org.siberianhusy.huskychannelandserver.event.ChannelMessageEvent;
import org.siberianhusy.huskychannelandserver.event.MinecraftMessageEvent;

import java.util.Objects;


public final class HuskyChannelAndServer extends JavaPlugin {
    public static HuskyChannelAndServer hcas;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.saveResource("Messages/messages.yml", false);
        hcas = this;
        Bukkit.getPluginManager().registerEvents(new MinecraftMessageEvent(),this);
        Objects.requireNonNull(Bukkit.getPluginCommand("HuskyChannelAndServer")).setExecutor(new Commands());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
