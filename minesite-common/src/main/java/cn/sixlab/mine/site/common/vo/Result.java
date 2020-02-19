package cn.sixlab.mine.site.common.vo;

import cn.sixlab.mine.site.common.utils.I18nUtils;
import cn.sixlab.mine.site.common.utils.JsonUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {

    private boolean success;

    private Integer code;

    private String msg;

    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public Result setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = I18nUtils.get(msg);
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }

    public Result addData(String key, Object val) {
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
}
