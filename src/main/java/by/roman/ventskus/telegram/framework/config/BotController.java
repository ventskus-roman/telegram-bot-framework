package by.roman.ventskus.telegram.framework.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface BotController {

    String value();
}
