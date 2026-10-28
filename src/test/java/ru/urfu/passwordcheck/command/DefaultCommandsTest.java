package ru.urfu.passwordcheck.command;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DefaultCommandsTest {

    @Test
    void registryContainsAboutAuthorAndHelp() {
        CommandRegistry registry = DefaultCommands.createRegistry();

        assertTrue(registry.find("about").isPresent());
        assertTrue(registry.find("author").isPresent());
        assertTrue(registry.find("help").isPresent());
    }

    @Test
    void helpListsAllDefaultCommands() {
        CommandRegistry registry = DefaultCommands.createRegistry();
        Command help = registry.find("help").orElseThrow();

        String text = help.execute(new Request(1L, "help", List.of())).text();

        assertTrue(text.contains("/about"));
        assertTrue(text.contains("/author"));
        assertTrue(text.contains("/help"));
    }
}
