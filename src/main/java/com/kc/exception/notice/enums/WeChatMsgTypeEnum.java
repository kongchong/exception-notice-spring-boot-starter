package com.kc.exception.notice.enums;

/**
 * 企业微信文本类型枚举
 *
 * @author kongchong
 */
public enum WeChatMsgTypeEnum {

    TEXT("text"), MARKDOWN("markdown");

    private final String msgType;

    public String getMsgType() {
        return msgType;
    }

    WeChatMsgTypeEnum(String msgType) {
        this.msgType = msgType;
    }
}
