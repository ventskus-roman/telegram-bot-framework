package by.roman.ventskus.telegram.framework.telegram.send;

import by.roman.ventskus.telegram.framework.entity.response.OneMessageResponse;
import by.roman.ventskus.telegram.framework.telegram.TelegramAPI;
import com.google.common.base.Splitter;

import java.util.List;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public class SingleMessageSendStrategy extends SendStrategy<OneMessageResponse> {

    public SingleMessageSendStrategy(TelegramAPI telegramAPI) {
        super(telegramAPI);
    }

    @Override
    public void send(OneMessageResponse response) {
        List<String> messages = Splitter
                .fixedLength(4096)
                .splitToList(response.getText());
        if (messages.size() <= 1) {
            getTelegramAPI().send(response.getText(), response.getUser(), response.getReplyKeyboardMarkup(), response.isEnableMarkdown());
        } else {
            for (int i = 0; i < messages.size() - 1; i++) {
                getTelegramAPI().send(messages.get(i), response.getUser(), null, response.isEnableMarkdown());
            }
            getTelegramAPI().send(messages.get(messages.size() - 1), response.getUser(), response.getReplyKeyboardMarkup(), response.isEnableMarkdown());
        }
    }
}
