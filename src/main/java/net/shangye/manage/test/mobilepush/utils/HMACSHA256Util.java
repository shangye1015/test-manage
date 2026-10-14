package net.shangye.manage.test.mobilepush.utils;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HMACSHA256Util {
    /**
     * 生成 HMAC-SHA256 签名
     *
     * @param data 要签名的数据
     * @param key  密钥
     * @return Base64编码的签名字符串
     * @throws Exception 抛出异常，例如无法找到算法或无效的密钥等
     */
    public static String generateHmacSHA256Signature(String data, String key) throws Exception {
        // 获取HMAC-SHA256算法的实例
        Mac hmacSha256 = Mac.getInstance("HmacSHA256");
        // 使用给定的密钥初始化Mac对象
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        hmacSha256.init(secretKeySpec);
        // 执行签名
        byte[] rawHmac = hmacSha256.doFinal(data.getBytes(StandardCharsets.UTF_8));
        // 将二进制结果转换为Base64字符串以便于显示或存储
        return Base64.getEncoder().encodeToString(rawHmac);
    }

//    public static void main(String[] args) {
//        try {
//            String data = "The quick brown fox jumps over the lazy dog";
//            String key = "secret";
//            String signature = generateHmacSHA256Signature(data, key);
//            System.out.println("Signature: " + signature);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

