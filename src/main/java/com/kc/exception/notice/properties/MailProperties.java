package com.kc.exception.notice.properties;

import lombok.Data;

/**
 * 邮箱配置
 *
 * @author kongchong
 */
@Data
public class MailProperties {

    /**
     * 发送人
     */
    private String from;
    /**
     * 接收人，可多选
     */
    private String[] to;
    /**
     * 抄送人，可多选
     */
    private String[] cc;

}
