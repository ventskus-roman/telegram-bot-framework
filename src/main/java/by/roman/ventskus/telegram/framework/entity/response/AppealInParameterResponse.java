package by.roman.ventskus.telegram.framework.entity.response;

import by.roman.ventskus.telegram.framework.entity.Parameter;
import lombok.Data;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
@Data
public class AppealInParameterResponse extends OneMessageResponse {

    private String text;
    private Parameter requestedParameter;

    public AppealInParameterResponse(String text, Parameter requestedParameter) {
        this.text = text;
        this.requestedParameter = requestedParameter;
    }
}
