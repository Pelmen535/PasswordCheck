package ru.urfu.passwordcheck.command.impl;

import org.junit.jupiter.api.Test;
import ru.urfu.passwordcheck.command.Command;
import ru.urfu.passwordcheck.command.CommandRegistry;
import ru.urfu.passwordcheck.command.Request;
import ru.urfu.passwordcheck.command.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HelpCommandTest {

    private final CommandRegistry registry = new CommandRegistry();

    private Response run(List<String> args) {
        HelpCommand help = new HelpCommand(registry);
        registry.register(help);
        return help.execute(new Request(1L, "help", args));
    }

    @Test
    void withoutArgsListsAllCommands() {
        registry.register(new AboutCommand());
        registry.register(new AuthorCommand());

        String text = run(List.of()).text();

        assertTrue(text.contains("/about"));
        assertTrue(text.contains("/author"));
        assertTrue(text.contains("/help"));
    }

    @Test
    void withArgShowsDetailedHelp() {
        registry.register(new AboutCommand());

        String text = run(List.of("about")).text();

        assertTrue(text.contains(new AboutCommand().help()));
    }

    @Test
    void slashInArgIsAccepted() {
        registry.register(new AboutCommand());

        assertTrue(run(List.of("/about")).text().contains(new AboutCommand().help()));
    }

    @Test
    void unknownCommandIsReported() {
        assertTrue(run(List.of("nope")).text().contains("Неизвестная команда"));
    }

    @Test
    void newCommandAppearsInHelpAutomatically() {
        registry.register(new Command() {
            public String name() { return "ping"; }
            public String description() { return "проверка связи"; }
            public String usage() { return "/ping"; }
            public String help() { return "отвечает pong"; }
            public Response execute(Request r) { return new Response("pong"); }
        });

        assertTrue(run(List.of()).text().contains("/ping — проверка связи"));
    }
}