package by.roman.ventskus.telegram.framework.entity.response;

import by.roman.ventskus.telegram.framework.entity.Command;
import by.roman.ventskus.telegram.framework.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */

@Getter
@Setter
public abstract class Response {

    protected Command command;
    protected ReplyKeyboard replyKeyboardMarkup;
    protected User user;
    protected boolean enableMarkdown = true;
    protected boolean enableWebPreview = true;
}
