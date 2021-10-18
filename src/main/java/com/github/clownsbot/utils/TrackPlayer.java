package com.github.clownsbot.utils;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import org.javacord.api.audio.AudioSource;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.entity.channel.ServerVoiceChannel;

import java.util.LinkedList;
import java.util.Queue;

public class TrackPlayer {
    public static void play(ServerVoiceChannel channel, String url, MessageCreateEvent event) {
        channel.connect().thenAccept(audioConnection -> {
            AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
            playerManager.registerSourceManager(new YoutubeAudioSourceManager());

            AudioPlayer player = playerManager.createPlayer();

            AudioSource source = new LavaplayerAudioSource(event.getApi(), player);
            audioConnection.setAudioSource(source);

            playerManager.loadItem(url, new AudioLoadResultHandler() {
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
                    event.getChannel().sendMessage("something happened in the player");
                }
            });


        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
    }
}
