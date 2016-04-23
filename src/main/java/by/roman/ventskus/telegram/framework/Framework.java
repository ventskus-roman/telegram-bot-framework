package by.roman.ventskus.telegram.framework;

import by.roman.ventskus.telegram.framework.config.HistoryManager;
import by.roman.ventskus.telegram.framework.entity.Command;
import by.roman.ventskus.telegram.framework.entity.FrameworkParams;
import by.roman.ventskus.telegram.framework.entity.request.Request;
import by.roman.ventskus.telegram.framework.entity.response.Response;
import by.roman.ventskus.telegram.framework.processor.request.CommandRequestProcessor;
import by.roman.ventskus.telegram.framework.processor.request.RequestProcessor;
import by.roman.ventskus.telegram.framework.processor.request.TextRequestProcessor;
import by.roman.ventskus.telegram.framework.telegram.TelegramRealApi;
import by.roman.ventskus.telegram.framework.telegram.send.Sender;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public abstract class Framework {


    private static final String COMMAND_PREFIX = "/";
    private static Framework instance;
    private RequestProcessor commandProcessor = new CommandRequestProcessor();
    private RequestProcessor textProcessor = new TextRequestProcessor();
    private Sender sender;

    public static Framework getInstance() {
        if (instance == null) {
            throw new RuntimeException("You should call metod init before");
        }
        return instance;
    }

    public static void init(FrameworkParams params) {
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
            };
            instance = new Framework(telegramRealApi) {
            };
        } else {
            throw new RuntimeException("Framework already initialized!");
        }
    }

    private Framework(TelegramRealApi telegramAPI) {
        sender = new Sender(telegramAPI);
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(telegramAPI);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
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
        return response;
    }


    private boolean isCommand(String text) {
        return text.startsWith(COMMAND_PREFIX);
    }

    public Response redirectToCommand(Request request, Command command) {
        request.setText(COMMAND_PREFIX + command.getText());
        return buildResponse(request);
    }
}
