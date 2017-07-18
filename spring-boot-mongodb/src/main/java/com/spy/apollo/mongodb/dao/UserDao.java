package com.spy.apollo.mongodb.dao;

import com.spy.apollo.mongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-18 9:19
 * @since 1.0
 */
public interface UserDao extends MongoRepository<User, Long> {

    User findByUsername(String username);
}
