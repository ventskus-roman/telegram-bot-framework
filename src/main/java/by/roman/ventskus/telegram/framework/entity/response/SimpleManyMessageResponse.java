package by.roman.ventskus.telegram.framework.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by Roman Ventskus on 24.04.2016.
 */
@Data
public class SimpleManyMessageResponse extends ManyMessagesResponse {
    private List<String> messages;

    public SimpleManyMessageResponse(List<String> messages) {
        this.messages = messages;
    }
}
