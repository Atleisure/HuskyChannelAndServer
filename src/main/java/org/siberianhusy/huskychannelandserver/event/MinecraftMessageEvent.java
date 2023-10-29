package org.siberianhusy.huskychannelandserver.event;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.siberianhusy.huskybotapi.utils.Replace;
import org.siberianhusy.huskychannelandserver.Data;

import java.util.Objects;

public class MinecraftMessageEvent implements Listener {
    @EventHandler
    public static void PlayerMessage(PlayerChatEvent event){
        if (Data.isRun){
            if (Data.channel==null){
                Bukkit.broadcastMessage(Replace.replaceColor("&c频道不存在!"));
            }else {
                String msg = org.siberianhusy.huskychannelandserver.utils.Replace.replaceName(Data.config.getString("prefix"),event.getPlayer().getName()) +event.getMessage();
                Data.channel.send(msg);
            }
        }
    }
    @EventHandler
    public static void PlayerDeathMessage(PlayerDeathEvent event){
        if (Data.isRun){
            if (Data.channel==null){
                Bukkit.broadcastMessage(Replace.replaceColor("&c频道不存在!"));
            }else {
                String msg = event.getDeathMessage();
                Data.channel.send(msg);
            }
        }
    }
    @EventHandler
    public static void PlayerJoinMessage(PlayerJoinEvent event){
        if (Data.isRun){
            if (Data.channel==null){
                Bukkit.broadcastMessage(Replace.replaceColor("&c频道不存在!"));
            }else {
                String msg = org.siberianhusy.huskychannelandserver.utils.Replace.replaceName(Objects.requireNonNull(Data.messages.getString("join")),event.getPlayer().getName());
                Data.channel.send(msg);
            }
        }
    }
    @EventHandler
    public static void PlayerQuitMessage(PlayerQuitEvent event){
        if (Data.isRun){
            if (Data.channel==null){
                Bukkit.broadcastMessage(Replace.replaceColor("&c频道不存在!"));
            }else {
                String msg = org.siberianhusy.huskychannelandserver.utils.Replace.replaceName(Objects.requireNonNull(Data.messages.getString("quit")),event.getPlayer().getName());
                Data.channel.send(msg);
            }

        }
    }
}
