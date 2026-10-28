
package ru.urfu.passwordcheck.command;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Создаём реестр
        CommandRegistry registry = DefaultCommands.createRegistry();

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
