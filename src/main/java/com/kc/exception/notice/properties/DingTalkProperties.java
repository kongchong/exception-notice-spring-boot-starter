package com.kc.exception.notice.properties;

import lombok.Data;

/**
 * 钉钉机器人配置
 *
 * @author kongchong
 */
@Data
public class DingTalkProperties {

    /**
     * 发送消息时被@的钉钉用户手机号
     */
    private String[] atPhones;

    /**
     * 钉钉机器人webHook地址
     */
    private String webHook;
}
