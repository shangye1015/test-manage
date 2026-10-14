package net.shangye.manage.test.mobilepush.push_api.tuibian;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.shangye.manage.test.mobilepush.push_api.dto.Message;
import net.shangye.manage.test.mobilepush.utils.HMACSHA256Util;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Base64;

@Slf4j
public class TestTuiBiAn {
    public static void main(String[] args) {
        String token = getToken("2c8209c35aa04be69da75d5d1f6432e9",
                "7efcc5a53d3e46b1a7e8983fa9fcef04", "https://push.tuibianchina.com/v1/token");

        System.out.println("token:" + token);
        String audit = getAudit("测试oppo推送bbbb", "推送内容推送内容审核bbbb", token, "https://push.tuibianchina.com/v1/audit");
        System.out.println(audit);
    }


    // accessKeyId 从appId里面拿
    public static String getAuditResult(String accessKeyId, String accessKeySecret,
                                        String tokenUrl, String auditUrl, Message message) {
        String token = getToken(accessKeyId, accessKeySecret, tokenUrl);
        System.out.println("token:" + token);
        String audit = getAudit("测试oppo推送bbbb", "推送内容推送内容审核bbbb", token, "https://push.tuibianchina.com/v1/audit");
        System.out.println(audit);
        return audit;
    }

    // 获取token
    public static String getToken(String accessKeyId, String accessKeySecret, String url) {
        String result = null;
        String signatureMethod = "HMAC-SHA256";
        // 八位随机字符串，每次重新生成
        String signatureNonce = RandomStringUtils.randomAlphanumeric(32).toLowerCase();
        Instant utcNow = Instant.now();

        // 转换为北京时间
        Instant beijingTime = utcNow.atZone(ZoneOffset.UTC)
                .withZoneSameInstant(ZoneId.of("Asia/Shanghai"))
                .toInstant();
        long timestamp = beijingTime.getEpochSecond();
        String signedText = accessKeyId + "&" + signatureMethod + "&" + signatureNonce + "&" + timestamp;
        System.out.println("signedText:" + signedText);
        String signature = null;
        try {
            signature = HMACSHA256Util.generateHmacSHA256Signature(signedText, accessKeySecret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("signature:" + signature);

        JSONObject body = new JSONObject();
        body.put("accessKeyId", accessKeyId);
        body.put("signatureMethod", "HMAC-SHA256");
        body.put("signatureNonce", signatureNonce);
        body.put("timestamp", String.valueOf(timestamp));
        body.put("signature", signature);
        String requestBody = body.toJSONString();
        System.out.println("requestBody:" + requestBody);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);

            // 设置请求头
            httpPost.setHeader("Content-Type", "application/json");

            // 设置请求体
            httpPost.setEntity(new StringEntity(requestBody));

            // 执行请求 1713347540682 1713347542943
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // 处理响应
                    String rs = EntityUtils.toString(entity);
                    System.out.println("Response: " + rs);
                    JSONObject jsonObject = JSON.parseObject(rs);
                    String code = jsonObject.getString("code");
                    result = jsonObject.getJSONObject("data").getString("accessToken");
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 调用审核
    public static String getAudit(String title, String content, String token, String url) {
        String result = "";
        // 构建body
        JSONObject body = new JSONObject();
        String contentBody = title + "#" + content;
        body.put("pkg", "net.luculent.gdhbny"); // 使用appid
        body.put("content", contentBody); // 最大10000
        String requestBody = body.toJSONString();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);

            // 设置请求头
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Audit-Access-Token", token);

            // 设置请求体
            httpPost.setEntity(new StringEntity(requestBody));

            // 执行请求 1713347540682 1713347542943
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // 处理响应
                    String rs = EntityUtils.toString(entity);
                    System.out.println("Response: " + rs);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return result;
    }


    public static String base64Encode(String originalString) {
        byte[] encodedBytes = Base64.getEncoder().encode(originalString.getBytes());
        return new String(encodedBytes);
    }

}
