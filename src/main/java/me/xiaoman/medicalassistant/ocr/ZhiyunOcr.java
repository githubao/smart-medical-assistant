package me.xiaoman.medicalassistant.ocr;

/**
 * 智云的ocr的接口
 *
 * @author pacman
 * @version 1.0
 * date: 2018/4/2 16:33
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.kevinsawicki.http.HttpRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.springframework.stereotype.Component;

@Component
public class ZhiyunOcr implements SmartOcr {
    private static Logger logger = LoggerFactory.getLogger(ZhiyunOcr.class);

    private static final String URL = "http://openapi.youdao.com/ocrapi";
    private static final String APP_KEY = "08d47c6d7eff013a";
    private static final String APP_SECRET = "eJfivFgH0FYOW7IriAyyQcoQ6gNPRqe1";

    @Override
    public String recognize(String filename) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        String img = getImageStr(filename);

        String salt = String.valueOf(System.currentTimeMillis());

        map.put("appKey", APP_KEY);
        map.put("img", img);
        map.put("detectType", "10012");
        map.put("imageType", "1");
        map.put("langType", "zh-en");
        map.put("salt", salt);
        map.put("docType", "json");
        String sign = DigestUtils.md5Hex(APP_KEY + img + salt + APP_SECRET);
        map.put("sign", sign);

//        return requestOCRForHttp(URL, map);
        return HttpRequest.post(URL).form(map).body();
    }

    private String requestOCRForHttp(String url, Map requestParams) throws Exception {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        /**HttpPost*/
        HttpPost httpPost = new HttpPost(url);
        List<BasicNameValuePair> params = new ArrayList<>();

        params.add(new BasicNameValuePair("appKey", requestParams.get("appKey").toString()));
        params.add(new BasicNameValuePair("img", requestParams.get("img").toString()));
        params.add(new BasicNameValuePair("detectType", requestParams.get("detectType").toString()));
        params.add(new BasicNameValuePair("imageType", requestParams.get("imageType").toString()));
        params.add(new BasicNameValuePair("langType", requestParams.get("langType").toString()));
        params.add(new BasicNameValuePair("salt", requestParams.get("salt").toString()));
        params.add(new BasicNameValuePair("sign", requestParams.get("sign").toString()));
        params.add(new BasicNameValuePair("docType", requestParams.get("docType").toString()));
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        /**HttpResponse*/
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        try {
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity, "utf-8");
            EntityUtils.consume(httpEntity);
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                logger.info("## release resouce error ##" + e);
            }
        }
        return result;
    }

    /**
     * 获得图片的Base64编码
     *
     * @param imgFile
     * @return
     */
    private String getImageStr(String imgFile) {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        return Base64.encode(data);//返回Base64编码过的字节数组字符串
    }

    /**
     * 生成32位MD5摘要
     *
     * @param string
     * @return
     */
    public static String md5(String string) {
        if (string == null) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput = string.getBytes();
        try {
            /** 获得MD5摘要算法的 MessageDigest 对象 */
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            /** 使用指定的字节更新摘要 */
            mdInst.update(btInput);
            /** 获得密文 */
            byte[] md = mdInst.digest();
            /** 把密文转换成十六进制的字符串形式 */
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
