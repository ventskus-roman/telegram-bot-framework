package by.roman.ventskus.telegram.framework.processor.request;

import by.roman.ventskus.telegram.framework.config.Controller;
import by.roman.ventskus.telegram.framework.config.HistoryManager;
import by.roman.ventskus.telegram.framework.entity.Parameter;
import by.roman.ventskus.telegram.framework.entity.request.Request;
import by.roman.ventskus.telegram.framework.entity.response.Response;
import by.roman.ventskus.telegram.framework.router.CommandRouter;

import java.util.Map;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public class CommandRequestProcessor implements RequestProcessor {

    @Override
    public Response process(Request request) {
        Class<? extends Controller> clazz = CommandRouter.getRoute(request.getText());
        try {
            Map<Parameter, String> params = HistoryManager.getInstance().getParams(request);
            Response response = clazz.newInstance().process(request, params);
            HistoryManager.getInstance().registerResponse(request.getUser(), response);
            return response;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
