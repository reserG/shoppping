package com.example.seckillservice.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(METHOD)
public @interface MyAnnotationHandler {
    int seconds();

    int maxCount();

    boolean needLogin() default true;
}
