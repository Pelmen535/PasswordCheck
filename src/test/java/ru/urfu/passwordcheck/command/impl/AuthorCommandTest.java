package ru.urfu.passwordcheck.command.impl;

import org.junit.jupiter.api.Test;
import ru.urfu.passwordcheck.command.Request;
import ru.urfu.passwordcheck.command.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorCommandTest {

    @Test
    void nameIsAbout() {
        assertEquals("author", new AuthorCommand().name());
    }

    @Test
    void executeReturnsNonEmptyText() {
        Response response = new AuthorCommand().execute(new Request(1L, "author", List.of()));
        assertFalse(response.text().isBlank());
    }
}