package by.roman.ventskus.telegram.framework.entity.response;

import by.roman.ventskus.telegram.framework.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Roman Ventskus on 24.04.2016.
 */
@Getter
@Setter
public abstract class ManyMessagesResponse extends Response {
    protected  List<String> messages;
}
