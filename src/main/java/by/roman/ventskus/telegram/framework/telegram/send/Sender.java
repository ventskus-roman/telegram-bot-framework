package by.roman.ventskus.telegram.framework.telegram.send;

import by.roman.ventskus.telegram.framework.entity.response.ManyMessagesResponse;
import by.roman.ventskus.telegram.framework.entity.response.OneMessageResponse;
import by.roman.ventskus.telegram.framework.entity.response.PhotoResponse;
import by.roman.ventskus.telegram.framework.entity.response.Response;
import by.roman.ventskus.telegram.framework.telegram.TelegramAPI;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public class Sender {

    private TelegramAPI telegramAPI;
    private SendStrategy singleMessageSendStrategy;
    private SendStrategy manyMessagesSendStrategy;

    public Sender(TelegramAPI telegramAPI) {
        this.telegramAPI = telegramAPI;
        this.singleMessageSendStrategy = new SingleMessageSendStrategy(telegramAPI);
        this.manyMessagesSendStrategy = new ManyMessagesSendStrategy(telegramAPI);
    }

    public void send(Response response) {
        if (response instanceof OneMessageResponse) {
            singleMessageSendStrategy.send(response);
        } else if (response instanceof ManyMessagesResponse) {
            manyMessagesSendStrategy.send(response);
        } else if (response instanceof PhotoResponse) {
            PhotoResponse photoResponse = (PhotoResponse) response;
            telegramAPI.sendPhoto(photoResponse.getFileName(), photoResponse.getUser(), photoResponse.getInputStream());
        }
    }

    public Integer getMembersCount(Long chatId) {
        return telegramAPI.getMembersCount(chatId);
    }
}
