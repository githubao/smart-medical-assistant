package me.xiaoman.medicalassistant.ocr;

/**
 * ocr 识别的接口
 *
 * @author pacman
 * @version 1.0
 * date: 2018/4/6 14:32
 */

public interface SmartOcr {
    String recognize(String filename) throws Exception;
}
