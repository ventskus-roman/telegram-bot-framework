package by.roman.ventskus.telegram.framework.entity.response;

import by.roman.ventskus.telegram.framework.entity.Command;
import org.telegram.telegrambots.api.objects.ReplyKeyboardMarkup;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */

public interface Response {

    Command getCommand();
    ReplyKeyboardMarkup getReplyKeyboardMarkup();
}
