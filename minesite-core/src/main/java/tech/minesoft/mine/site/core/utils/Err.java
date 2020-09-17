package tech.minesoft.mine.site.core.utils;

public class Err {
    public static Integer SUCCESS = 200;

    // 都是四位，和三位的HTTP状态码区分开
    // 未知异常
    public static Integer EXCEPTION = 1000;

    // 权限异常
    public static Integer AUTH = 9999;
    // 验证权限失败
    public static Integer AUTH_MATCH = 9000;

    // 已存在
    public static Integer ERR_EXIST = 4000;
    // 不存在
    public static Integer ERR_NOT_EXIST = 4004;
}
