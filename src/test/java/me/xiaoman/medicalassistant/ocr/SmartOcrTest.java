package me.xiaoman.medicalassistant.ocr;


import org.junit.Test;

/**
 * ocr测试接口
 *
 * @author pacman
 * @version 1.0
 * date: 2018/4/6 15:31
 */

public class SmartOcrTest {

    @Test
    public void testOcr() throws Exception {
        String filename = "C:\\Users\\xiaobao\\Desktop\\1.png";

        String result = new ZhiyunOcr().recognize(filename);

        System.out.println(result);

    }
}