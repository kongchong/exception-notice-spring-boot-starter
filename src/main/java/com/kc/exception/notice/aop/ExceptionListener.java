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

    private final ExceptionNoticeHandler handler;

    @AfterThrowing(value = "@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Controller)", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, Exception e) {
        handler.createNotice(e, joinPoint);
    }
}
