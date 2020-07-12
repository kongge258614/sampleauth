package com.lkker.sampleauth.config;

/**
 * @Author liliang
 * @Date 2020/7/12 17:38
 * @Description
 **/
public enum MenuType {

    /**
     * 导航菜单
     */
    NAV,
    /**
     * 用户菜单
     */
    USER,
    /**
     * 管理菜单
     */
    MANAGE,
    /**
     * APP接口
     */
    API,
    /**
     * 分析接口
     */
    ANALYSIS,
    /**
     * 其他
     */
    OTHER;

    public static MenuType judgeValue(String code) {

        MenuType[] values = MenuType.values();
        for (MenuType value : values) {
            if (value.name().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
