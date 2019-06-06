package com.framework.xhb.butterknife_apt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xhb
 * @desc custom annotation type
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Test {

}
