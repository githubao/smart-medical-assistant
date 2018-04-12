package me.xiaoman.medicalassistant.service;

import me.xiaoman.medicalassistant.domain.MedicalAssistant;

/**
 * 服务层
 *
 * @author pacman
 * @version 1.0
 * date: 2018/4/12 20:54
 */


public interface MedicalAssistantService {

    MedicalAssistant assist(String filename);
}
