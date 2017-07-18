package com.spy.apollo.mongodb.service;

import com.spy.apollo.mongodb.BaseAppTest;
import com.spy.apollo.mongodb.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-18 15:27
 * @since 1.0
 */
@Slf4j
public class UserServiceTest extends BaseAppTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void updateTest() {

        mongoTemplate.updateFirst(Query.query(Criteria.where("username").is("3ee6d05b-ce18-4ead-aeca-62758ccd6fe7")),
            Update.update("age", 99),
            User.class
        );

    }
}
