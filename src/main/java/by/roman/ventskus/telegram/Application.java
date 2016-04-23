package by.roman.ventskus.telegram;

import by.roman.ventskus.telegram.framework.Framework;
import by.roman.ventskus.telegram.framework.entity.FrameworkParams;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public class Application {

    private static final String BOT_USERNAME = "username";
    private static final String BOT_TOKEN = "token";

    public static void main(String[] args) {
        Framework.init(new FrameworkParams(BOT_USERNAME, BOT_TOKEN));
    }
}
