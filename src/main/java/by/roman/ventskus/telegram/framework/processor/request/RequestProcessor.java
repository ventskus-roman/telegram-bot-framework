package by.roman.ventskus.telegram.framework.processor.request;

import by.roman.ventskus.telegram.framework.entity.request.Request;
import by.roman.ventskus.telegram.framework.entity.response.Response;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
public interface RequestProcessor {

    Response process(Request request);

}
