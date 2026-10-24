
package ru.urfu.passwordcheck.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.urfu.passwordcheck.command.impl.AboutCommand;
import ru.urfu.passwordcheck.command.impl.AuthorCommand;
import ru.urfu.passwordcheck.command.impl.HelpCommand;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandMessageHandlerTest {

    private CommandRegistry registry;
    private MessageHandler handler;

    @BeforeEach
    void setUp() {
        registry = new CommandRegistry();

        registry.register(new AboutCommand());
        registry.register(new AuthorCommand());
        registry.register(new HelpCommand(registry));

        handler = new CommandMessageHandler(registry);
    }

    @Test
    void executesAboutCommand() {
        Response response = handler.handle(123L, "/about");

        assertTrue(response.text().contains("PasswordCheck"));
    }

    @Test
    void executesAuthorCommand() {
        Response response = handler.handle(123L, "/author");

        assertTrue(response.text().contains("Шека Николай"));
    }

    @Test
    void executesHelpCommand() {
        Response response = handler.handle(123L, "/help");

        assertTrue(response.text().contains("/about"));
        assertTrue(response.text().contains("/author"));
        assertTrue(response.text().contains("/help"));
    }

    @Test
    void passesArgumentsToHelp() {
        Response response = handler.handle(123L, "/help about");

        assertTrue(
                response.text().contains(new AboutCommand().help())
        );
    }

    @Test
    void ignoresExtraSpaces() {
        Response response = handler.handle(
                123L, "   /help    about   "
        );

        assertTrue(
                response.text().contains(new AboutCommand().help())
        );
    }

    @Test
    void handlesUnknownCommand() {
        Response response = handler.handle(123L, "/unknown");

        assertEquals(
                "Неизвестная команда. Список команд: /help",
                response.text()
        );
    }

    @Test
    void handlesEmptyMessage() {
        Response response = handler.handle(123L, "   ");

        assertEquals(
                "Введите команду. Список команд: /help",
                response.text()
        );
    }

    @Test
    void handlesTextWithoutSlash() {
        Response response = handler.handle(123L, "Привет");

        assertEquals(
                "Команда должна начинаться с /. Список команд: /help",
                response.text()
        );
    }

    @Test
    void passesUserIdAndArguments() {
        registry.register(new Command() {

            public String name() { return "inspect"; }

            public String description() { return "Проверка"; }

            public String usage() { return "/inspect"; }

            public String help() { return "Проверяет запрос"; }

            public Response execute(Request request) {
                return new Response(
                        request.userId() + ":" +
                                request.name() + ":" +
                                String.join(",", request.args())
                );
            }
        });

        Response response = handler.handle(
                42L, "/inspect first second"
        );

        assertEquals(
                "42:inspect:first,second",
                response.text()
        );
    }
}
