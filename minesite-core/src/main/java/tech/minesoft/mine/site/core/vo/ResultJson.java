package tech.minesoft.mine.site.core.vo;

import tech.minesoft.mine.site.core.utils.I18nUtils;
import tech.minesoft.mine.site.core.utils.JsonUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResultJson {
    private int status;
    private String message;
    private Object data;

    public boolean isSuccess() {
        return 200 == status;
    }

    public String getMessage() {
        return message;
    }

    public ResultJson setMessage(String message) {
        this.message = I18nUtils.get(message);
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResultJson setData(Object data) {
        this.data = data;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public ResultJson setStatus(int status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }

    public ResultJson addData(String key, Object val) {
        Map map;
        if (null == data) {
            map = new LinkedHashMap<>();
            setData(map);
        } else {
            map = (Map) data;
        }

        map.put(key, val);

        return this;
    }

    /**
     * 请求成功，不需要返回data
     *
     * @return Result
     */
    public static ResultJson success() {
        ResultJson result = new ResultJson();
        result.setStatus(200);
        result.setMessage("common.operate.success");
        return result;
    }

    public static ResultJson successMsg(String message) {
        ResultJson result = new ResultJson();
        result.setStatus(200);
        result.setMessage(message);
        return result;
    }

    public static ResultJson successData(Object data) {
        ResultJson result = new ResultJson();
        result.setStatus(200);
        result.setMessage("common.operate.success");
        result.setData(data);
        return result;
    }

    public static ResultJson success(String message, Object data) {
        ResultJson result = new ResultJson();
        result.setStatus(200);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static ResultJson error() {
        ResultJson result = new ResultJson();
        result.setStatus(0);
        return result;
    }

    public static ResultJson error(Integer code) {
        ResultJson result = new ResultJson();
        result.setStatus(code);
        return result;
    }

    /**
     * 请求失败
     *
     * @param code      错误编号
     * @param message   错误消息
     * @return Result
     */
    public static ResultJson error(Integer code, String message) {
        ResultJson result = new ResultJson();
        result.setStatus(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 请求失败
     *
     * @param code    错误编号
     * @param message 错误消息
     * @param data    错误相关数据
     * @return Result
     */
    public static ResultJson error(Integer code, String message, Object data) {
        ResultJson result = new ResultJson();
        result.setStatus(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

}
