package by.roman.ventskus.telegram.framework.entity.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
@Getter
@Setter
public abstract class OneMessageResponse extends Response {
    protected String text;
}
