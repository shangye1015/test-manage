package net.shangye.manage.test.mobilepush.push_api;

import net.shangye.manage.test.mobilepush.push_api.dto.AppPushParam;
import net.shangye.manage.test.mobilepush.push_api.dto.PushMessage;
import net.shangye.manage.test.mobilepush.service.PushMessageService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 消息发送服务抽象类
 */
@Component
public abstract class MessageSendAbstract implements MessageSend {

    protected static final String APP_PREFIX = "net.luculent";

    /**
     * 推送service
     */
    protected final PushMessageService pushMessageService;

    /**
     * 推送配置
     */
    public AppPushParam pushParam;

    public MessageSendAbstract(PushMessageService pushMessageService) {
        this.pushMessageService = pushMessageService;
    }

    /**
     * 设置推送参数
     *
     * @param pushParam 推送配置
     * @return MessageSend 自身
     */
    @Override
    public MessageSend config(AppPushParam pushParam) {
        this.pushParam = pushParam;
        return this;
    }

    /**
     * 发送消息
     *
     * @param message 消息体
     * @return int 成功与否 ack
     */
    @Override
    public int send(PushMessage message) {
        return 0;
    }

    /**
     * 异步发送消息
     *
     * @param message   消息体
     * @param afterSend 消息返回结果处理方法，接收一个参数为 Map 类型
     */
    @Override
    public void send(PushMessage message, Consumer<Map<String, Object>> afterSend) {

    }

    /**
     * 异步发送所有消息
     *
     * @param messages  消息体列表
     * @param afterSend 消息返回结果处理方法，接收一个参数为 Map 类型
     */
    @Override
    public void sendAll(List<PushMessage> messages, Consumer<Map<String, Object>> afterSend) {

    }

    /**
     * 发送结束后处理方法
     *
     * @param param 参数
     */
    @Override
    public void afterSend(Map<String, Object> param) {
        /*调用接口更新状态*/
    }
}
