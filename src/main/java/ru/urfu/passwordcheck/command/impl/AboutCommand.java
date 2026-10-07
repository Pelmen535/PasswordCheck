package ru.urfu.passwordcheck.command.impl;

import ru.urfu.passwordcheck.command.Command;
import ru.urfu.passwordcheck.command.Request;
import ru.urfu.passwordcheck.command.Response;

public class AboutCommand implements Command {

    @Override
    public String name() {
        return "about";
    }

    @Override
    public String description() {
        return "О назначении бота";
    }

    @Override
    public String usage() {
        return "/about";
    }

    @Override
    public String help() {
        return "Рассказывает, зачем нужен бот и чего он не делает.";
    }

    @Override
    public Response execute(Request request) {
        return new Response("""
        PasswordCheck — бот для парольной гигиены.
        Генерирует пароли, оценивает их стойкость и проверяет по базе утечек.
        Ни один пароль не сохраняется.
        """);
    }
}
