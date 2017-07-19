package com.spy.apollo.mongodb.dao;

import com.spy.apollo.mongodb.BaseAppTest;
import com.spy.apollo.mongodb.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

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
    public void cleanAll() {
        userDao.deleteAll();
        log.debug("delete all suc");
    }

    @Test
    public void add() {
        for (int i = 0; i < 10; i++) {
            User user = new User("" + i, UUID.randomUUID().toString(), i + 10);
            userDao.save(user);
        }
        log.debug("add user suc..");
    }

    @Test
    public void add2() {
        User user = new User(UUID.randomUUID().toString(), 100);
        userDao.save(user);
    }


    @Test
    public void query() {
        log.debug("user={}", userDao.findByUsername("Alice"));
    }

    @Test
    public void queryAll() {
        userDao.findAll().forEach(System.out::println);
    }

    @Test
    public void delete() {
        userDao.delete(1L);
    }

    @Test
    public void update() {
    }


}
