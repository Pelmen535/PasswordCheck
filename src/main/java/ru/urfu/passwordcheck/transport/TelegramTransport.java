package ru.urfu.passwordcheck.transport;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import ru.urfu.passwordcheck.command.MessageHandler;

import java.util.Optional;

public class TelegramTransport {
    static final String ERROR_TEXT = "Что-то пошло не так. Попробуй ещё раз.";

    private final TelegramBot bot;
    private final MessageHandler handler;

    public TelegramTransport(String token, MessageHandler handler) {
        this.bot = new TelegramBot(token);
        this.handler = handler;
    }

    public void start() {
        bot.setUpdatesListener(updates -> {
            updates.forEach(this::process);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private void process(Update update) {
        Message message = update.message();
        if (message == null || message.from() == null) {
            return;
        }
        replyTo(message.from().id(), message.text())
                .ifPresent(text -> bot.execute(new SendMessage(message.chat().id(), text)));
    }

    Optional<String> replyTo(long userId, String text) {
        if (text == null || text.isBlank()) {
            return Optional.empty();
        }
        try {
            return Optional.of(handler.handle(userId, text).text());
        } catch (RuntimeException e) {
            return Optional.of(ERROR_TEXT);
        }
    }
}
