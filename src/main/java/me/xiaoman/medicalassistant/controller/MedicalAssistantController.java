package me.xiaoman.medicalassistant.controller;

import me.xiaoman.medicalassistant.domain.MedicalAssistant;
import me.xiaoman.medicalassistant.service.MedicalAssistantService;
import me.xiaoman.medicalassistant.util.JsonParser;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 页面的控制类
 *
 * @author pacman
 * @version 1.0
 * date: 2018/4/12 20:50
 */

@Controller
public class MedicalAssistantController {
    private static Logger logger = LoggerFactory.getLogger(MedicalAssistantController.class);

    private static String UPLOADED_FOLDER = "D://temp//";

    @Autowired
    private MedicalAssistantService service;

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            byte[] bytes = file.getBytes();
            String filename = UPLOADED_FOLDER + file.getOriginalFilename();
            Path path = Paths.get(filename);
            Files.write(path, bytes);

            MedicalAssistant assist = service.assist(filename);

            redirectAttributes.addFlashAttribute("message", JsonParser.toJson(assist));

        } catch (IOException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
}
