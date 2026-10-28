package ru.urfu.passwordcheck.command;

import ru.urfu.passwordcheck.command.impl.AboutCommand;
import ru.urfu.passwordcheck.command.impl.AuthorCommand;
import ru.urfu.passwordcheck.command.impl.HelpCommand;

/**
 * Единственное место, где перечислены команды бота.
 */
public final class DefaultCommands {

    private DefaultCommands() {
    }

    /**
     * Создаёт реестр со всеми командами бота: about, author, help.
     */
    public static CommandRegistry createRegistry() {
        CommandRegistry registry = new CommandRegistry();
        registry.register(new AboutCommand());
        registry.register(new AuthorCommand());
        registry.register(new HelpCommand(registry));
        return registry;
    }
}
