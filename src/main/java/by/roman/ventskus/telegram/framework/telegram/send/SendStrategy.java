package by.roman.ventskus.telegram.framework.telegram.send;

import by.roman.ventskus.telegram.framework.entity.response.Response;
import by.roman.ventskus.telegram.framework.telegram.TelegramAPI;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public abstract class SendStrategy<T extends Response> {

    private TelegramAPI telegramAPI;

    public SendStrategy(TelegramAPI telegramAPI) {
        this.telegramAPI = telegramAPI;
    }

    abstract void send(T response);

    public TelegramAPI getTelegramAPI() {
        return telegramAPI;
    }
}
