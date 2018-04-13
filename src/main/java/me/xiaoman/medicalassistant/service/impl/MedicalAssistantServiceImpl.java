package me.xiaoman.medicalassistant.service.impl;

import me.xiaoman.medicalassistant.domain.MedicalAssistant;
import me.xiaoman.medicalassistant.ocr.BaiduOcr;
import me.xiaoman.medicalassistant.ocr.ZhiyunOcr;
import me.xiaoman.medicalassistant.service.MedicalAssistantService;
import me.xiaoman.medicalassistant.util.ConfigUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 智能医药助理 实现类
 *
 * @author pacman
 * @version 1.0
 * date: 2018/4/12 20:56
 */

@Service
public class MedicalAssistantServiceImpl implements MedicalAssistantService {

    @Autowired
    private BaiduOcr baiduOcr;

    @Autowired
    private ZhiyunOcr zhiyunOcr;

    @Override
    public MedicalAssistant assist(String filename) {
        MedicalAssistant assistant = new MedicalAssistant(filename);

        ocr(assistant);

        nlp(assistant);

        return assistant;

    }

    private void ocr(MedicalAssistant assistant) {
        String filename = assistant.getPicFile();

        assistant.setBaiduOcr(baiduOcr.fake(filename));
        assistant.setZhiyunOcr(zhiyunOcr.fake(filename));
    }

    private void nlp(MedicalAssistant assistant) {

//        TODO 这里只使用了百度ocr的结果
        final String baiduAsr = assistant.getBaiduOcr();

        Map<String, String> explains = new HashMap<>();

        medicalProperMap.entrySet().stream()
                .filter(it -> baiduAsr.contains(it.getKey()))
                .forEach(it -> explains.put(it.getKey(), it.getValue()));

        assistant.setExplanations(explains);
    }

    private static Map<String, String> medicalProperMap = null;

    static {
        medicalProperMap = ConfigUtils.getMedicalProper();
    }
}
