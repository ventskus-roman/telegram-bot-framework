package by.roman.ventskus.telegram.framework.router;

import by.roman.ventskus.telegram.framework.config.BotController;
import by.roman.ventskus.telegram.framework.config.Controller;
import by.roman.ventskus.telegram.framework.exception.NoMappingFoundException;
import com.vdurmont.emoji.EmojiParser;
import org.reflections.Reflections;

import java.lang.annotation.IncompleteAnnotationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by romanventskus on 11.03.17.
 */
public class CommandRouter {

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

    public static Class<? extends Controller> getRoute(String text) {
        Class<? extends Controller> clazz = mapping.get(removeSlashAndEmoji(text));
        if (clazz == null) {
            throw new NoMappingFoundException("There is no mapping for command: " + text);
        } else {
            return clazz;
        }
    }

    public static boolean isRoute(String text) {
        return mapping.containsKey(removeSlashAndEmoji(text));
    }

    private static String removeSlashAndEmoji(String text) {
        String withoutSlash = text.replace("/", "");
        return EmojiParser.removeAllEmojis(withoutSlash);
    }
}
