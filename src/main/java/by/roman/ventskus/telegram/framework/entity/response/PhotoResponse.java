package by.roman.ventskus.telegram.framework.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.InputStream;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
@Data
@AllArgsConstructor
public class PhotoResponse extends Response {
    private String fileName;
    private InputStream inputStream;

    @Override
    public String toString() {
        return fileName;
    }
}
