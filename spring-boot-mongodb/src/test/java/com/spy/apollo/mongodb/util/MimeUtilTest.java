package com.spy.apollo.mongodb.util;

import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-18 13:35
 * @since 1.0
 */
@Slf4j
public class MimeUtilTest {


    @Test
    public void run2() throws IOException {
        getContentType2("c:/test.txt");
        getContentType2("c:/superman-console.png");
        getContentType2("c:/a.txt");
        getContentType2("c:/cityData.json");
    }

    @Test
    public void run3() throws IOException {
        getContentType3("c:/test.txt");
        getContentType3("c:/superman-console.png");
        getContentType3("c:/a.txt");
        getContentType3("c:/cityData.json");
    }


    private void getContentType2(final String filePath) throws IOException {
        ContentInfoUtil util = new ContentInfoUtil();
        ContentInfo     info = util.findMatch(filePath);
        if (info == null) {
            log.debug("content type is null");
        } else {
            log.debug("contentType={}", info.getMimeType());
        }
    }

    private void getContentType3(final String filePath) {
        File file = new File(filePath);

        String contentType = new MimetypesFileTypeMap().getContentType(file);
        log.debug("contentType={}", contentType);
    }


}
