package me.xiaoman.medicalassistant.ocr;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.ocr.AipOcr;
import me.xiaoman.medicalassistant.util.ConfigUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 百度的ocr的接口
 *
 * @author pacman
 * @version 1.0
 * date: 2018/4/2 16:33
 */

@Component
public class BaiduOcr implements SmartOcr {

    private static final String APP_ID = "11028921";
    private static final String API_KEY = "DAM8uFr0GlTpSK5CClP69IG0";
    private static final String SECRET_KEY = ConfigUtils.getPassword("baidu-ocr");

    private AipOcr client;

    @PostConstruct
    private void init() {
        client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
    }

    @Override
    public String recognize(String filename) {

        // TODO 使用spring-boot 之后，也就是上线之前，把这一行注释掉
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        JSONObject response = client.basicGeneral(filename, new HashMap<String,String>());
        return parse(response);
//        return response.toString();
    }

    private String parse(JSONObject root) {
        JSONArray results = root.getJSONArray("words_result");

        List<String> texts = new ArrayList<>();
        for (Object result : results) {
            com.alibaba.fastjson.JSONObject json = JSON.parseObject(String.valueOf(result));
            texts.add(json.getString("words"));
        }
        return StringUtils.join(texts, "\n");
    }
}
