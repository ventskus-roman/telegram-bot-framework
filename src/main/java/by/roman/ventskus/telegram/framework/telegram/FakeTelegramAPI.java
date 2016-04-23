package by.roman.ventskus.telegram.framework.telegram;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public class FakeTelegramAPI implements TelegramAPI {
    @Override
    public void send(String text) {
        System.out.println(text);
    }
}
