package net.shangye.manage.test.mobilepush.push_api.oppo;

import com.oppo.push.server.*;
import lombok.extern.slf4j.Slf4j;
import net.shangye.manage.test.mobilepush.push_api.MessageSendAbstract;
import net.shangye.manage.test.mobilepush.push_api.dto.PushMessage;
import net.shangye.manage.test.mobilepush.push_api.enums.SendStatusEnum;
import net.shangye.manage.test.mobilepush.service.PushMessageService;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Title: OPPO服务端推送
 * Description:
 * Copyright: Copyright (c) 2020
 * Company: Luculent
 * Author: wangsongru
 * Version: 7.0
 * CreateTime: 2020-04-28 15:39:53
 */
@Slf4j
@Service(value = "push_O")
@SuppressWarnings("unused")
public class OppoSend extends MessageSendAbstract {

    public OppoSend(PushMessageService pushMessageService) {
        super(pushMessageService);
    }

    /**
     * 发送消息
     *
     * @param message 消息体
     * @return 推送返回状态
     * Author: wangsongru
     * CreateTime: 2020-10-09 17:51:23
     */
    @Override
    public int send(PushMessage message) {
        return sendImpl(message.getTitle(), message.getDescribe(), message.getExtra(), message.getDeviceTokenId());
    }

    /**
     * 实际发送消息的实现
     *
     * @param title       消息标题
     * @param content     消息内容
     * @param extras      额外信息
     * @param deviceToken 设备token
     * @return 推送返回状态
     * Author: luwendi
     * CreateTime: 2020-10-06 15:45:23
     */
    public int sendImpl(String title, String content, String extras, String deviceToken) {
        String masterSecret = pushParam.getSecret();
        String appKey = pushParam.getKey();
        Sender sender;
        try {
            sender = new Sender(appKey, masterSecret);
            Notification notification = new Notification();
            notification.setTitle(title);
            notification.setContent(content);
            notification.setStyle(1);
            notification.setAppMessageId(UUID.randomUUID().toString());
            notification.setClickActionType(0);
            notification.setShowTimeType(0);
            notification.setOffLine(true);
            notification.setOffLineTtl(24 * 3600);
            notification.setTimeZone("GMT+08:00");
            notification.setNetworkType(0);
            notification.setChannelId("luculent");

            Target target = new Target();
            target.setTargetValue(deviceToken);

            Result result = sender.unicastNotification(notification, target);
            ReturnCode returnCode = result.getReturnCode();
            int errorCode = returnCode.getCode();
            if (errorCode == 0) {
                log.info("OPPO推送成功");
                return SendStatusEnum.SUCCESS.getId();
            } else {
                log.error("OPPO推送失败 error Code:" + errorCode + "\nErrMsg:" + returnCode.getMessage());
                return SendStatusEnum.FAILED.getId();
            }
        } catch (Exception e) {
            log.error("OPPO推送失败", e);
            e.printStackTrace();
            return SendStatusEnum.FAILED.getId();
        }
    }
}
