package com.github.clownsbot.commands;

import com.github.clownsbot.utils.UserInfo;
import com.github.clownsbot.utils.Link;

import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import com.github.clownsbot.utils.TrackPlayer;

public class PlayCommand implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if(event.getMessageContent().contains("!play ")) {
            try {
                String url = event.getMessageContent().replace("!play ", "");
                if(Link.validate(url)) {
                    User user = event.getMessage().getAuthor().asUser().get();
                    long userVoiceChannelId = UserInfo.getVoiceChannelId(event, user);

                    if(userVoiceChannelId != 0 ){
                        try{
                            ServerVoiceChannel channel = event.getApi().getServerVoiceChannelById(userVoiceChannelId).get();
                            TrackPlayer.play(channel, url, event);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    event.getChannel().sendMessage("This is not a valid link dumb fuck");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
