package com.framework.xhb.butterknife_apt.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.CLASS;

@Retention(CLASS) @Target(FIELD)
public @interface BindArray {
    /** Array resource ID to which the field will be bound. */
//    @ArrayRes
    int value();
}
