package by.roman.ventskus.telegram.framework.entity.response;

import by.roman.ventskus.telegram.framework.entity.Command;
import by.roman.ventskus.telegram.framework.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

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
}
