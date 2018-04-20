package by.roman.ventskus.telegram.framework.telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.GetFile;
import org.telegram.telegrambots.api.methods.groupadministration.GetChatMemberCount;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import by.roman.ventskus.telegram.framework.Framework;
import by.roman.ventskus.telegram.framework.entity.User;
import by.roman.ventskus.telegram.framework.entity.request.Request;
import by.roman.ventskus.telegram.framework.router.CommandRouter;
import lombok.Getter;
import lombok.Setter;


/**
 * Created by Roman Ventskus on 23.04.2016.
 */
@Getter
@Setter
public abstract class TelegramRealApi extends TelegramLongPollingBot implements TelegramAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramRealApi.class);
    private Framework framework;

    public void onUpdateReceived(Update update) {
        String text = null;
        Integer chatId = null;
        String username = null;
        Long forwardedChannelId = null;
        String forwardedChannelName = null;
        String forwardedChannelUsername = null;
        File photo = null;
        Message message = update.getMessage();
        if (message != null) {
            text = message.getText();
            chatId = message.getFrom().getId();
            username = message.getFrom().getUserName();
            List<PhotoSize> photos = message.getPhoto();
            photo = loadPhoto(photos);
            Chat forwardedChannel = message.getForwardFromChat();
            if (forwardedChannel != null) {
                forwardedChannelId = forwardedChannel.getId();
                forwardedChannelName = forwardedChannel.getTitle();
                forwardedChannelUsername = forwardedChannel.getUserName();
            }
        } else {
            text = update.getCallbackQuery().getData();
            chatId = update.getCallbackQuery().getFrom().getId();
        }
        Request request = new Request(new User(new Long(chatId), username), text, getCommandRouter().isRoute(text), forwardedChannelId,
                forwardedChannelName, forwardedChannelUsername, photo);
        framework.process(request);
    }

    private File loadPhoto(List<PhotoSize> photos) {
        if (photos != null && !photos.isEmpty()) {
            try {
                PhotoSize lastPhoto = photos.get(photos.size() - 1);
                GetFile getFileMethod = new GetFile();
                getFileMethod.setFileId(lastPhoto.getFileId());
                org.telegram.telegrambots.api.objects.File file = execute(getFileMethod);
                return this.downloadFile(file.getFilePath());
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }

    @Override
    public abstract String getBotUsername();

    @Override
    public abstract String getBotToken();

    public abstract CommandRouter getCommandRouter();

    public void send(String text, User user, ReplyKeyboard replyKeyboardMarkup, boolean enableMarkdown,
                     boolean enableWebPreview) {
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.enableMarkdown(enableMarkdown);
            sendMessage.setText(text);
            sendMessage.setChatId(user.getId());
            if (enableWebPreview) {
                sendMessage.enableWebPagePreview();
            } else {
                sendMessage.disableWebPagePreview();
            }
            if (replyKeyboardMarkup != null) {
                sendMessage.setReplyMarkup(replyKeyboardMarkup);
            }
            sendMessage.validate();
            execute(sendMessage);
        } catch (TelegramApiRequestException e) {
            String description = e.getApiResponse();
            LOGGER.error("Sending exception, descpription: " + description, e);
        } catch (Exception e) {
            LOGGER.error("Sending exception", e);
        }
    }

    @Override
    public Integer getMembersCount(Long chatId) {
        try {
            GetChatMemberCount request = new GetChatMemberCount();
            request.setChatId(chatId);
            return getChatMemberCount(request);
        } catch (Exception e) {
            LOGGER.error("Get chat count exception", e);
        }
        return null;
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
