package by.roman.ventskus.telegram.framework.config;

import by.roman.ventskus.telegram.framework.entity.Command;
import by.roman.ventskus.telegram.framework.entity.Parameter;
import by.roman.ventskus.telegram.framework.entity.State;
import by.roman.ventskus.telegram.framework.entity.User;
import by.roman.ventskus.telegram.framework.entity.request.Request;
import by.roman.ventskus.telegram.framework.entity.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public class HistoryManager {

    private static HistoryManager instance;
    private final Map<User, State> stateMap = new HashMap<>();

    private HistoryManager() {}

    public static HistoryManager getInstance() {
        if (instance == null) {
            instance = new HistoryManager();
        }
        return instance;
    }


    public Map<Parameter, Object> getParams(Request request) {
        State state = getState(request.getUser());
        return Optional.ofNullable(state.getParams(request.getCommand())).orElse(new HashMap<>());
    }

    public void putParam(User user, Command command, Parameter parameter, String text) {
        State state = getState(user);
        state.getParams(command).put(parameter, text);
    }

    public Response getLastResponse(User user) {
        State state = getState(user);
        return state.getLastResponse();
    }

    public void registerResponse(User user, Response response) {
        State state = getState(user);
        state.setLastResponse(response);
    }

    private State getState(User user) {
        State state = stateMap.get(user);
        if (state == null) {
            state = new State();
            stateMap.put(user, state);
        }
        return state;
    }

    public void wipeCommandParams(Request request) {
        Command command = request.getCommand();
        if (command != null) {
            getState(request.getUser()).wipeParams(command);
        }

    }
}
