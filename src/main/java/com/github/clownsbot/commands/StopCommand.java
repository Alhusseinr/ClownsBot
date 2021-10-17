package com.github.clownsbot.commands;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.channel.VoiceChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.server.ServerUpdater;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import com.github.clownsbot.utils.Bot;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class StopCommand implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if(event.getMessageContent().contains("!stop")) {
            try{
                event.getApi().disconnect();
                Bot.connect();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
