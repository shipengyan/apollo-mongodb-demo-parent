package com.spy.apollo.mongodb.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-18 9:18
 * @since 1.0
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@Document
public class User {
    @Id
    private String  id;
    private String  username;
    private Integer age;


    public User(String id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

}
