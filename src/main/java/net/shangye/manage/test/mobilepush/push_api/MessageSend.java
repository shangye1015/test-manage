package net.shangye.manage.test.mobilepush.push_api;


import net.shangye.manage.test.mobilepush.push_api.dto.AppPushParam;
import net.shangye.manage.test.mobilepush.push_api.dto.PushMessage;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface MessageSend {

    /**
     * 发送消息
     *
     * @param message 消息体
     * @return int 成功与否 ack
     * @author hudi
     * @createTime 2020-10-06 15:44:41
     */
    int send(PushMessage message);

    /**
     * 异步发送消息
     *
     * @param message   消息体
     * @param afterSend 消息返回结果处理方法，接收一个参数为 Map 类型
     * @implNote 实现类需异步发送消息，并在发送完成后调用 afterSend 方法处理结果
     */
    void send(PushMessage message, Consumer<Map<String, Object>> afterSend);

    /**
     * 异步发送所有消息
     *
     * @param messages  消息体列表
     * @param afterSend 消息返回结果处理方法，接收一个参数为 Map 类型
     * @implNote 实现类需异步发送消息列表，并在发送完成后调用 afterSend 方法处理结果
     */
    void sendAll(List<PushMessage> messages, Consumer<Map<String, Object>> afterSend);

    /**
     * 发送结束后处理方法
     *
     * @param param 参数
     * @implNote 实现类需在消息发送完成后调用此方法，对发送结果进行处理
     */
    void afterSend(Map<String, Object> param);

    /**
     * 设置推送参数
     *
     * @param pushParam 推送配置
     * @return MessageSend 自身
     * @implNote 实现类需设置推送参数，并返回自身以支持链式调用
     */
    MessageSend config(AppPushParam pushParam);
}

