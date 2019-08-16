package com.kc.exception.notice.content;

import lombok.Data;

/**
 * 钉钉异常通知消息请求体
 *
 * @author kongchong
 */
@Data
public class DingTalkExceptionInfo {

    private DingDingText text;
    private DingDingAt at;
    private String msgtype = "text";

    public DingTalkExceptionInfo(String text, String... at) {
        this.text = new DingDingText(text);
        this.at = new DingDingAt(at);
    }

    @Data
    static class DingDingText {

        private String content;

        DingDingText(String content) {
            this.content = content;
        }

    }

    @Data
    static class DingDingAt {

        private String[] atMobiles;

        private boolean isAtAll = false;

        DingDingAt(String... atMobiles) {
            this.atMobiles = atMobiles;
        }

    }


}
