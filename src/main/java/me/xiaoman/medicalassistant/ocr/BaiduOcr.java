package me.xiaoman.medicalassistant.ocr;

/**
 * 百度的ocr的接口
 *
 * @author pacman
 * @version 1.0
 * date: 2018/4/2 16:33
 */

public class BaiduOcr implements SmartOcr{
    private static final String URL = "http://openapi.youdao.com/ocrapi";

    private static final String APP_ID = "";
    private static final String APP_SECRET = "";


    @Override
    public String recognize(String filename) {
        return null;
    }
}
