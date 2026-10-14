package net.shangye.manage.test.mobilepush.push_api.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import net.shangye.manage.test.mobilepush.push_api.enums.SendStatusEnum;

/**
 * 发送消息的结果对象
 */
@Data
@SuppressWarnings("unused")
public class ResultMessage {

    /**
     * 消息id
     */
    private String id;

    /**
     * 消息对象
     */
    private PushMessage pushMessage;

    /**
     * 消息发送结果Enum
     */
    private SendStatusEnum sendStatusEnum;

    public ResultMessage(String id, PushMessage pushMessage, SendStatusEnum sendStatusEnum) {
        this.id = id;
        this.pushMessage = pushMessage;
        this.sendStatusEnum = sendStatusEnum;
    }

    /**
     * ResultMessage对象转json方法
     *
     * @return JSONObject json对象
     */
    public JSONObject toJson() {
        final JSONObject result = new JSONObject();
        result.put("id", id);
        result.put("pushMessage", JSONObject.toJSONString(pushMessage));
        result.put("sendStatus", sendStatusEnum.getId());
        return result;
    }
}
