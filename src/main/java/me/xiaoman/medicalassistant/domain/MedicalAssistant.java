package me.xiaoman.medicalassistant.domain;

import java.util.Map;

/**
 * 智能识别的结果
 *
 * @author pacman
 * @version 1.0
 * date: 2018/4/12 20:58
 */

public class MedicalAssistant {

    private String baiduOcr;

    private String zhiyunOcr;

    private Map<String,String> explations;

    public MedicalAssistant() {
    }

    public String getBaiduOcr() {
        return baiduOcr;
    }

    public void setBaiduOcr(String baiduOcr) {
        this.baiduOcr = baiduOcr;
    }

    public String getZhiyunOcr() {
        return zhiyunOcr;
    }

    public void setZhiyunOcr(String zhiyunOcr) {
        this.zhiyunOcr = zhiyunOcr;
    }

    public Map<String, String> getExplations() {
        return explations;
    }

    public void setExplations(Map<String, String> explations) {
        this.explations = explations;
    }
}
