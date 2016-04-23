package by.roman.ventskus.telegram.framework.entity.response;

import by.roman.ventskus.telegram.framework.entity.User;

import java.util.List;

/**
 * Created by Roman Ventskus on 24.04.2016.
 */
public interface ManyMessagesResponse extends Response {
    List<String> getMessages();
    User getUser();
}
