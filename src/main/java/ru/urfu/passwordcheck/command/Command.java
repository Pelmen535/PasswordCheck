package ru.urfu.passwordcheck.command;

public interface Command {
    String name();
    String description();
    String usage();
    String help();
    Response execute(Request request);
}