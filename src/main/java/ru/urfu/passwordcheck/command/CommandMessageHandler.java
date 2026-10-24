
package ru.urfu.passwordcheck.command;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class CommandMessageHandler implements MessageHandler {

    private final CommandRegistry registry;

    public CommandMessageHandler(CommandRegistry registry) {
        this.registry = Objects.requireNonNull(registry);
    }

    @Override
    public Response handle(long userId, String text) {

        // Проверяем пустое сообщение
        if (text == null || text.isBlank()) {
            return new Response("Введите команду. Список команд: /help");
        }

        // Убираем пробелы по краям
        String normalized = text.strip();

        // Проверяем наличие /
        if (!normalized.startsWith("/")) {
            return new Response(
                    "Команда должна начинаться с /. Список команд: /help"
            );
        }

        // Разделяем сообщение на части
        String[] parts = normalized.split("\\s+");

        // Из "/help" получаем "help"
        String name = parts[0].substring(1);

        if (name.isEmpty()) {
            return new Response(
                    "Неизвестная команда. Список команд: /help"
            );
        }

        // Ищем команду в реестре
        Optional<Command> command = registry.find(name);

        if (command.isEmpty()) {
            return new Response(
                    "Неизвестная команда. Список команд: /help"
            );
        }

        // Получаем аргументы после названия команды
        List<String> args = Arrays.stream(parts)
                .skip(1)
                .toList();

        // Создаём запрос
        Request request = new Request(userId, name, args);

        // Выполняем найденную команду
        return command.get().execute(request);
    }
}
