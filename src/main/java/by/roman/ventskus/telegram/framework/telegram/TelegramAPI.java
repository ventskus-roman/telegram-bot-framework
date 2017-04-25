package by.roman.ventskus.telegram.framework.telegram;

import by.roman.ventskus.telegram.framework.entity.User;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.io.InputStream;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public interface TelegramAPI {

    public void send(String text, User user, ReplyKeyboard replyKeyboardMarkup, boolean enableMarkdown);

    public void sendPhoto(String fileName, User user, InputStream inputStream);
}
