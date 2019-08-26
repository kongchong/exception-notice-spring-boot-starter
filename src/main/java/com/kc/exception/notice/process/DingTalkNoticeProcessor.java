package com.kc.exception.notice.process;

import com.kc.exception.notice.content.DingTalkExceptionInfo;
import com.kc.exception.notice.content.DingTalkResult;
import com.kc.exception.notice.content.ExceptionInfo;
import com.kc.exception.notice.properties.DingTalkProperties;
import com.kc.exception.notice.properties.ExceptionNoticeProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/**
 * 钉钉异常信息通知具体实现
 *
 * @author kongchong
 */
public class DingTalkNoticeProcessor implements INoticeProcessor {

    private final DingTalkProperties dingTalkProperties;

    private final RestTemplate restTemplate;

    private final Log logger = LogFactory.getLog(getClass());

    public DingTalkNoticeProcessor(RestTemplate restTemplate,
                                   DingTalkProperties dingTalkProperties) {
        Assert.hasText(dingTalkProperties.getWebHook(),"DingTalk webHook must not be null");
        this.dingTalkProperties = dingTalkProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendNotice(ExceptionInfo exceptionInfo) {
        DingTalkExceptionInfo dingDingNotice = new DingTalkExceptionInfo(exceptionInfo.createText(),
                dingTalkProperties.getAtPhones());
        DingTalkResult result = restTemplate.postForObject(dingTalkProperties.getWebHook(), dingDingNotice, DingTalkResult.class);
        logger.debug(result);
    }

}
