package com.kc.exception.notice.content;

import com.kc.exception.notice.enums.DingTalkMsgTypeEnum;
import com.kc.exception.notice.properties.DingTalkProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.kc.exception.notice.enums.DingTalkMsgTypeEnum.MARKDOWN;
import static com.kc.exception.notice.enums.DingTalkMsgTypeEnum.TEXT;

/**
 * 钉钉异常通知消息请求体
 *
 * @author kongchong
 */
@Data
public class DingTalkExceptionInfo {

    private String msgtype;

    private DingDingText text;

    private DingDingMarkDown markdown;

    private DingDingAt at;

    public DingTalkExceptionInfo(ExceptionInfo exceptionInfo, DingTalkProperties dingTalkProperties) {
        DingTalkMsgTypeEnum msgType = dingTalkProperties.getMsgType();
        if (msgType.equals(TEXT)) {
            this.text = new DingDingText(exceptionInfo.createText());
        } else if (msgType.equals(MARKDOWN)) {
            this.markdown = new DingDingMarkDown(exceptionInfo.getProject(), exceptionInfo.createDingTalkMarkDown());
        }
        this.msgtype = msgType.getMsgType();
        this.at = new DingDingAt(dingTalkProperties.getAtMobiles(), dingTalkProperties.getIsAtAll());
    }

    @Data
    static class DingDingText {

        private String content;

        DingDingText(String content) {
            this.content = content;
        }

    }

    @AllArgsConstructor
    @Data
    static class DingDingMarkDown {

        private String title;

        private String text;

    }

    @AllArgsConstructor
    @Data
    static class DingDingAt {

        private String[] atMobiles;

        private boolean isAtAll;

    }


}
