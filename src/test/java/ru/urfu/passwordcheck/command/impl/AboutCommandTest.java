package ru.urfu.passwordcheck.command.impl;

import org.junit.jupiter.api.Test;
import ru.urfu.passwordcheck.command.Request;
import ru.urfu.passwordcheck.command.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AboutCommandTest {

    @Test
    void nameIsAbout() {
        assertEquals("about", new AboutCommand().name());
    }

    @Test
    void executeReturnsNonEmptyText() {
        Response response = new AboutCommand().execute(new Request(1L, "about", List.of()));
        assertFalse(response.text().isBlank());
    }
}