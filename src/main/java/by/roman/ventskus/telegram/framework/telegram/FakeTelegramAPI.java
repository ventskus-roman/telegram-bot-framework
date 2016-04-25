package by.roman.ventskus.telegram.framework.telegram;

import by.roman.ventskus.telegram.framework.entity.User;
import org.telegram.telegrambots.api.objects.ReplyKeyboardMarkup;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public class FakeTelegramAPI implements TelegramAPI {
    @Override
    public void send(String text, User user, ReplyKeyboardMarkup replyKeyboardMarkup) {
        System.out.println(text);
    }

    @Override
    public void sendPhoto(String fileName, User user) {

    }
}
