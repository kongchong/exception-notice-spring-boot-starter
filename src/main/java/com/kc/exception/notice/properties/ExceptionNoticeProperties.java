package com.kc.exception.notice.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 异常通知配置
 *
 * @author kongchong
 */
@Data
@ConfigurationProperties(prefix = ExceptionNoticeProperties.PREFIX)
public class ExceptionNoticeProperties {

    public static final String PREFIX = "exception.notice";
    /**
     * 启用开关
     */
    private boolean enable;
    /**
     * 异常工程名
     */
    @Value("${spring.application.name:${exception.project-name:无名工程}}")
    private String projectName;
    /**
     * 追踪信息的包含的包名
     */
    private String includedTracePackage;
    /**
     * 异常信息发送的时间周期 以秒为单位 默认5s
     */
    private Long period = 5L;
    /**
     * 排除的需要统计的异常
     */
    private List<Class<? extends Exception>> excludeExceptions = new ArrayList<>();
    /**
     * 钉钉通知配置
     */
    @NestedConfigurationProperty
    private DingTalkProperties dingTalk;
    /**
     * 邮箱通知配置
     */
    @NestedConfigurationProperty
    private MailProperties mail;
}
