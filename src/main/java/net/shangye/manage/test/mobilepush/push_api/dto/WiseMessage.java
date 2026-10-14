package net.shangye.manage.test.mobilepush.push_api.dto;

import lombok.Data;

import java.util.Date;

/**
 * <p>Title: 消息实体父类</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2020</p >
 * <p>Company: Luculent</p >
 *
 * @author hudi
 * @version 7.0
 * @createTime 2021-11-01 16:57:14
 */
@Data
@SuppressWarnings("unused")
public class WiseMessage {

    /**
     * 消息id
     */
    protected String msgId;

    /**
     * 标题
     */
    protected String title;

    /**
     * 内容详情
     */
    protected String describe;

    /**
     * 附件(多个用逗号隔开)
     */
    protected String docIds;

    /**
     * 短网址主键
     */
    protected String dwzNo;

    /**
     * 外部链接完整url
     */
    protected String url;

    /**
     * 图标，建议使用base64
     */
    protected String icon;

    /**
     * 分享人
     */
    protected String sendUser;

    /**
     * 分享人name
     */
    protected String sendUserNam;

    /**
     * 消息状态：0未发送1发送成功2发送失败
     */
    protected String status;

    /**
     * 接收人
     */
    protected String[] receiver;

    /**
     * 计划发送时间
     */
    protected Date planSendTime;

    /**
     * 扩展内容
     */
    protected String extra = "";

    /**
     * 终端类型
     */
    protected String terminal;

    /**
     * 分享人公司号
     */
    protected String sendUserOrgNo;

    /**
     * 角标
     */
    protected Integer badge;
}
