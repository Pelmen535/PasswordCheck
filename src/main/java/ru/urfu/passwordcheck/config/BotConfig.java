package ru.urfu.passwordcheck.config;

import java.util.function.UnaryOperator;

public final class BotConfig {

    private static final String TOKEN_VARIABLE = "BOT_TOKEN";

    private final String token;

    private BotConfig(String token) {
        this.token = token;
    }

    public static BotConfig fromEnvironment() {
        return from(System::getenv);
    }

    static BotConfig from(UnaryOperator<String> env) {
        String value = env.apply(TOKEN_VARIABLE);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Не задана переменная окружения " + TOKEN_VARIABLE + ". См. .env.example");
        }
        return new BotConfig(value.strip());
    }

    public String token() {
        return token;
    }

    @Override
    public String toString() {
        return "BotConfig[token=***]";
    }
}