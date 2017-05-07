package by.roman.ventskus.telegram.framework.telegram.send;

import by.roman.ventskus.telegram.framework.entity.response.ManyMessagesResponse;
import by.roman.ventskus.telegram.framework.telegram.TelegramAPI;

import java.util.List;

/**
 * Created by Roman Ventskus on 24.04.2016.
 */
public class ManyMessagesSendStrategy extends SendStrategy<ManyMessagesResponse> {

    public ManyMessagesSendStrategy(TelegramAPI telegramAPI) {
        super(telegramAPI);
    }

    @Override
    void send(ManyMessagesResponse response) {
        List<String> messages = response.getMessages();
        if (messages.size() > 1) {
            messages.stream()
                    .limit(Math.max(0, messages.size() - 1))
                    .forEach(s -> getTelegramAPI().send(s, response.getUser(), null, response.isEnableMarkdown(),
                            response.isEnableWebPreview()));
        }
        messages.stream()
                .skip(Math.max(0, messages.size() - 1))
                .forEach(s -> getTelegramAPI().send(s, response.getUser(), response.getReplyKeyboardMarkup(),
                        response.isEnableMarkdown(), response.isEnableWebPreview()));
    }
}
