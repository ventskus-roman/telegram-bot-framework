package by.roman.ventskus.telegram.framework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Roman Ventskus on 23.04.2016.
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(exclude = "username")
public class User {

    private Long id;
    private String username;

    public User(Long id) {
        this.id = id;
    }


}
