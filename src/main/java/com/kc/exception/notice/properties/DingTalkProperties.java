package com.kc.exception.notice.properties;

import com.kc.exception.notice.enums.DingTalkMsgTypeEnum;
import lombok.Data;

import static com.kc.exception.notice.enums.DingTalkMsgTypeEnum.TEXT;


/**
 * 钉钉机器人配置
 *
 * @author kongchong
 */
@Data
public class DingTalkProperties {

    /**
     * 钉钉机器人webHook地址
     */
    private String webHook;

    /**
     * 发送消息时被@的钉钉用户手机号
     */
    private String[] atMobiles;

    /**
     * 发送消息时被@的钉钉用户手机号
     */
    private Boolean isAtAll = false;

    /**
     * 消息类型 暂只支持text和markdown
     */
    private DingTalkMsgTypeEnum msgType = TEXT;
}
