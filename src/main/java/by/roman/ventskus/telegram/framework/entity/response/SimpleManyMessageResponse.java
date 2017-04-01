package by.roman.ventskus.telegram.framework.entity.response;

import lombok.*;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

/**
 * Created by Roman Ventskus on 24.04.2016.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class SimpleManyMessageResponse extends ManyMessagesResponse {

    public SimpleManyMessageResponse(List<String> messages) {
        this.messages = messages;
    }

    public SimpleManyMessageResponse(List<String> messages, InlineKeyboardMarkup markup) {
        this(messages);
        setReplyKeyboardMarkup(markup);
    }
}
