package net.shangye.manage.test.mobilepush.push_api.dto;

import lombok.Data;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: 推送配置参数 </p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2019</p >
 * <p>Company: Luculent</p >
 *
 * @author hudi
 * @version 7.0
 * @createTime 2021-11-09 16:30:00
 */
@Data
public class AppPushParam {

    private static final Map<String, String> PLATFORM_MAP = new HashMap<>();

    static {
        PLATFORM_MAP.put("xiaomi", "M");
        PLATFORM_MAP.put("huawei", "H");
        PLATFORM_MAP.put("ios", "I");
        PLATFORM_MAP.put("vivo", "V");
        PLATFORM_MAP.put("oppo", "O");
        PLATFORM_MAP.put("jiguang", "A");
    }

    /**
     * 配置标记no
     */
    private String appNo;

    /**
     * 推送用app标记
     */
    private String appId = "mobile70";

    /**
     * 平台类型
     */
    private String platform;

    /**
     * 推送参数key
     */
    private String key;

    /**
     * 推送参数secret
     */
    private String secret;

    /**
     * 推送参数 额外自定义参数
     */
    private JSONObject extern;

    /**
     * 推送消息类别
     */
    private String pushAppId;

    /**
     * 通过json构建对象
     *
     * @param json 来源json
     * @return AppPushParam 对象
     * @author hudi
     * @createTime 2021-11-09 17:09:11
     */
    public static AppPushParam fromJson(JSONObject json) {
        final AppPushParam appPushParam = new AppPushParam();
        appPushParam.appNo = json.optString("appNo");
        appPushParam.appId = json.optString("appId");
        appPushParam.platform = PLATFORM_MAP.get(json.optString("platform"));
        appPushParam.key = json.optString("key");
        appPushParam.secret = json.optString("secret");
        appPushParam.pushAppId = json.optString("pushAppId");
        appPushParam.extern = null; // TODO：JSONUtils.optJSONObject(json, JSONObject::new, "extern");
        return appPushParam;
    }





}