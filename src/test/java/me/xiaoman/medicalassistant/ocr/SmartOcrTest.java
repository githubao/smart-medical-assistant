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
    //    private static final String filename = "C:\\Users\\xiaobao\\Desktop\\ocr\\test.png";
    private static final String filename = "C:\\Users\\xiaobao\\Desktop\\ocr\\medical-diagnose.jpg";
//    private static final String filename = "C:\\Users\\xiaobao\\Desktop\\ocr\\symptom.png";

    @Test
    public void testOcr() {
        testBaiduOcr();
        System.out.println("------------------\n");
        testZhiyunOcr();
    }

    private void testBaiduOcr() {
        String result = new BaiduOcr().recognize(filename);
        System.out.println("baidu ocr: \n" + result);
    }

    private void testZhiyunOcr() {
        String result = new ZhiyunOcr().recognize(filename);
        System.out.println("zhiyun ocr: \n" + result);
    }
}