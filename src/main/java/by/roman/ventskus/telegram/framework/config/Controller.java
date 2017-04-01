package by.roman.ventskus.telegram.framework.config;

import by.roman.ventskus.telegram.framework.entity.Parameter;
import by.roman.ventskus.telegram.framework.entity.request.Request;
import by.roman.ventskus.telegram.framework.entity.response.Response;

import java.util.Map;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public interface Controller {

    Response process(Request request, Map<Parameter, Object> params);

}
