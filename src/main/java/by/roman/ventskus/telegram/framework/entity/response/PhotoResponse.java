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
public class PhotoResponse implements Response {
    private String fileName;
    private Command command;
    private User user;
    private ReplyKeyboardMarkup replyKeyboardMarkup;
}
