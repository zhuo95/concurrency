package com.zz.concurrency.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来标记线程不安全
 */

@Target(ElementType.TYPE) //哪些可以用这个注解
@Retention(RetentionPolicy.SOURCE) //注解存在的范围,source 在编译时会去掉
public @interface NotThreadSafe {
    String value() default "";
}
