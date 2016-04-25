package by.roman.ventskus.telegram.framework.entity.response;

import by.roman.ventskus.telegram.framework.entity.Command;
import by.roman.ventskus.telegram.framework.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.telegram.telegrambots.api.objects.ReplyKeyboardMarkup;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
@Data
@AllArgsConstructor
public class SingleMessageResponse implements OneMessageResponse {

    private String text;
    private Command command;
    private User user;
    private ReplyKeyboardMarkup replyKeyboardMarkup;

    public SingleMessageResponse(String text, Command command, User user) {
        this.text = text;
        this.command = command;
        this.user = user;
    }
}
