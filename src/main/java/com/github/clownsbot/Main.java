package com.github.clownsbot;

import com.github.clownsbot.commands.NewGuyCommand;
import com.github.clownsbot.commands.PingCommand;
import com.github.clownsbot.commands.PlayCommand;
import com.github.clownsbot.commands.StopCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import com.github.clownsbot.utils.Bot;
public class Main {
    public static void main(String args[]) {
        Bot.connect();
    }
}
