package com.lkker.sampleauth.common.utils;

/**
 * @Author liliang
 * @Date 2020/7/12 21:05
 * @Description
 **/
public enum  BaseMessage implements CommonMessage {

    /**
     * 通用消息成功
     */
    SUCCESS(200, "请求成功"),
    /**
     * 通用消息失败
     */
    ERROR(500, "请求异常"),


    /**
     * 对象不存在
     */
    NOT_EXIST(9904, "请求对象不存在"),

    /**
     * 对象已存在
     */
    EXIST(9905, "请求对象已存在"),
    /**
     * 创建失败
     */
    CREATE_ERROR(9906, "%s 创建失败"),

    QUERY_PAGE_ERROR(9908, "分页参数错误 %s"),
    /**
     * 通用消息处理中
     */
    PROCESS(9999, "请求正在处理中");



    private int code;

    private String message;

    BaseMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public static BaseMessage judgeValue(int code) {

        BaseMessage[] values = BaseMessage.values();
        for (BaseMessage value : values) {
            if (value.code == code) {
                return value;
            }
        }
        return null;
    }
}
