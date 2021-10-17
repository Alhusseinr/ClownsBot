package com.github.clownsbot.commands;

import com.github.clownsbot.utils.LavaplayerAudioSource;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;

import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import org.javacord.api.audio.AudioSource;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.channel.VoiceChannel;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.List;

public class PlayCommand implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if(event.getMessageContent().contains("!play ")) {
            String url = event.getMessageContent().replace("!play", "");
            System.out.println(url);
            User user = event.getMessage().getAuthor().asUser().get();

            long userVoiceChannelId = 0;
            List<ServerVoiceChannel> voiceChannels = event.getServer().get().getVoiceChannels();
            for(ServerVoiceChannel channel : voiceChannels) {
                if (user.isConnected(channel)) {
                    userVoiceChannelId = channel.getId();
                }
            }

            if(userVoiceChannelId != 0 ){
                try{
                    ServerVoiceChannel channel = event.getApi().getServerVoiceChannelById(userVoiceChannelId).get();

                    channel.connect().thenAccept(audioConnection -> {
                        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
                        playerManager.registerSourceManager(new YoutubeAudioSourceManager());
                        AudioPlayer player = playerManager.createPlayer();

                        AudioSource source = new LavaplayerAudioSource(event.getApi(), player);
                        audioConnection.setAudioSource(source);

                        playerManager.loadItem("https://www.youtube.com/watch?v=aXJLG5Svoa0&ab_channel=ReggaetonMundial", new AudioLoadResultHandler() {
                            @Override
                            public void trackLoaded(AudioTrack audioTrack) {
                                player.playTrack(audioTrack);
                            }

                            @Override
                            public void playlistLoaded(AudioPlaylist audioPlaylist) {
                                for(AudioTrack track : audioPlaylist.getTracks()) {
                                    player.playTrack(track);
                                }
                            }

                            @Override
                            public void noMatches() {
                                event.getChannel().sendMessage("nothing was found");
                            }

                            @Override
                            public void loadFailed(FriendlyException e) {
                                event.getChannel().sendMessage("rami is not a good engineer tell him it failed");
                            }
                        });
                    }).exceptionally(e -> {
                        e.printStackTrace();
                        return null;
                    });

//                    if(event.getMessageContent().equalsIgnoreCase("!stop"))
//                        channel.getApi().disconnect();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
