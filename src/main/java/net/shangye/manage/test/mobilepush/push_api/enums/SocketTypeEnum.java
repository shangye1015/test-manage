package net.shangye.manage.test.mobilepush.push_api.enums;


/**
 * <p>Title: socket类型枚举类</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2020</p >
 * <p>Company: Luculent</p >
 * @author wangsongru
 * @createTime 2020-2-25 11:24:02
 * @version 7.0
 */
@SuppressWarnings("unused")
public enum SocketTypeEnum {
    /**
     * 聊天消息类型
     */
    CHAT("chat"),

    /**
     *  业务消息类型
     */
    BUSINESS("business"),

    /**
     * 超时锁定
     */
    TIMEOUT_LOCK("timeoutLock"),

    /**
     * socket会话保持
     */
    CHECK("check"),

    /**
     * socket已连接
     */
    CONNECTED("connected"),

    /**
     * 移动透传使用的简单消息
     */
    SIMPLE("simple");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    SocketTypeEnum(String value) {
        this.value = value;
    }
}