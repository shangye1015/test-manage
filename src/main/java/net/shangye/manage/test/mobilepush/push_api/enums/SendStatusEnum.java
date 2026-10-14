package net.shangye.manage.test.mobilepush.push_api.enums;

/**
 * <p>Title: 发送状态枚举类</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2020</p >
 * <p>Company: Luculent</p >
 *
 * @author luwendi
 * @version 7.0
 * @createTime 2020-10-06 16:37:05
 */
@SuppressWarnings("unused")
public enum SendStatusEnum {
    // 没有发送
    NO_SEND(0, "NOSEND"),
    // 成功
    SUCCESS(1, "SUCCESS"),
    // 失败
    FAILED(2, "FAILOUR"),
    // 需要重新发送
    RESEND(3, "RESEND");

    /**
     * id
     */
    private final int id;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 构造方法
     *
     * @param id   数据库值
     * @param desc 描述
     * @author hudi
     * @createTime 2020-10-06 16:37:41
     */
    SendStatusEnum(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 根据id获取类型
     *
     * @param id 数据库值
     * @return SendStatusEnum 枚举
     * @author luwendi
     * @createTime 2020-10-06 16:37:49
     */
    public static SendStatusEnum valueOfById(int id) {
        SendStatusEnum[] types = SendStatusEnum.values();
        for (SendStatusEnum type : types) {
            if (type.id == id) {
                return type;
            }
        }
        return SendStatusEnum.NO_SEND;
    }

}