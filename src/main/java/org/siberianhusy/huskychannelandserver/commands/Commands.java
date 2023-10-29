package org.siberianhusy.huskychannelandserver.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.siberianhusy.huskybotapi.utils.SendMessage;
import org.siberianhusy.huskychannelandserver.Data;
import org.siberianhusy.huskychannelandserver.HuskyChannelAndServer;
import org.siberianhusy.huskychannelandserver.event.ChannelMessageEvent;
import org.siberianhusy.huskychannelandserver.utils.Reload;
import org.siberianhusy.huskychannelandserver.utils.Replace;

import java.util.Objects;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender.hasPermission("huskychannelandserver.admin")){
            if (args.length==0||(args.length==1&&args[0].equals("help"))){
                SendMessage.sendMessage(Objects.requireNonNull(Data.messages.getString("help")),sender);
                return true;
            }
            if (args.length==1&&args[0].equals("run")){
                if (Data.isFirstRun){
                    ChannelMessageEvent.channelMessages();
                    Data.isRun = true;
                    Data.isFirstRun = false;
                }else {
                    Data.isRun = true;
                }
                SendMessage.sendMessage(Objects.requireNonNull(Data.messages.getString("run")),sender);
                return true;
            }
            if (args.length==1&&args[0].equals("close")){
                Data.isRun = false;
                SendMessage.sendMessage(Objects.requireNonNull(Data.messages.getString("close")),sender);
                return true;
            }
            if (args.length==2&&args[0].equals("set")){
                HuskyChannelAndServer.hcas.getConfig().set("channelName",args[1]);
                Reload.reload();
                SendMessage.sendMessage(Replace.replaceName(Objects.requireNonNull(Data.messages.getString("set")),args[1]),sender);
                return true;
            }
            if (args.length==1&&args[0].equals("reload")){
                Reload.reload();
                SendMessage.sendMessage(Replace.replaceName("&c插件已重载!",args[0]),sender);
                return true;
            }
            SendMessage.sendMessage(Objects.requireNonNull(Data.messages.getString("help")),sender);
            return true;
        }
        return false;
    }
}
