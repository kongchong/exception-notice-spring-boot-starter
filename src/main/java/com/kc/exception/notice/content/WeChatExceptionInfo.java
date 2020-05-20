package com.kc.exception.notice.content;

import com.kc.exception.notice.enums.WeChatMsgTypeEnum;
import com.kc.exception.notice.properties.WeChatProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.kc.exception.notice.enums.WeChatMsgTypeEnum.MARKDOWN;
import static com.kc.exception.notice.enums.WeChatMsgTypeEnum.TEXT;

/**
 * 企业微信异常通知消息请求体
 *
 * @author kongchong
 */
@Data
public class WeChatExceptionInfo {

    private WeChatText text;
    private WeChatMarkDown markdown;
    private String msgtype;

    public WeChatExceptionInfo(ExceptionInfo exceptionInfo, WeChatProperties weChatProperties) {
        WeChatMsgTypeEnum msgType = weChatProperties.getMsgType();
        if (msgType.equals(TEXT)) {
            this.text = new WeChatText(exceptionInfo.createText(), weChatProperties.getAtUserIds(), weChatProperties.getAtPhones());
        } else if (msgType.equals(MARKDOWN)) {
            this.markdown = new WeChatMarkDown(exceptionInfo.createWeChatMarkDown());
        }
        this.msgtype = msgType.getMsgType();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class WeChatText {

        private String content;

        private String[] mentioned_list;

        private String[] mentioned_mobile_list;

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class WeChatMarkDown {

        private String content;

    }


}
