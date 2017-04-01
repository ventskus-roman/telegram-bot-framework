package by.roman.ventskus.telegram.framework.telegram.builder;

import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romanventskus on 12.03.17.
 */
public class InlineKeyboarMarkupBuilder {

    private List<List<InlineKeyboardButton>> rows;
    InlineKeyboardMarkup markup;

    public InlineKeyboarMarkupBuilder() {
        rows = new ArrayList<>();
        markup = new InlineKeyboardMarkup();
        markup.setKeyboard(rows);
    }

    public InlineKeyboarMarkupBuilder addRow(InlineKeyboardButton...buttons) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        for (InlineKeyboardButton button : buttons) {
            row.add(button);
        }
        rows.add(row);
        return this;
    }

    public InlineKeyboarMarkupBuilder addRowWithOneButton(String text, String data) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(data);
        addRow(button);
        return this;
    }

    public InlineKeyboarMarkupBuilder addRowWithTwoButtons(String firstButtonText, String firstButtonData,
                                                           String secondButtonText, String secondButtonData) {
        InlineKeyboardButton firstButton = new InlineKeyboardButton();
        firstButton.setText(firstButtonText);
        firstButton.setCallbackData(firstButtonData);

        InlineKeyboardButton secondButton = new InlineKeyboardButton();
        secondButton.setText(secondButtonText);
        secondButton.setCallbackData(secondButtonData);

        addRow(firstButton, secondButton);
        return this;
    }

    public InlineKeyboardMarkup build() {
        return markup;
    }

}
