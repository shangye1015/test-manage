package net.shangye.manage.test.mobilepush.push_api.oppo;

import com.alibaba.fastjson.JSON;
import com.oppo.push.server.*;
import lombok.extern.slf4j.Slf4j;
import net.shangye.manage.test.mobilepush.push_api.dto.Message;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import static net.shangye.manage.test.mobilepush.push_api.tuibian.TestTuiBiAn.getAuditResult;

@Slf4j
//@Service("push_O")
public class OppoSendTest {
    public static void main(String[] args) throws Exception {

        String auditResult = getAuditResult("2c8209c35aa04be69da75d5d1f6432e9",
                "7efcc5a53d3e46b1a7e8983fa9fcef04", "https://push.tuibianchina.com/v1/token",
                "https://push.tuibianchina.com/v1/audit", new Message());
        int status = sendImpl(auditResult, "测试oppo推送bbbb", "推送内容推送内容审核bbbb", "", "10000000000000000000");
        System.out.println("---------sta:" + status);
    }

    public static int sendImpl(String audit, String title, String content, String extras, String acceptUsrId) throws Exception {
        //	Properties p = getOppoProperties();
        String masterSecret = "b955c8d2ad7f4b7fb4dd523967948db4";
        String appKey = "9553ac64d6604fa9b92e063eb5ec32ab";
        Sender sender = new Sender(appKey, masterSecret);
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setContent(content);
        //通知栏样式 1. 标准样式  2. 长文本样式  3. 大图样式 【非必填，默认1-标准样式】
        notification.setStyle(1);
        // App开发者自定义消息Id，OPPO推送平台根据此ID做去重处理，对于广播推送相同appMessageId只会保存一次，对于单推相同appMessageId只会推送一次
        notification.setAppMessageId(UUID.randomUUID().toString());
        // 点击动作类型0，启动应用；1，打开应用内页（activity的intent action）；2，打开网页；4，打开应用内页（activity）；【非必填，默认值为0】;5,Intent scheme URL
        notification.setClickActionType(0);
        // 展示类型 (0, “即时”),(1, “定时”)
        notification.setShowTimeType(0);
        // 是否进离线消息,【非必填，默认为True】
        notification.setOffLine(true);
        // 离线消息的存活时间(time_to_live) (单位：秒), 【off_line值为true时，必填，最长3天】
        notification.setOffLineTtl(24 * 3600);
        // 时区，默认值：（GMT+08:00）北京，香港，新加坡
        notification.setTimeZone("GMT+08:00");
        // 0：不限联网方式, 1：仅wifi推送
        notification.setNetworkType(0);
//        notification.setChannelId("123456789");
        notification.setChannelId("luculent");
        notification.setAuditResponse(audit); // 添加审核响应内容
        Target target = new Target(); // 创建广播目标
        target.setTargetValue("OnePlus_CN_1940ec2b67edc10b39ebf2dc873f5a73");

//		Target target = Target.build(acceptUsrId); 
//		Target target = new Target();
//		//TargetType targetType = new TargetType();
//		target.setTargetType(TargetType.);//创建发送对象

        Result result = sender.unicastNotification(notification, target);  //发送单推消息
        ReturnCode returnCode = result.getReturnCode();
        System.out.println("oppoResult:" + JSON.toJSONString(result));

        int errorCode = returnCode.getCode();
        if (errorCode == 0) {
            return 1;
        }
        return 0;
    }

    private static Notification getNotification() {
        Notification notification = new Notification();
        /**
         * 以下参数必填项
         */
        notification.setTitle("通知栏消息tile");
        notification.setContent("通知栏内容");
        /**
         * 以下参数非必填项， 如果需要使用可以参考OPPO push服务端api文档进行设置
         */
        //通知栏样式 1. 标准样式  2. 长文本样式  3. 大图样式 【非必填，默认1-标准样式】
        notification.setStyle(1);
        // App开发者自定义消息Id，OPPO推送平台根据此ID做去重处理，对于广播推送相同appMessageId只会保存一次，对于单推相同appMessageId只会推送一次
        notification.setAppMessageId(UUID.randomUUID().toString());
        // 应用接收消息到达回执的回调URL，字数限制200以内，中英文均以一个计算
        // notification.setCallBackUrl("http://www.test.com");
        // App开发者自定义回执参数，字数限制50以内，中英文均以一个计算
        // notification.setCallBackParameter("");
        // 点击动作类型0，启动应用；1，打开应用内页（activity的intent action）；2，打开网页；4，打开应用内页（activity）；【非必填，默认值为0】;5,Intent scheme URL
        notification.setClickActionType(0);
        // 应用内页地址【click_action_type为1或4时必填，长度500】
        // notification.setClickActionActivity("com.coloros.push.demo.component.InternalActivity");
        // 网页地址【click_action_type为2必填，长度500】
        // notification.setClickActionUrl("http://www.test.com");
        // 动作参数，打开应用内页或网页时传递给应用或网页【JSON格式，非必填】，字符数不能超过4K，示例：{"key1":"value1","key2":"value2"}
        // notification.setActionParameters("{\"key1\":\"value1\",\"key2\":\"value2\"}");
        // 展示类型 (0, “即时”),(1, “定时”)
        notification.setShowTimeType(0);
        // 定时展示开始时间（根据time_zone转换成当地时间），时间的毫秒数
        // notification.setShowStartTime(System.currentTimeMillis() + 1000 * 60 * 3);
        // 定时展示结束时间（根据time_zone转换成当地时间），时间的毫秒数
        // notification.setShowEndTime(System.currentTimeMillis() + 1000 * 60 * 5);
        // 是否进离线消息,【非必填，默认为True】
        notification.setOffLine(true);
        // 离线消息的存活时间(time_to_live) (单位：秒), 【off_line值为true时，必填，最长3天】
        notification.setOffLineTtl(24 * 3600);
        // 时区，默认值：（GMT+08:00）北京，香港，新加坡
        notification.setTimeZone("GMT+08:00");
        // 0：不限联网方式, 1：仅wifi推送
        notification.setNetworkType(0);
        return notification;
    }

    private static Properties getOppoProperties() {
        Properties pro = new Properties();
        try {
            pro.load(OppoSendTest.class.getResourceAsStream("oppo.properties"));
        } catch (IOException e) {
            log.error("获取配置文件异常" + e);
        }
        return pro;
    }
}
