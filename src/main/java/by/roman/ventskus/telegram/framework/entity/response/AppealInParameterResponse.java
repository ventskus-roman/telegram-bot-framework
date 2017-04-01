package by.roman.ventskus.telegram.framework.entity.response;

import by.roman.ventskus.telegram.framework.entity.Parameter;
import lombok.*;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class AppealInParameterResponse extends OneMessageResponse {

    private Parameter requestedParameter;

    public AppealInParameterResponse(String text, Parameter requestedParameter) {
        this.text = text;
        this.requestedParameter = requestedParameter;
    }

    public AppealInParameterResponse(String text, Parameter requestedParameter, InlineKeyboardMarkup markup) {
        this(text, requestedParameter);
        setReplyKeyboardMarkup(markup);
    }
}
