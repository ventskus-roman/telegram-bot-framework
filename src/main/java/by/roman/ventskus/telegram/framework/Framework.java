package by.roman.ventskus.telegram.framework;

import by.roman.ventskus.telegram.framework.config.HistoryManager;
import by.roman.ventskus.telegram.framework.entity.Command;
import by.roman.ventskus.telegram.framework.entity.FrameworkParams;
import by.roman.ventskus.telegram.framework.entity.User;
import by.roman.ventskus.telegram.framework.entity.request.Request;
import by.roman.ventskus.telegram.framework.entity.response.RedirectResponse;
import by.roman.ventskus.telegram.framework.entity.response.Response;
import by.roman.ventskus.telegram.framework.processor.request.CommandRequestProcessor;
import by.roman.ventskus.telegram.framework.processor.request.RequestProcessor;
import by.roman.ventskus.telegram.framework.processor.request.TextRequestProcessor;
import by.roman.ventskus.telegram.framework.router.CommandRouter;
import by.roman.ventskus.telegram.framework.telegram.TelegramRealApi;
import by.roman.ventskus.telegram.framework.telegram.send.Sender;
import com.vdurmont.emoji.EmojiLoader;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;
import lombok.Getter;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public class Framework {

    static {
        ApiContextInitializer.init();
    }

    private static Framework instance;

    @Autowired
    private CommandRequestProcessor commandProcessor;

    @Autowired
    private TextRequestProcessor textProcessor;

    @Autowired
    private CommandRouter commandRouter;

    @Getter
    private Sender sender;

    public static Framework getInstance() {
        if (instance == null) {
            throw new RuntimeException("You should call metod init before");
        }
        return instance;
    }

    public Framework(FrameworkParams params) {
        if (instance == null) {
            TelegramRealApi telegramRealApi = new TelegramRealApi() {
                @Override
                public String getBotUsername() {
                    return params.getBotUsername();
                }

                @Override
                public String getBotToken() {
                    return params.getBotToken();
                }

                @Override
                public CommandRouter getCommandRouter() {
                    return commandRouter;
                }
            };
            telegramRealApi.setFramework(this);

            sender = new Sender(telegramRealApi);
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            try {
                telegramBotsApi.registerBot(telegramRealApi);
            } catch (TelegramApiRequestException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Framework already initialized!");
        }
    }

    public void process(Request request) {
        HistoryManager.getInstance().wipeCommandParams(request);
        Response response = buildResponse(request);
        sender.send(response);
    }

    private Response buildResponse(Request request) {
        Response response = null;
        if (isCommand(request.getText())) {
            response = commandProcessor.process(request);
        } else {
            response = textProcessor.process(request);
        }
        response.setUser(request.getUser());
        response.setCommand(request.getCommand());
        if (response instanceof RedirectResponse) {
            return buildResponse(new Request(response.getUser(), ((RedirectResponse) response).getNewCommand(), true));
        }
        return response;
    }


    private boolean isCommand(String text) {
        return commandRouter.isRoute(text);
    }

    public Response redirectToCommand(Request request, Command command) {
        request.setText(command.getText());
        request.setIsCommand(commandRouter.isRoute(command.getText()));
        return buildResponse(request);
    }
}
