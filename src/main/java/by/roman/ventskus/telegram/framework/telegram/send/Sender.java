package by.roman.ventskus.telegram.framework.telegram.send;

import by.roman.ventskus.telegram.framework.entity.response.OneMessageResponse;
import by.roman.ventskus.telegram.framework.entity.response.Response;
import by.roman.ventskus.telegram.framework.telegram.TelegramAPI;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public class Sender {

    private TelegramAPI telegramAPI;
    private SendStrategy singleMessageSendStrategy;

    public Sender(TelegramAPI telegramAPI) {
        this.telegramAPI = telegramAPI;
        this.singleMessageSendStrategy = new SingleMessageSendStrategy(telegramAPI);
    }

    public void send(Response response) {
        if (response instanceof OneMessageResponse) {
            singleMessageSendStrategy.send(response);
        }
    }
}
