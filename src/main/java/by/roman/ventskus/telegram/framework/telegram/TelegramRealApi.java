package by.roman.ventskus.telegram.framework.telegram;

import by.roman.ventskus.telegram.framework.Framework;
import by.roman.ventskus.telegram.framework.entity.User;
import by.roman.ventskus.telegram.framework.entity.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.SendMessage;
import org.telegram.telegrambots.api.methods.SendPhoto;
import org.telegram.telegrambots.api.objects.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public abstract class TelegramRealApi extends TelegramLongPollingBot implements TelegramAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramRealApi.class);

    public void onUpdateReceived(Update update) {
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        Request request = new Request(new User(chatId + ""), text);
        Framework.getInstance().process(request);
    }

    @Override
    public abstract String getBotUsername();

    @Override
    public abstract String getBotToken();

    public void send(String text, User user, ReplyKeyboardMarkup replyKeyboardMarkup) {
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(text);
            sendMessage.setChatId(user.getId());
            if (replyKeyboardMarkup != null) {
                sendMessage.setReplayMarkup(replyKeyboardMarkup);
            }
            sendMessage(sendMessage);
        } catch (Exception e) {
            LOGGER.error("Sending exception", e);
        }
    }

    public void sendPhoto(String fileName, User user) {
        try {
            SendPhoto sendPhoto = new SendPhoto();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy_hh:mm:ss");
            sendPhoto.setNewPhoto(fileName, simpleDateFormat.format(new Date()) + ".png");
            sendPhoto.setChatId(user.getId());
            sendPhoto(sendPhoto);
        } catch (Exception e) {
            LOGGER.error("Sending photo exception", e);
        }
    }
}
