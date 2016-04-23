package by.roman.ventskus.telegram.framework.telegram.send;

import by.roman.ventskus.telegram.framework.entity.response.OneMessageResponse;
import by.roman.ventskus.telegram.framework.telegram.TelegramAPI;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public class SingleMessageSendStrategy extends SendStrategy<OneMessageResponse> {

    public SingleMessageSendStrategy(TelegramAPI telegramAPI) {
        super(telegramAPI);
    }

    @Override
    public void send(OneMessageResponse response) {
        getTelegramAPI().send(response.getText(), response.getUser());
    }
}
