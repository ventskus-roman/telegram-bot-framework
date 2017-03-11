package by.roman.ventskus.telegram.controller;

import by.roman.ventskus.telegram.framework.config.BotController;
import by.roman.ventskus.telegram.framework.config.Controller;
import by.roman.ventskus.telegram.framework.entity.Parameter;
import by.roman.ventskus.telegram.framework.entity.request.Request;
import by.roman.ventskus.telegram.framework.entity.response.AppealInParameterResponse;
import by.roman.ventskus.telegram.framework.entity.response.Response;
import by.roman.ventskus.telegram.framework.entity.response.SingleMessageResponse;

import java.util.Map;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
//@BotController(value = "/test")
public class SimpleController implements Controller {

    private Parameter firstName = new Parameter("firstName");
    private Parameter secondName = new Parameter("secondName");

    @Override
    public Response process(Request request, Map<Parameter, String> params) {
        if (params.get(firstName) == null) {
            return new AppealInParameterResponse("What is your first name?", firstName);
        } else if (params.get(secondName) == null) {
            return new AppealInParameterResponse("What is your second name?", secondName);
        } else {
            return new SingleMessageResponse("Hello, " + params.get(firstName) + " " + params.get(secondName) + "!");
        }
    }
}
