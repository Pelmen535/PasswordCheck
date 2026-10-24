package ru.urfu.passwordcheck;

import ru.urfu.passwordcheck.command.Response;
import ru.urfu.passwordcheck.config.BotConfig;
import ru.urfu.passwordcheck.transport.TelegramTransport;

public class Main {
    public static void main(String[] args) {
        BotConfig config = BotConfig.fromEnvironment();
        // ВРЕМЕННАЯ заглушка: заменим на обработчик Николая, когда он будет готов
        TelegramTransport transport = new TelegramTransport(
                config.token(),
                (userId, text) -> new Response("Бот на связи. Команды подключаются."));
        transport.start();
        System.out.println("Бот запущен");
    }
}
