package com.yuerrd.common.spi;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * @author yuerrd
 */
@Retention(SOURCE)
@Documented
@Target(TYPE)
public @interface MyAnnotation {
    String value() default "test";
}
