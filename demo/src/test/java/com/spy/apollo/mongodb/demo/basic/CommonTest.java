package com.spy.apollo.mongodb.demo.basic;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.spy.apollo.mongodb.demo.BaseMongodbTest;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.junit.Test;

import java.util.Iterator;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-19 9:54
 * @since 1.0
 */
@Slf4j
public class CommonTest extends BaseMongodbTest {

    @Test
    public void run() {
        Iterator it = mongoClient.listDatabaseNames().iterator();

        while (it.hasNext()) {
            String dbName = (String) it.next();
            log.debug(dbName);
        }
    }

    @Test
    public void run30() {
        MongoCollection<Document> userDocs = mongoDb.getCollection("user");

        log.debug("count={}", userDocs.count());

        FindIterable tDocs = userDocs.find();
        log.debug("tdocs={}", tDocs);
    }

}
