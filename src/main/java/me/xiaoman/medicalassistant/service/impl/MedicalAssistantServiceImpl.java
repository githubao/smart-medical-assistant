package me.xiaoman.medicalassistant.service.impl;

import me.xiaoman.medicalassistant.domain.MedicalAssistant;
import me.xiaoman.medicalassistant.ocr.BaiduOcr;
import me.xiaoman.medicalassistant.ocr.SmartOcr;
import me.xiaoman.medicalassistant.ocr.ZhiyunOcr;
import me.xiaoman.medicalassistant.service.MedicalAssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        MedicalAssistant assistant = ocr(filename);

        nlp(assistant);

        return assistant;

    }


    private MedicalAssistant ocr(String filename) {
        MedicalAssistant assistant = new MedicalAssistant();

        assistant.setBaiduOcr(baiduOcr.recognize(filename));
        assistant.setZhiyunOcr(zhiyunOcr.recognize(filename));

        return assistant;
    }

    private void nlp(MedicalAssistant assistant) {
        return;
    }
}
