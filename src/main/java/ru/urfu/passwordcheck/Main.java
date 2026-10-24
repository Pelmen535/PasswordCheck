package ru.urfu.passwordcheck;

import ru.urfu.passwordcheck.command.CommandMessageHandler;
import ru.urfu.passwordcheck.command.CommandRegistry;
import ru.urfu.passwordcheck.command.impl.AboutCommand;
import ru.urfu.passwordcheck.command.impl.AuthorCommand;
import ru.urfu.passwordcheck.command.impl.HelpCommand;
import ru.urfu.passwordcheck.config.BotConfig;
import ru.urfu.passwordcheck.transport.TelegramTransport;

public class Main {
    public static void main(String[] args) {
        BotConfig config = BotConfig.fromEnvironment();

        CommandRegistry registry = new CommandRegistry();
        registry.register(new AboutCommand());
        registry.register(new AuthorCommand());
        registry.register(new HelpCommand(registry));

        TelegramTransport transport = new TelegramTransport(
                config.token(),
                new CommandMessageHandler(registry));
        transport.start();
        System.out.println("Бот запущен");
    }
}
