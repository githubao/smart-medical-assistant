package me.xiaoman.medicalassistant.support;

import me.xiaoman.medicalassistant.service.impl.MedicalAssistantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时更新任务 每隔5分钟更新一次数据文件
 *
 * @author pacman
 * @version 1.0
 * date: 2018/4/13 15:03
 */

@Component
public class TimedTask {

    @Autowired
    private MedicalAssistantServiceImpl service;

    @Scheduled(fixedDelay = 300000)
    public void updateMap() {
        service.updateMap();
    }
}
