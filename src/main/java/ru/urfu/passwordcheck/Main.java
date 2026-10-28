package ru.urfu.passwordcheck;

import ru.urfu.passwordcheck.command.CommandMessageHandler;
import ru.urfu.passwordcheck.command.CommandRegistry;
import ru.urfu.passwordcheck.command.DefaultCommands;
import ru.urfu.passwordcheck.config.BotConfig;
import ru.urfu.passwordcheck.transport.TelegramTransport;

public class Main {
    public static void main(String[] args) {
        BotConfig config = BotConfig.fromEnvironment();

        CommandRegistry registry = DefaultCommands.createRegistry();

        TelegramTransport transport = new TelegramTransport(
                config.token(),
                new CommandMessageHandler(registry));
        transport.start();
        System.out.println("Бот запущен");
    }
}
