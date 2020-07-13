package com.lkker.sampleauth.constant;

public class ConstantKey {

    public static final String SIGNING_KEY = "spring-security-@Jwt!&Secret^#";

    public static class userRole{

        /**
         * 超级管理员
         */
        public static final String ADMIN = "ADMIN";

        /**
         * 普通成员
         */
        public static final String MEMBER = "MEMBER";

    }
}
