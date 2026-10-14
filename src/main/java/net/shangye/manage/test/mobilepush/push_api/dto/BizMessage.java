package net.shangye.manage.test.mobilepush.push_api.dto;

import lombok.Data;

/**
 * <p>Title: 业务消息类</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2020</p >
 * <p>Company: Luculent</p >
 *
 * @author hudi
 * @version 7.0
 * @createTime 2021-11-01 16:58:50
 */
@Data
@SuppressWarnings("unused")
public class BizMessage extends WiseMessage {

    /**
     * 程序号
     */
    private String pgmId;

    /**
     * 程序名称
     */
    private String pgmNam;

    /**
     * 主键
     */
    private String pkValue;

    /**
     * 待办任务号
     */
    private String todoNo;

    /**
     * 流程节点号
     */
    private String objectNo;

    /**
     * 单据时间
     */
    private String time;

    /**
     * 分类Id
     */
    private String cateId;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 消息是否已读
     */
    private String isRead;

    /**
     * 集群消息id-单个消息id
     */
    private String clusterMsgId;

    /**
     * 集群消息id-多个消息ids
     */
    private String clusterMsgIds;
}