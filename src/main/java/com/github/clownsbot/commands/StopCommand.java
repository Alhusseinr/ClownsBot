package com.github.clownsbot.commands;

import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.channel.VoiceChannel;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.List;
import java.util.Optional;

public class StopCommand implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if(event.getMessageContent().contains("!")) {
            event.getChannel().sendMessage(event.getMessageContent());



//            User user = event.getMessage().getAuthor().asUser().get();
//
//            long userVoiceChannelId = 0;
//            List<ServerVoiceChannel> voiceChannels = event.getServer().get().getVoiceChannels();
//            for(ServerVoiceChannel channel : voiceChannels) {
//                if (user.isConnected(channel)) {
//                    userVoiceChannelId = channel.getId();
//                }
//            }
//
//            Optional<VoiceChannel> connectedChannel = event.getApi().getVoiceChannelById(userVoiceChannelId);
//            System.out.println(connectedChannel);
//            if(connectedChannel.equals(Optional.empty())) {
//                event.getChannel().sendMessage("Not in the channel you degenerate!");
//            }

        }
    }
}
