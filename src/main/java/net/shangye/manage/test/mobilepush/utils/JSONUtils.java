package net.shangye.manage.test.mobilepush.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class JSONUtils {

    /**
     * 安全的从json中获取JSONArray
     *
     * @param json         来源json
     * @param defaultValue 默认值构造方法
     * @param keys         从来源json获取JSONArray的来源路径
     * @return JSONArray JSONArray对象
     * @author hudi
     * @createTime 2021-11-08 09:02:42
     */
    public static JSONArray optJSONArray(JSONObject json, Supplier<JSONArray> defaultValue, Object... keys) {
        JSONArray result = null;
//        if (keys.length == 1) {
//            result = json.optJSONArray((String) keys[0]);
//            return result != null ? result : defaultValue.get();
//        }
//        Object innerObject = json;
//        try {
//            innerObject = getInner(innerObject, keys);
//            if (innerObject instanceof JSONArray) {
//                result = (JSONArray) innerObject;
//            }
//        } catch (Exception e) {
//            return defaultValue.get();
//        }
        return result != null ? result : defaultValue.get();
    }

}
