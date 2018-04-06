package me.xiaoman.medicalassistant.ocr;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ocr测试接口
 *
 * @author pacman
 * @version 1.0
 * date: 2018/4/6 15:31
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmartOcrTest2 {
    //    private static final String filename = "C:\\Users\\xiaobao\\Desktop\\ocr\\test.png";
//    private static final String filename = "C:\\Users\\xiaobao\\Desktop\\ocr\\medical-diagnose.jpg";
    private static final String filename = "C:\\Users\\xiaobao\\Desktop\\ocr\\symptom.png";

    @Autowired
    private BaiduOcr baiduOcr;

    @Autowired
    private ZhiyunOcr zhiyunOcr;

    @Test
    public void testOcr() {
//        testBaiduOcr();
        System.out.println("------------------");
        testZhiyunOcr();
    }

    /**
     * {"result":"MR所见:膝关节MRI:右侧膝关节腔显示积液,前交叉韧带显示肿胀,信\n号混杂,边缘不规则,关节囊显示小灶性游离体征象,关节诸骨\n未见异常征象,内外侧半月板大小形态显示正常,其内显示异常\n信号灶。","elapsed":2029}
     */
    private void testBaiduOcr() {
        String result = baiduOcr.recognize(filename);
        System.out.println("baidu ocr: \n" + result);
    }

    /**
     * {"result":"MR所见: 膝关节MRI:右侧膝关节腔显示积液,前交叉韧带显示肿胀,信\n号混杂,边缘不规则,关节囊显示小灶性游离体征象,关节诸骨\n未见异常征象,内外侧半月板大小形态显示正常,其内显示异常\n信号灶。","elapsed":1212}
     */
    private void testZhiyunOcr() {
        String result = zhiyunOcr.recognize(filename);
        System.out.println("zhiyun ocr: " + result);
    }
}