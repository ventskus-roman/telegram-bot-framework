package by.roman.ventskus.telegram.framework.entity.response;

import by.roman.ventskus.telegram.framework.entity.Command;
import by.roman.ventskus.telegram.framework.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.telegram.telegrambots.api.objects.ReplyKeyboardMarkup;

import java.util.List;

/**
 * Created by Roman Ventskus on 24.04.2016.
 */
@Data
@AllArgsConstructor
public class SimpleManyMessageResponse implements ManyMessagesResponse {
    private List<String> messages;
    private Command command;
    private User user;
    private ReplyKeyboardMarkup replyKeyboardMarkup;

    public SimpleManyMessageResponse(List<String> messages, Command command, User user) {
        this.messages = messages;
        this.command = command;
        this.user = user;
    }
}
