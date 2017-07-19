package com.spy.apollo.mongodb.demo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.spy.apollo.mongodb.demo.common.Const;
import com.spy.apollo.test.AbstractApolloTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-19 9:51
 * @since 1.0
 */
@Slf4j
public abstract class BaseMongodbTest extends AbstractApolloTest {

    protected MongoClient   mongoClient;
    protected MongoDatabase mongoDb;


    @Before
    public void before() {
        mongoClient = new MongoClient(Const.HOST, Const.PORT);
        mongoDb = mongoClient.getDatabase(Const.DEFAULT_DB);
    }


    @After
    public void after() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
