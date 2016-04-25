package by.roman.ventskus.telegram.framework.telegram.builder;

import org.telegram.telegrambots.api.objects.ReplyKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman Ventskus on 26.04.2016.
 */
public class ReplyKeyboardMarkupBuilder {

    private ReplyKeyboardMarkup markup;
    private List<List<String>> keyboard;

    public ReplyKeyboardMarkupBuilder() {
        markup = new ReplyKeyboardMarkup();
        keyboard = new ArrayList<>();
        markup.setKeyboard(keyboard);
    }

    public ReplyKeyboardMarkupBuilder addRow(String... buttons) {
        List<String> row = new ArrayList<>();
        if (buttons != null) {
            for (String button : buttons) {
                row.add(button);
            }
        }
        keyboard.add(row);
        return this;
    }

    public ReplyKeyboardMarkupBuilder oneTimeKeyboad() {
        markup.setOneTimeKeyboad(true);
        return this;
    }

    public ReplyKeyboardMarkupBuilder selective() {
        markup.setSelective(true);
        return this;
    }

    public ReplyKeyboardMarkupBuilder resizeKeyboard() {
        markup.setResizeKeyboard(true);
        return this;
    }

    public ReplyKeyboardMarkup build() {
        return markup;
    }
}
