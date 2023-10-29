package org.siberianhusy.huskychannelandserver.event;

import io.github.kloping.qqbot.api.message.MessageChannelReceiveEvent;
import io.github.kloping.qqbot.api.message.MessageEvent;
import io.github.kloping.qqbot.entities.ex.At;
import io.github.kloping.qqbot.entities.ex.AtAll;
import io.github.kloping.qqbot.entities.ex.Image;
import io.github.kloping.qqbot.entities.ex.PlainText;
import io.github.kloping.qqbot.entities.ex.msg.MessageChain;
import io.github.kloping.qqbot.entities.qqpd.User;
import io.github.kloping.qqbot.entities.qqpd.data.Emoji;
import io.github.kloping.qqbot.entities.qqpd.message.RawMessage;
import io.github.kloping.qqbot.impl.EventReceiver;
import io.github.kloping.qqbot.impl.ListenerHost;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;
import org.siberianhusy.huskybotapi.Data;
import org.siberianhusy.huskychannelandserver.utils.Replace;

import java.util.Objects;

public class ChannelMessageEvent {
    public static void channelMessages(){
        Data.starter.registerListenerHost(new ListenerHost() {

            @Override
            public void handleException(Throwable throwable) {

            }

            @EventReceiver
            private void event(MessageEvent event){
                RawMessage message = event.getRawMessage();
                if(message.getContent().equals("开启频服互联")){
                        org.siberianhusy.huskychannelandserver.Data.channelId = message.getChannelId();
                        org.siberianhusy.huskychannelandserver.Data.isRun = true;
                        String guildId = message.getGuildId();
                        org.siberianhusy.huskychannelandserver.Data.channel = Data.starter.getBot().getGuild(guildId).channelMap().get(org.siberianhusy.huskychannelandserver.Data.channelId);
                        message.send("频服互联已经开启！");
                }
                if (message.getContent().equals("关闭频服互联")){
                    if (!org.siberianhusy.huskychannelandserver.Data.isRun){
                        message.send("频服互联未开启！");
                    }else {
                        org.siberianhusy.huskychannelandserver.Data.isRun = false;
                        message.send("频服互联已关闭！");
                    }
                }
            }
            private String msg;
            @EventReceiver
            public void onMessage(MessageChannelReceiveEvent event){

                if (Data.isRun){
                    if (org.siberianhusy.huskychannelandserver.Data.isRun){
                        MessageChain chain = event.getMessage();
                        msg = Replace.replaceName(Objects.requireNonNull(org.siberianhusy.huskychannelandserver.Data.config.getString("s_prefix")),event.getRawMessage().getAuthor().getUsername());

                        chain.forEach((e)->{
                            if (e instanceof Emoji){
                                Emoji emoji = (Emoji) e;
                                msg+=emoji.getText();
                            } else if (e instanceof Image) {
                                Image image = (Image) e;
                                msg+=image.getUrl();
                            } else if (e instanceof At) {
                                At at = (At) e;
                                msg+= at;
                            } else if (e instanceof AtAll) {
                                AtAll atAll = (AtAll) e;
                                msg+=atAll;
                            } else if (e instanceof PlainText) {
                                PlainText plainText = (PlainText) e;
                                msg+=plainText.getText();
                            }
                        });
                        Bukkit.broadcastMessage(msg);
                    }
                }
            }
        });
    }
}
