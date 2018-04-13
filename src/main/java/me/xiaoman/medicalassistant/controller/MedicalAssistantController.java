package me.xiaoman.medicalassistant.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.xiaoman.medicalassistant.constant.MedicalAssistantConstant;
import me.xiaoman.medicalassistant.domain.MedicalAssistant;
import me.xiaoman.medicalassistant.service.MedicalAssistantService;
import me.xiaoman.medicalassistant.util.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
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
            return "redirect:success";
        }

        try {
            byte[] bytes = file.getBytes();
            String filename = buildFileName(file);
            Path path = Paths.get(filename);
            Files.write(path, bytes);

            MedicalAssistant assist = service.assist(filename);

            addAttribute(redirectAttributes, assist);

        } catch (IOException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }

        return "redirect:/success";
    }

    private String buildFileName(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String[] attr = originalFilename.split("\\.");
        String target = "." + attr[attr.length - 1];
        String replacement = "-" + System.currentTimeMillis() + target;
        String replacedFilename = originalFilename.replace(target, replacement);

        return MedicalAssistantConstant.IMG_PATH + replacedFilename;
    }

    private void addAttribute(RedirectAttributes redirectAttributes, MedicalAssistant assist) {
        JSONArray baiduOcr = JSONObject.parseArray(assist.getBaiduOcr());
        String baiduResult = StringUtils.join(baiduOcr, "\n");

        JSONArray zhiyunOcr = JSONObject.parseArray(assist.getZhiyunOcr());
        String zhiyunResult = StringUtils.join(zhiyunOcr, "\n---region--\n");

        String explanations = StringUtils.join(assist.getExplanations().entrySet(), "\n");

        String filename = new File(assist.getPicFile()).getName();

        redirectAttributes.addFlashAttribute("baiduOcr", baiduResult);
        redirectAttributes.addFlashAttribute("zhiyunOcr", zhiyunResult);
        redirectAttributes.addFlashAttribute("explanations", explanations);
        redirectAttributes.addFlashAttribute("picFile", "img/" + filename);
    }

    @GetMapping("/success")
    public String uploadStatus() {
        return "success";
    }
}
