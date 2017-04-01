package by.roman.ventskus.telegram.framework.telegram;

import by.roman.ventskus.telegram.framework.Framework;
import by.roman.ventskus.telegram.framework.entity.User;
import by.roman.ventskus.telegram.framework.entity.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.io.InputStream;


/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public abstract class TelegramRealApi extends TelegramLongPollingBot implements TelegramAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramRealApi.class);

    public void onUpdateReceived(Update update) {
        String text = null;
        Integer chatId = null;
        if (update.getMessage() != null) {
            text = update.getMessage().getText();
            chatId = update.getMessage().getFrom().getId();
        } else {
            text = update.getCallbackQuery().getData();
            chatId = update.getCallbackQuery().getFrom().getId();
        }
        Request request = new Request(new User(chatId), text);
        Framework.getInstance().process(request);
    }

    @Override
    public abstract String getBotUsername();

    @Override
    public abstract String getBotToken();

    public void send(String text, User user, ReplyKeyboard replyKeyboardMarkup) {
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.enableMarkdown(true);
            sendMessage.setText(text);
            sendMessage.setChatId(user.getId() + "");
            if (replyKeyboardMarkup != null) {
                sendMessage.setReplyMarkup(replyKeyboardMarkup);
            }
            sendMessage(sendMessage);
        } catch (Exception e) {
            LOGGER.error("Sending exception", e);
        }
    }

    public void sendPhoto(String fileName, User user, InputStream inputStream) {
        try {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setNewPhoto(fileName, inputStream);
            sendPhoto.setChatId(user.getId() + "");
            sendPhoto(sendPhoto);
        } catch (Exception e) {
            LOGGER.error("Sending photo exception", e);
        }
    }
}
