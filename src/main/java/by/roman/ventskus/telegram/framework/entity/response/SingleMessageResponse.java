package by.roman.ventskus.telegram.framework.entity.response;

import by.roman.ventskus.telegram.framework.entity.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
@Data
@AllArgsConstructor
public class SingleMessageResponse implements OneMessageResponse {

    private String text;
    private Command command;
}
