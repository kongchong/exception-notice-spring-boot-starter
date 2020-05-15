package com.kc.exception.notice.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public WeChatExceptionInfo(String msgType, ExceptionInfo exceptionInfo, String[] mentioned_list, String[] mentioned_mobile_list) {
        this.msgtype = msgType;
        if (msgType.equals("text")) {
            this.text = new WeChatText(exceptionInfo.createText(), mentioned_list, mentioned_mobile_list);
        } else if (msgType.equals("markdown")) {
            this.markdown = new WeChatMarkDown(exceptionInfo.createMarkDownText());
        }
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
