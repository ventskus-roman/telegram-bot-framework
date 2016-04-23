package by.roman.ventskus.telegram.framework.entity.response;

import by.roman.ventskus.telegram.framework.entity.User;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public interface OneMessageResponse extends Response {

    String getText();
    User getUser();
}
