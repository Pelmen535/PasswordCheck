package ru.urfu.passwordcheck.command.impl;

import ru.urfu.passwordcheck.command.Command;
import ru.urfu.passwordcheck.command.CommandRegistry;
import ru.urfu.passwordcheck.command.Request;
import ru.urfu.passwordcheck.command.Response;

public class HelpCommand implements Command {

    private final CommandRegistry registry;

    public HelpCommand(CommandRegistry registry) {
        this.registry = registry;
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public String description() {
        return "Список команд и справка по каждой";
    }

    @Override
    public String usage() {
        return "/help [команда]";
    }

    @Override
    public String help() {
        return "Без аргумента показывает все команды. С аргументом показывает подробную справку, например: /help about";
    }

    @Override
    public Response execute(Request request) {
        if (request.args().isEmpty()) {
            return new Response(allCommands());
        }
        return new Response(oneCommand(request.args().get(0)));
    }

    private String allCommands() {
        StringBuilder sb = new StringBuilder("Доступные команды:\n");
        for (Command command : registry.all()) {
            sb.append(command.usage()).append(" — ").append(command.description()).append("\n");
        }
        return sb.toString();
    }

    private String oneCommand(String rawName) {
        String name = rawName.startsWith("/") ? rawName.substring(1) : rawName;
        return registry.find(name)
                .map(c -> c.usage() + "\n" + c.help())
                .orElse("Неизвестная команда: " + rawName + ". Список команд: /help");
    }
}