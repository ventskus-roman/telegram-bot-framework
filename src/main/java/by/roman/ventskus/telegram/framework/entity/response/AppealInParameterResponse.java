package by.roman.ventskus.telegram.framework.entity.response;

import by.roman.ventskus.telegram.framework.entity.Command;
import by.roman.ventskus.telegram.framework.entity.Parameter;
import by.roman.ventskus.telegram.framework.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.telegram.telegrambots.api.objects.ReplyKeyboardMarkup;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
@Data
@AllArgsConstructor
public class AppealInParameterResponse implements OneMessageResponse {

    private String text;
    private Parameter requestedParameter;
    private Command command;
    private User user;
    private ReplyKeyboardMarkup replyKeyboardMarkup;

    public AppealInParameterResponse(String text, Parameter requestedParameter, Command command, User user) {
        this.text = text;
        this.requestedParameter = requestedParameter;
        this.command = command;
        this.user = user;
    }
}
