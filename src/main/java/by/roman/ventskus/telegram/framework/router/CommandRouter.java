package by.roman.ventskus.telegram.framework.router;

import by.roman.ventskus.telegram.framework.config.BotController;
import by.roman.ventskus.telegram.framework.config.Controller;
import by.roman.ventskus.telegram.framework.exception.NoMappingFoundException;
import com.vdurmont.emoji.EmojiParser;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.lang.annotation.IncompleteAnnotationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by romanventskus on 11.03.17.
 */
public class CommandRouter {

    @Autowired
    private ApplicationContext applicationContext;

    private static final Map<String, Class<? extends Controller>> mapping = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, Controller> beans = applicationContext.getBeansOfType(Controller.class);
        if (beans != null) {
            beans.forEach((key, value) -> {
                BotController annotation = value.getClass().getDeclaredAnnotation(BotController.class);
                String mappingValue = annotation.value();
                mapping.put(mappingValue, value.getClass());
            });
        }
    }

    public Class<? extends Controller> getRoute(String text) {
        Class<? extends Controller> clazz = mapping.get(removeSlashAndEmoji(text));
        if (clazz == null) {
            throw new NoMappingFoundException("There is no mapping for command: " + text);
        } else {
            return clazz;
        }
    }

    public boolean isRoute(String text) {
        if (text == null) {
            return false;
        }
        return mapping.containsKey(removeSlashAndEmoji(text));
    }

    private String removeSlashAndEmoji(String text) {
        String withoutSlash = text.replace("/", "");
        return EmojiParser.removeAllEmojis(withoutSlash);
    }
}
