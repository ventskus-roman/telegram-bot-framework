package by.roman.ventskus.telegram.framework.entity;

import by.roman.ventskus.telegram.framework.entity.response.Response;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
@Data
public class State {

    private final Map<Command, Map<Parameter, Object>> commandStates = new HashMap<>();
    private Response lastResponse;

    public Map<Parameter, Object> getParams(Command command) {
        Map<Parameter, Object> params = commandStates.get(command);
        if (params == null) {
            params = new HashMap<>();
            commandStates.put(command, params);
        }
        return params;
    }

    public void wipeParams(Command command) {
        commandStates.remove(command);
    }

}
