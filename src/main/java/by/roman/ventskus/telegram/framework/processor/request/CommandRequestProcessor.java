package by.roman.ventskus.telegram.framework.processor.request;

import by.roman.ventskus.telegram.framework.config.Controller;
import by.roman.ventskus.telegram.framework.config.HistoryManager;
import by.roman.ventskus.telegram.framework.entity.Parameter;
import by.roman.ventskus.telegram.framework.entity.request.Request;
import by.roman.ventskus.telegram.framework.entity.response.Response;
import by.roman.ventskus.telegram.framework.router.CommandRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public class CommandRequestProcessor implements RequestProcessor {


    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Response process(Request request) {
        Class<? extends Controller> clazz = CommandRouter.getRoute(request.getText());
        try {
            Map<Parameter, Object> params = HistoryManager.getInstance().getParams(request);
            Controller controller = getController(clazz);
            Response response = controller.process(request, params);
            HistoryManager.getInstance().registerResponse(request.getUser(), response);
            return response;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Controller getController(Class<? extends Controller> clazz) throws IllegalAccessException, InstantiationException {
        if (applicationContext == null) {
            return clazz.newInstance();
        } else {
            return applicationContext.getBean(clazz);
        }
    }
}
