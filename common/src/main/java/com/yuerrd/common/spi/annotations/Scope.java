package com.yuerrd.common.spi.annotations;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author yuerrd
 */
@Retention(RetentionPolicy.CLASS)
@Inherited
public @interface Scope {
    Scopes value();
}
