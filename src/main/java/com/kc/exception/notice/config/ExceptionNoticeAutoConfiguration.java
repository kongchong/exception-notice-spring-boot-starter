package com.kc.exception.notice.config;

import com.kc.exception.notice.aop.ExceptionListener;
import com.kc.exception.notice.process.DingTalkNoticeProcessor;
import com.kc.exception.notice.handler.ExceptionNoticeHandler;
import com.kc.exception.notice.process.INoticeProcessor;
import com.kc.exception.notice.properties.DingTalkProperties;
import com.kc.exception.notice.properties.ExceptionNoticeProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 异常信息通知配置类
 *
 * @author kongchong
 * date: 2019-08-09 15:18
 */
@Configuration
@ConditionalOnProperty(prefix = ExceptionNoticeProperties.PREFIX, name = "enable", havingValue = "true")
@EnableConfigurationProperties(value = ExceptionNoticeProperties.class)
public class ExceptionNoticeAutoConfiguration {

    private final RestTemplate restTemplate = new RestTemplate();

    @Bean(initMethod = "start")
    public ExceptionNoticeHandler noticeHandler(ExceptionNoticeProperties properties) {
        DingTalkProperties dingTalk = properties.getDingTalk();
        INoticeProcessor exceptionNotice = null;
        if (null != dingTalk) {
            exceptionNotice = new DingTalkNoticeProcessor(restTemplate, properties);
        }
        if (null == exceptionNotice) {
            throw new IllegalArgumentException("异常通知配置不正确");
        }
        return new ExceptionNoticeHandler(properties, exceptionNotice);
    }

    @Bean
    @ConditionalOnBean(ExceptionNoticeHandler.class)
    public ExceptionListener exceptionListener(ExceptionNoticeHandler noticeHandler) {
        return new ExceptionListener(noticeHandler);
    }
}
