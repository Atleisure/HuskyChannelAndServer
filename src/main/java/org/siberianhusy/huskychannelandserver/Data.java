package org.siberianhusy.huskychannelandserver;

import io.github.kloping.qqbot.entities.qqpd.Channel;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;


public class Data {
    public static FileConfiguration config = HuskyChannelAndServer.hcas.getConfig();
    public static String channelId = "";
    public static Channel channel = null;
    public static File messagesFile = new File(HuskyChannelAndServer.hcas.getDataFolder(), "Messages/messages.yml");
    public static FileConfiguration messages = YamlConfiguration.loadConfiguration(messagesFile);
    public static boolean isRun = false;
    public static boolean isFirstRun = true;
}
