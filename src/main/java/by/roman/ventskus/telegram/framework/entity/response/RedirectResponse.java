package by.roman.ventskus.telegram.framework.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by romanventskus on 11.04.17.
 */
@Data
@AllArgsConstructor
public class RedirectResponse extends Response {

    private String newCommand;
}
