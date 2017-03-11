package by.roman.ventskus.telegram.framework.entity.response;

import lombok.Data;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
@Data
public class SingleMessageResponse extends OneMessageResponse {

    private String text;

    public SingleMessageResponse(String text) {
        this.text = text;
    }
}
