package ru.urfu.passwordcheck.command;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CommandRegistry {

    private final Map<String, Command> commands = new LinkedHashMap<>();

    public void register(Command command) {
        String key = command.name().toLowerCase();
        if (commands.containsKey(key)) {
            throw new IllegalArgumentException("Команда уже зарегистрирована: " + key);
        }
        commands.put(key, command);
    }

    public Optional<Command> find(String name) {
        return Optional.ofNullable(commands.get(name.toLowerCase()));
    }

    public Collection<Command> all() {
        return List.copyOf(commands.values());
    }
}