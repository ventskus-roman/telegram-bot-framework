package by.roman.ventskus.telegram;

import by.roman.ventskus.telegram.framework.Framework;
import by.roman.ventskus.telegram.framework.entity.FrameworkParams;
import lombok.extern.log4j.Log4j;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public class Application {

    private static final String BOT_USERNAME = "PostogramBot";
    private static final String BOT_TOKEN = "";

    public static void main(String[] args) {
        Framework.init(new FrameworkParams(BOT_USERNAME, BOT_TOKEN));
    }
}
