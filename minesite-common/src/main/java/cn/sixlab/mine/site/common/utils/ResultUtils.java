package cn.sixlab.mine.site.common.utils;

import cn.sixlab.mine.site.common.vo.Result;

public class ResultUtils {

    /**
     * 请求返回成功
     *
     * @param message 消息
     * @return Result
     */
    public static Result msg(String message) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(Err.SUCCESS);
        result.setMsg(message);
        return result;
    }

    /**
     * 请求返回成功
     *
     * @param object 返回的 data
     * @return Result
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(Err.SUCCESS);
        result.setMsg("common.operate.success");
        result.setData(object);
        return result;
    }

    /**
     * 请求返回成功
     *
     * @param code 错误编号
     * @param msg  错误消息
     * @return Result
     */
    public static Result success(Integer code, String msg) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 请求成功，不需要返回data
     *
     * @return Result
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 请求失败
     *
     * @param code 错误编号
     * @param msg  错误消息
     * @return Result
     */
    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 请求失败
     *
     * @param code   错误编号
     * @param msg    错误消息
     * @param object 错误相关数据
     * @return Result
     */
    public static Result error(Integer code, String msg, Object object) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }
}
