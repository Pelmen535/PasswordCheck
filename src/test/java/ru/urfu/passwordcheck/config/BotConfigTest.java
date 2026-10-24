package ru.urfu.passwordcheck.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BotConfigTest {

    @Test
    void readsTokenFromEnvironment() {
        assertEquals("123:abc", BotConfig.from(name -> "123:abc").token());
    }

    @Test
    void failsWhenTokenIsMissing() {
        assertThrows(IllegalStateException.class, () -> BotConfig.from(name -> null));
    }

    @Test
    void failsWhenTokenIsBlank() {
        assertThrows(IllegalStateException.class, () -> BotConfig.from(name -> "   "));
    }

    @Test
    void toStringDoesNotLeakToken() {
        assertFalse(BotConfig.from(name -> "123:abc").toString().contains("123:abc"));
    }
}