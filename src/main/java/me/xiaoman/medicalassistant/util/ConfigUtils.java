package me.xiaoman.medicalassistant.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 从本地读取文件
 *
 * @author pacman
 * @version 1.0
 * date: 2018/4/6 14:55
 */

public class ConfigUtils {

    private static final String ROOT_PATH = "C:\\Users\\xiaobao\\Desktop\\ocr";

    public static String getPassword(String filename) {
        String fullname = String.format("%s/%s.txt", ROOT_PATH, filename);
        try {
            return FileUtils.readFileToString(new File(fullname), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
