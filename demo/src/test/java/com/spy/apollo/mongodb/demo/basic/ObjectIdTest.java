package com.spy.apollo.mongodb.demo.basic;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.junit.Test;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-19 9:07
 * @since 1.0
 */
@Slf4j
public class ObjectIdTest {

    @Test
    public void run() {
        ObjectId objectId = ObjectId.get();

        log.debug("obj id={}", objectId);
    }

}
