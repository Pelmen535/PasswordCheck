
package ru.urfu.passwordcheck.command;

public interface MessageHandler {

    Response handle(long userId, String text);

}
