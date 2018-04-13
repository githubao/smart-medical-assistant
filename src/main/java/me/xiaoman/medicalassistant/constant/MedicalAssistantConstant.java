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
    public static String FILE_PATH = "d:/ocr/";
    public static String IMG_PATH = FILE_PATH + "img/";

    static {
        File file = new File(IMG_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }

    }
}
