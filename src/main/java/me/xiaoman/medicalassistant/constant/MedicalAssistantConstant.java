package me.xiaoman.medicalassistant.constant;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 * @author pacman
 * @version 1.0
 * date: 2018/4/12 21:09
 */

public class MedicalAssistantConstant {
    //    public static final String ROOT_PATH = "C:\\Users\\xiaobao\\Desktop\\ocr\\";
    public static String ROOT_PATH;

    public static String FILE_PATH = "d:/ocr/";
    public static String IMG_PATH = FILE_PATH + "img/";

    static {
        File file = new File(IMG_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            ROOT_PATH = new ClassPathResource("file").getFile().getAbsolutePath();
//            FILE_PATH = ROOT_PATH;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
