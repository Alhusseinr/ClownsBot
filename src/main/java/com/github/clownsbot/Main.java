package com.github.clownsbot;

import com.github.clownsbot.commands.NewGuyCommand;
import com.github.clownsbot.commands.PingCommand;
import com.github.clownsbot.commands.PlayCommand;
import com.github.clownsbot.commands.StopCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String args[]) {
        DiscordApi api = new DiscordApiBuilder().setToken(System.getenv("token")).login().join();
        

        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());

        // Adding listeners
        api.addMessageCreateListener(new PingCommand());
        api.addMessageCreateListener(new PlayCommand());
        api.addMessageCreateListener(new NewGuyCommand());
        api.addMessageCreateListener(new StopCommand());

        // Logging when the bot joins or leaves any server
        api.addServerJoinListener(event -> logger.info("Joined server " + event.getServer().getName()));
        api.addServerLeaveListener(event -> logger.info("Left server " + event.getServer().getName()));


    }
}
