package com.spy.apollo.mongodb;

import com.spy.apollo.mongodb.dao.UserDao;
import com.spy.apollo.mongodb.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-18 9:26
 * @since 1.0
 */
@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }


    @Autowired
    private UserDao userDao;

    @Override
    public void run(String... args) throws Exception {
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
