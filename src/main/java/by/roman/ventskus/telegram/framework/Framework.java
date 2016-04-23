package by.roman.ventskus.telegram.framework;

import by.roman.ventskus.telegram.framework.config.HistoryManager;
import by.roman.ventskus.telegram.framework.entity.Command;
import by.roman.ventskus.telegram.framework.entity.request.Request;
import by.roman.ventskus.telegram.framework.entity.response.Response;
import by.roman.ventskus.telegram.framework.processor.request.CommandRequestProcessor;
import by.roman.ventskus.telegram.framework.processor.request.RequestProcessor;
import by.roman.ventskus.telegram.framework.processor.request.TextRequestProcessor;
import by.roman.ventskus.telegram.framework.telegram.FakeTelegramAPI;
import by.roman.ventskus.telegram.framework.telegram.TelegramAPI;
import by.roman.ventskus.telegram.framework.telegram.send.Sender;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public class Framework {


    private static final String COMMAND_PREFIX = "/";
    private static Framework instance;
    private RequestProcessor commandProcessor = new CommandRequestProcessor();
    private RequestProcessor textProcessor = new TextRequestProcessor();
    private TelegramAPI telegramAPI = new FakeTelegramAPI();
    private Sender sender = new Sender(telegramAPI);

    public static Framework getInstance() {
        if (instance == null) {
            instance = new Framework();
        }
        return instance;
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
