package com.spy.apollo.mongodb.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-18 9:18
 * @since 1.0
 */
@Data
@Accessors(chain = true)
public class User {
    @Id
    private Long    id;
    private String  username;
    private Integer age;

    public User(Long id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }
}
