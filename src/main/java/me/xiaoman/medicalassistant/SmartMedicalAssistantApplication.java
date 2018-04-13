package me.xiaoman.medicalassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartMedicalAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartMedicalAssistantApplication.class, args);
    }
}
