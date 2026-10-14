package net.shangye.manage.test.mobilepush.push_api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 推送消息
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuppressWarnings("unused")
public class PushMessage extends BizMessage {

    private static final Logger Log = LoggerFactory.getLogger(PushMessage.class);

    /**
     * 移动平台 I苹果A安卓H华为M小米
     */
    private String platform = "";

    /**
     * 设备令牌
     */
    private String deviceTokenId = "";

    /**
     * 提示音
     */
    private String sound;

    /**
     * 推送配置的appId标识
     */
    private String appNo;

    /**
     * json转PushMessage对象方法
     *
     * @param message json
     * @return PushMessage 对象
     */
    public static PushMessage fromJson(JSONObject message) {
        final PushMessage pushMessage = new PushMessage();
        try {
            final JSONArray receiverArray = null;// TODO: optJSONArray 使用org.json 不是fastJson JSONUtils.optJSONArray(message, JSONArray::new, "receiver");
            final String[] receivers = new String[receiverArray.length()];
            for (int i = 0; i < receiverArray.length(); i++) {
                receivers[i] = receiverArray.optString(i);
            }
            pushMessage.setReceiver(receivers);
            pushMessage.setExtra(message.optString("extra"));
            pushMessage.setSendUser(message.optString("sendUser"));
            pushMessage.setMsgId(message.optString("msgId"));
            pushMessage.setDeviceTokenId(message.optString("deviceTokenId"));
            pushMessage.setTodoNo(message.optString("todoNo"));
            pushMessage.setPgmId(message.optString("pgmId"));
            pushMessage.setTerminal(message.optString("terminal"));
            pushMessage.setDescribe(message.optString("describe"));
            pushMessage.setPkValue(message.optString("pkValue"));
            pushMessage.setTitle(message.optString("title"));
            pushMessage.setPlatform(message.optString("platform"));
            pushMessage.setBadge(message.optInt("badge"));
            pushMessage.setAppNo(message.optString("appNo"));
        } catch (Exception e) {
            Log.error("json转换PushMessage实例出错", e);
        }
        return pushMessage;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDeviceTokenId() {
        return deviceTokenId;
    }

    public void setDeviceTokenId(String deviceTokenId) {
        this.deviceTokenId = deviceTokenId;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
        this.appNo = appNo;
    }
}
