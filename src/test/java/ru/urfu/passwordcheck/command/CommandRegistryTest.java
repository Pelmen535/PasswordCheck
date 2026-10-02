package ru.urfu.passwordcheck.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.urfu.passwordcheck.command.impl.AboutCommand;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CommandRegistryTest {

    private CommandRegistry registry;

    @BeforeEach
    void setUp() {
        registry = new CommandRegistry();
    }

    @Test
    void findsRegisteredCommandByName() {
        Command about = new AboutCommand();
        registry.register(about);

        Optional<Command> result = registry.find("about");

        assertTrue(result.isPresent());
        assertSame(about, result.get());
    }

    @Test
    void searchIgnoresCase() {
        registry.register(new AboutCommand());

        assertTrue(registry.find("ABOUT").isPresent());
    }

    @Test
    void unknownCommandIsNotFound() {
        assertTrue(registry.find("nope").isEmpty());
    }

    @Test
    void duplicateRegistrationThrows() {
        registry.register(new AboutCommand());

        assertThrows(IllegalArgumentException.class,
                () -> registry.register(new AboutCommand()));
    }
}