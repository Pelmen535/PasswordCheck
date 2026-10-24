package ru.urfu.passwordcheck.transport;

import org.junit.jupiter.api.Test;
import ru.urfu.passwordcheck.command.Response;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TelegramTransportTest {

    @Test
    void passesUserIdAndTextToHandler() {
        TelegramTransport transport = new TelegramTransport("token",
                (userId, text) -> new Response(userId + ":" + text));

        assertEquals(Optional.of("42:/help"), transport.replyTo(42, "/help"));
    }

    @Test
    void ignoresNullOrBlankText() {
        TelegramTransport transport = new TelegramTransport("token",
                (userId, text) -> new Response("x"));

        assertEquals(Optional.empty(), transport.replyTo(1, null));
        assertEquals(Optional.empty(), transport.replyTo(1, "   "));
    }

    @Test
    void returnsErrorTextWhenHandlerThrows() {
        TelegramTransport transport = new TelegramTransport("token",
                (userId, text) -> { throw new IllegalStateException("boom"); });

        assertEquals(Optional.of(TelegramTransport.ERROR_TEXT), transport.replyTo(1, "/help"));
    }
}
