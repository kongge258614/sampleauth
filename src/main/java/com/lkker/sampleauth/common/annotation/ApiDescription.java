package com.lkker.sampleauth.common.annotation;

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
