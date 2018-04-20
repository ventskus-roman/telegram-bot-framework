package by.roman.ventskus.telegram.framework.entity.request;

import java.io.File;

import by.roman.ventskus.telegram.framework.entity.Command;
import by.roman.ventskus.telegram.framework.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
@Data
@AllArgsConstructor
public class Request {

    private User user;
    private String text;
    private Boolean isCommand;
    private Long forwardedChannelId;
    private String forwardedChannelName;
    private String forwardedChannelUsername;
    private File file;

    public Request(User user, String text, Boolean isCommand) {
        this.user = user;
        this.text = text;
        this.isCommand = isCommand;
    }

    public Command getCommand() {
        if (isCommand) {
            return new Command(text.replace("/", ""));
        }
        return null;
    }

}
