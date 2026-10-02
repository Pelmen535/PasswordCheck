package ru.urfu.passwordcheck.command.impl;

import ru.urfu.passwordcheck.command.Command;
import ru.urfu.passwordcheck.command.Request;
import ru.urfu.passwordcheck.command.Response;

public class AuthorCommand implements Command {

    @Override
    public String name() {
        return "author";
    }

    @Override
    public String description() {
        return "Информация об авторах";
    }

    @Override
    public String usage() {
        return "/author";
    }

    @Override
    public String help() {
        return "Выводит список авторов бота.";
    }

    @Override
    public Response execute(Request request) {
        return new Response("""
        Шека Николай (https://github.com/Pelmen535)
        Мокин Максим (https://github.com/lufin1488)
        """);
    }
}
