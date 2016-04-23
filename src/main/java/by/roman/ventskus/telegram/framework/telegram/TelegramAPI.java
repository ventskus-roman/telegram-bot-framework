package by.roman.ventskus.telegram.framework.telegram;

import by.roman.ventskus.telegram.framework.entity.User;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public interface TelegramAPI {

    public void send(String text, User user);

    public void sendPhoto(String fileName, User user);
}
