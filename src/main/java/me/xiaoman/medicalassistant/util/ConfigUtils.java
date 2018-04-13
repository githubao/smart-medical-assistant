package me.xiaoman.medicalassistant.util;

import me.xiaoman.medicalassistant.constant.MedicalAssistantConstant;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 从本地读取文件
 *
 * @author pacman
 * @version 1.0
 * date: 2018/4/6 14:55
 */

public class ConfigUtils {
    private static final Logger logger = LoggerFactory.getLogger(ConfigUtils.class);

    public static String getPassword(String filename) {
        String fullname = String.format("%s/%s.txt", MedicalAssistantConstant.ROOT_PATH, filename);
        try {
            return FileUtils.readFileToString(new File(fullname), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Map<String, String> getMedicalProper() {
        try {
            return doGetMedicalProper();
        } catch (IOException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    private static Map<String, String> doGetMedicalProper() throws IOException {
//        File file = ResourceUtils.getFile("classpath:medical_proper_nouns.properties");

        String filename = String.format("%s/%s.properties", MedicalAssistantConstant.FILE_PATH, "medical_proper_nouns");

        List<String> lines = FileUtils.readLines(new File(filename), "utf-8");

        HashMap<String, String> map = new HashMap<>();
        for (String line : lines) {
            String[] attr = line.split("=");
            map.put(attr[0], attr[1]);
        }
        return map;

    }
}
