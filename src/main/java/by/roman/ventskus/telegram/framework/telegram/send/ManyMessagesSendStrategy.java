package by.roman.ventskus.telegram.framework.telegram.send;

import by.roman.ventskus.telegram.framework.entity.response.ManyMessagesResponse;
import by.roman.ventskus.telegram.framework.telegram.TelegramAPI;

/**
 * Created by Roman Ventskus on 24.04.2016.
 */
public class ManyMessagesSendStrategy extends SendStrategy<ManyMessagesResponse> {

    public ManyMessagesSendStrategy(TelegramAPI telegramAPI) {
        super(telegramAPI);
    }

    @Override
    void send(ManyMessagesResponse response) {
        response.getMessages().forEach(s -> getTelegramAPI().send(s, response.getUser(), response.getReplyKeyboardMarkup()));
    }
}
