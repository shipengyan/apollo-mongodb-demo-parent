package com.spy.apollo.mongodb.dao;

import com.spy.apollo.mongodb.BaseAppTest;
import com.spy.apollo.mongodb.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-18 9:45
 * @since 1.0
 */
@Slf4j
public class UserDaoTest extends BaseAppTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void add() {
        User user = new User();
        user.setId(3L).setUsername("shipengyan").setAge(10);

        userDao.save(user);
    }


    @Test
    public void all() {
        userDao.deleteAll();

        userDao.save(new User(1L, "Alice", 20));
        userDao.save(new User(2L, "Bob", 33));

        // fetch all users
        log.debug("Customers found with findAll():");
        log.debug("-------------------------------");

        userDao.findAll().forEach(System.out::println);


        // fetch an individual user
        log.debug("Customer found with name('Alice'):");
        log.debug("--------------------------------");
        log.debug("user={}", userDao.findByUsername("Alice"));
    }

}
