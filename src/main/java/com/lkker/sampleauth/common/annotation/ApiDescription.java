package com.lkker.sampleauth.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiDescription {
    /**
     * 描述
     *
     * @return 描述
     */
    String value();

    boolean isPublic() default false;

    /**
     * 入参
     *
     * @return 入参
     */
    Class<?> In() default Object.class;

    /**
     * 出参
     *
     * @return 出参
     */
    Class<?> Out() default Object.class;

}
