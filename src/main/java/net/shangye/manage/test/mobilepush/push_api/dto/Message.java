package net.shangye.manage.test.mobilepush.push_api.dto;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class Message {

    /**
     * 推送标题
     */
    private String msgTitle;

    /**
     * 推送文本内容
     */
    private String msgContent;

    /**
     * 扩展内容
     */
    private String extra;

    /**
     * 消息接收用户ID
     */
    private String acceptUsrId;

}

