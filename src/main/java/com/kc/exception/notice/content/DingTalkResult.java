package com.kc.exception.notice.content;

import lombok.Data;

/**
 * 钉钉异常通知响应结果
 *
 * @author kongchong
 */
@Data
public class DingTalkResult {

    private int errcode;

    private String errmsg;

    @Override
    public String toString() {
        return "DingDingResult [errcode=" + errcode + ", errmsg=" + errmsg + "]";
    }

}
