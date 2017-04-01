package by.roman.ventskus.telegram.framework.entity.response;

import by.roman.ventskus.telegram.framework.telegram.builder.InlineKeyboarMarkupBuilder;
import lombok.*;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class SingleMessageResponse extends OneMessageResponse {

    public SingleMessageResponse(String text) {
        this.text = text;
    }

    public SingleMessageResponse(String text, InlineKeyboardMarkup markup) {
        this(text);
        setReplyKeyboardMarkup(markup);
    }

    public SingleMessageResponse(String text, String buttonText, String buttonCommand) {
        this(text);
        InlineKeyboarMarkupBuilder builder = new InlineKeyboarMarkupBuilder();
        InlineKeyboardMarkup markup = builder.addRowWithOneButton(buttonText, buttonCommand).build();
        setReplyKeyboardMarkup(markup);
    }
}
