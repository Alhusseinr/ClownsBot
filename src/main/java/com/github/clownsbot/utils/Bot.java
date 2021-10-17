package com.github.clownsbot.utils;

import com.github.clownsbot.commands.NewGuyCommand;
import com.github.clownsbot.commands.PingCommand;
import com.github.clownsbot.commands.PlayCommand;
import com.github.clownsbot.commands.StopCommand;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Bot {
    public static void connect() {
        DiscordApi api = new DiscordApiBuilder().setToken(System.getenv("token")).login().join();

        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());

        // Adding listeners
        api.addMessageCreateListener(new PingCommand());
        api.addMessageCreateListener(new PlayCommand());
        api.addMessageCreateListener(new NewGuyCommand());
        api.addMessageCreateListener(new StopCommand());
    }
}
