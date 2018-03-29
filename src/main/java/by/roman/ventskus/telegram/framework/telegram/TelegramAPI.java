package by.roman.ventskus.telegram.framework.telegram;

import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;

import java.io.InputStream;

import by.roman.ventskus.telegram.framework.entity.User;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public interface TelegramAPI {

    public void send(String text, User user, ReplyKeyboard replyKeyboardMarkup, boolean enableMarkdown,
                     boolean enableWebPreview);

    Integer getMembersCount(Long chatId);

    public void sendPhoto(String fileName, User user, InputStream inputStream);
}
