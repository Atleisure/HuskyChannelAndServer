package org.siberianhusy.huskychannelandserver.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.siberianhusy.huskychannelandserver.Data;
import org.siberianhusy.huskychannelandserver.HuskyChannelAndServer;

import java.io.File;

public class Reload {
    public static void reload(){
        HuskyChannelAndServer.hcas.reloadConfig();
        Data.config = HuskyChannelAndServer.hcas.getConfig();
        File messagesFile = new File(HuskyChannelAndServer.hcas.getDataFolder(), "Messages/messages.yml");
        Data.messages = YamlConfiguration.loadConfiguration(messagesFile);
    }
}
