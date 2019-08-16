package com.kc.exception.notice.aop;

import com.kc.exception.notice.handler.ExceptionNoticeHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

/**
 * 异常捕获切面
 *
 * @author kongchong
 */
@Aspect
@Slf4j
@RequiredArgsConstructor
public class ExceptionListener {

    private static final String SEPARATOR = System.getProperty("line.separator");

    private final ExceptionNoticeHandler handler;

    @AfterThrowing(value = "@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Controller)", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, Exception e) {
        log.error("捕获到异常发送消息至钉钉:{}method:{}--->", SEPARATOR, joinPoint.getSignature().getName());
        handler.createNotice(e, joinPoint);
    }
}
