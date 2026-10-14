package net.shangye.manage.test.mobilepush.service;


import net.shangye.manage.test.mobilepush.push_api.dto.PushMessage;
import net.shangye.manage.test.mobilepush.push_api.dto.ResultMessage;
import org.json.JSONObject;

import java.util.List;

public interface PushMessageService {

    /**
     * 推送任务默认方法
     * @author hudi
     * @createTime 2021-11-05 17:39:37
     */
    default void pushMessageTask() {
        final List<PushMessage> allPushMessages = getAllPushMessages();
        pushMessage(allPushMessages);
    }

    /**
     * 获取所有需要推送的消息
     * @return List<PushMessage> 需要推送的消息集合
     * @author hudi
     * @createTime 2021-11-05 17:39:58
     */
    List<PushMessage> getAllPushMessages();

    /**
     * 推送消息
     * @param messages 需要推送的消息集合
     * @author hudi
     * @createTime 2021-11-05 17:40:36
     */
    void pushMessage(List<PushMessage> messages);

    /**
     * 更新消息
     * @param messages 需要推送的消息集合
     * @param resultMessages 推送有返回的消息集合
     * @author hudi
     * @createTime 2021-11-05 17:41:00
     */
    void updateMessage(List<PushMessage> messages, List<ResultMessage> resultMessages);

    /**
     * 读取message.json
     * @return JSONObject message.json
     * @author hudi
     * @createTime 2021-11-05 17:41:27
     */
    JSONObject readMessageJson();

    /**
     * 写入message.json
     * @param msgJson 需要写入的json
     * @author hudi
     * @createTime 2021-11-05 17:42:04
     */
    void writeMessageJson(JSONObject msgJson);

}