package ru.urfu.passwordcheck.command;

import java.util.List;

public record Request(long userId, String name, List<String> args) {}