
package ru.urfu.passwordcheck.command;

import java.util.Scanner;

import ru.urfu.passwordcheck.command.impl.AboutCommand;
import ru.urfu.passwordcheck.command.impl.AuthorCommand;
import ru.urfu.passwordcheck.command.impl.HelpCommand;

public class Main {

    public static void main(String[] args) {

        // Создаём реестр
        CommandRegistry registry = new CommandRegistry();

        // Регистрируем команды
        registry.register(new AboutCommand());
        registry.register(new AuthorCommand());
        registry.register(new HelpCommand(registry));

        // Создаём обработчик
        MessageHandler handler = new CommandMessageHandler(registry);

        System.out.println("PasswordCheck запущен!");
        System.out.println("Введите /help или /exit");

        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {

                System.out.print("> ");

                if (!scanner.hasNextLine()) {
                    break;
                }

                String text = scanner.nextLine();

                if (text.strip().equalsIgnoreCase("/exit")) {
                    break;
                }

                // Обрабатываем сообщение
                Response response = handler.handle(123L, text);

                // Выводим ответ
                System.out.println(response.text());
            }
        }
    }
}
