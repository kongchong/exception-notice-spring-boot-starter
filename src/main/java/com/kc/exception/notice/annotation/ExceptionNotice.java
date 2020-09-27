package com.kc.exception.notice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 针对一些异步方法无法捕获到异常的情况新增此注解
 * 只需要标记在异步方法的入口类上即可
 *
 * @author kongchong
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ExceptionNotice {


}
