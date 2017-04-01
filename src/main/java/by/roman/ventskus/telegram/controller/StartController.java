package by.roman.ventskus.telegram.controller;

import by.roman.ventskus.telegram.framework.config.BotController;
import by.roman.ventskus.telegram.framework.config.Controller;
import by.roman.ventskus.telegram.framework.entity.Parameter;
import by.roman.ventskus.telegram.framework.entity.request.Request;
import by.roman.ventskus.telegram.framework.entity.response.Response;
import by.roman.ventskus.telegram.framework.entity.response.SingleMessageResponse;

import java.util.Map;

/**
 * Created by romanventskus on 11.03.17.
 */
@BotController(value = "start")
public class StartController implements Controller {

    @Override
    public Response process(Request request, Map<Parameter, Object> params) {
        return new SingleMessageResponse("Привет! Я постер-бот!");
    }
}
