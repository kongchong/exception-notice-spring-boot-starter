package com.kc.exception.notice.process;

import com.kc.exception.notice.content.DingTalkExceptionInfo;
import com.kc.exception.notice.content.DingTalkResult;
import com.kc.exception.notice.content.ExceptionInfo;
import com.kc.exception.notice.properties.ExceptionNoticeProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 钉钉异常信息处理具体实现
 *
 * @author kongchong
 */
public class DingTalkNoticeProcessor implements INoticeProcessor {

    private final ExceptionNoticeProperties exceptionProperties;

    private final RestTemplate restTemplate;

    private final Log logger = LogFactory.getLog(getClass());

    public DingTalkNoticeProcessor(RestTemplate restTemplate,
                                   ExceptionNoticeProperties exceptionProperties) {
        this.exceptionProperties = exceptionProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendNotice(ExceptionInfo exceptionInfo) {
        DingTalkExceptionInfo dingDingNotice = new DingTalkExceptionInfo(exceptionInfo.createText(),
                exceptionProperties.getDingTalk().getAtPhones());
        DingTalkResult result = restTemplate.postForObject(exceptionProperties.getDingTalk().getWebHook(), dingDingNotice, DingTalkResult.class);
        logger.debug(result);
    }

}
