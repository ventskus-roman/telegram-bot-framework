package by.roman.ventskus.telegram.framework.processor.request;

import by.roman.ventskus.telegram.exception.NoMappingFoundException;
import by.roman.ventskus.telegram.framework.config.BotController;
import by.roman.ventskus.telegram.framework.config.Controller;
import by.roman.ventskus.telegram.framework.config.HistoryManager;
import by.roman.ventskus.telegram.framework.entity.Parameter;
import by.roman.ventskus.telegram.framework.entity.request.Request;
import by.roman.ventskus.telegram.framework.entity.response.Response;
import org.reflections.Reflections;

import java.lang.annotation.IncompleteAnnotationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public class CommandRequestProcessor implements RequestProcessor {

    private static final Map<String, Class<? extends Controller>> mapping = new HashMap<>();

    static {
        Reflections reflections = new Reflections();
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(BotController.class);
        if (annotated != null) {
            annotated.forEach(clazz -> {
                if (!Controller.class.isAssignableFrom(clazz)) {
                    throw new IncompleteAnnotationException(BotController.class, "You can use BotController anotation only for classes, that implements Controller interface");
                }
                BotController annotation = clazz.getAnnotation(BotController.class);
                String value = annotation.value();
                mapping.put(value, (Class<? extends Controller>) clazz);
            });
        }
    }

    @Override
    public Response process(Request request) {
        Class<? extends Controller> clazz = mapping.get(request.getText());
        try {
            if (clazz == null) {
                throw new NoMappingFoundException("There is no mapping for command: " + request.getText());
            }
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
