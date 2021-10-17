package com.github.clownsbot.utils;

import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserInfo {
    public static long getVoiceChannelId(@NotNull MessageCreateEvent event, @NotNull User user) {
        long voiceChannelId = 0;
        List<ServerVoiceChannel> voiceChannels = event.getServer().get().getVoiceChannels();
        for(ServerVoiceChannel channel : voiceChannels) {
            if (user.isConnected(channel)) {
                voiceChannelId = channel.getId();
            }
        }
        return voiceChannelId;
    }
}
