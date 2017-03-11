package by.roman.ventskus.telegram.framework.processor.request;

import by.roman.ventskus.telegram.framework.Framework;
import by.roman.ventskus.telegram.framework.config.HistoryManager;
import by.roman.ventskus.telegram.framework.entity.Command;
import by.roman.ventskus.telegram.framework.entity.Parameter;
import by.roman.ventskus.telegram.framework.entity.request.Request;
import by.roman.ventskus.telegram.framework.entity.response.AppealInParameterResponse;
import by.roman.ventskus.telegram.framework.entity.response.Response;
import by.roman.ventskus.telegram.framework.entity.response.SingleMessageResponse;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public class TextRequestProcessor implements RequestProcessor {
    @Override
    public Response process(Request request) {
        HistoryManager historyManager = HistoryManager.getInstance();
        Response response = historyManager.getLastResponse(request.getUser());
        if (response != null) {
            if (response instanceof AppealInParameterResponse) {
                AppealInParameterResponse appeal = (AppealInParameterResponse) response;
                Parameter parameter = appeal.getRequestedParameter();
                Command command = appeal.getCommand();
                historyManager.putParam(request.getUser(), command, parameter, request.getText());
                return Framework.getInstance().redirectToCommand(request, command);
            } else {
                Command command = response.getCommand();
                if (command != null) {
                    return Framework.getInstance().redirectToCommand(request, command);
                }
            }
        }
        return new SingleMessageResponse("I can`t understand you, sorry. Lets see commands list");
    }
}
