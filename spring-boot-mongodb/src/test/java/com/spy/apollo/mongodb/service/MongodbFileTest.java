package com.spy.apollo.mongodb.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.spy.apollo.mongodb.BaseAppTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 文件存储
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-18 11:25
 * @since 1.0
 */
@Slf4j
public class MongodbFileTest extends BaseAppTest {


    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Test
    public void saveFile() throws FileNotFoundException {

        File   file     = new File("c:/test.txt");
        String filename = UUID.randomUUID().toString();

        DBObject meta = new BasicDBObject();
        meta.put("description", " this is spy test file");

        String mimeType = "text/plain";

        GridFSFile gridFSFile = gridFsTemplate.store(new FileInputStream(file), filename, mimeType, meta);
        log.debug("gridFSFile={}", gridFSFile);
    }

    @Test
    public void saveFiles() throws FileNotFoundException {
        for (int i = 0; i < 20; i++) {
            saveFile();
        }
    }


    @Test
    public void queryFile() throws IOException {
        // _id 是files_id
        GridFSDBFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is("596d801496062108e054ed7d")));

        log.debug("file={}", file);

        // file.getInputStream();

        String filename = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");
        file.writeTo(new File("c:/test." + filename + ".txt"));
        log.debug("write file suc.");
    }

    /**
     * 模糊查询，含有9的文件
     */
    @Test
    public void queryLikeFile() {
        GridFsResource[] gridFsResource = gridFsTemplate.getResources("9*");

        log.debug("gridFsResource={}", gridFsResource.length);

        Arrays.stream(gridFsResource).forEach(gridFsResource1 -> {
            log.debug("res name={}", gridFsResource1.getFilename());
        });

    }

    @Test
    public void deleteFile() {
        gridFsTemplate.delete(new Query(Criteria.where("_id").is("596d801496062108e054ed7d")));
    }


    @Test
    public void queryByPageable() {
        for (int i = 0; i < 100; i++) {
            Pageable pageable = new PageRequest(i, 5);

            Query query = new Query().with(pageable);

            //TODO
            // java.lang.ClassCastException:
            // com.mongodb.BasicDBObject cannot be cast to com.mongodb.gridfs.GridFSDBFile
//            List<GridFSDBFile> files = mongoTemplate.find(query, GridFSDBFile.class, "fs.files");
            List<BasicDBObject> files = mongoTemplate.find(query, BasicDBObject.class, "fs.files");


            if (CollectionUtils.isEmpty(files)) {
                log.debug("query over! loop={}", i);
                break;
            } else {
                files.stream().forEach(file -> {
                    log.debug("id={},filename={}", file.get("_id"), file.get("filename"));
                });
            }
        }


    }


    @Test
    public void deleteAll2() {
        //根据上面查询的结果，逐个删除
    }


    @Test
    @Deprecated
    public void deleteAll() {
        Integer totalCount = gridFsTemplate.find(new Query()).size();

        log.debug("totalCount={}", totalCount);

        for (int i = 1; i < 100; i++) {
            Pageable pageable = new PageRequest(i, 2);
            Query    query    = new Query().with(pageable);

            // 不起作用，原生的driver没有开放接口
            List<GridFSDBFile> files = gridFsTemplate.find(query);
            if (CollectionUtils.isEmpty(files)) {
                break;
            } else {
                files.stream().forEach(file -> {
                    log.debug("file name={}", file.getFilename());
                });
            }

        }


    }


}
