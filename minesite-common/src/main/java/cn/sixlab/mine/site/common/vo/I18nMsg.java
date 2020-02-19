package cn.sixlab.mine.site.common.vo;

import cn.sixlab.mine.site.common.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class I18nMsg {
    // i18n 消息的编号
    private String code;

    // i18n 消息的参数
    private List<Object> args;

    /**
     * 国际化编号构造方法
     *
     * @param code 国际化编号
     */
    public I18nMsg(String code) {
        this.code = code;
        this.args = new ArrayList<>();
    }

    /**
     * 国际化编号 + 国际化参数 构造方法
     *
     * @param code 国际化编号
     * @param args 国际化参数
     */
    public I18nMsg(String code, List<Object> args) {
        this.code = code;
        this.args = args;
    }

    /**
     * 将消息体转成 json 字符串
     *
     * @return I18nMsg
     */
    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Object> getArgs() {
        return args;
    }

    public void setArgs(List<Object> args) {
        this.args = args;
    }

    /**
     * 往国际化参数的map里边，添加一个参数
     *
     * @param argv 消息的一个参数
     * @return I18nMsg
     */
    public I18nMsg add(Object argv) {
        args.add(argv);
        return this;
    }

    public I18nMsg addAll(Object... args) {
        this.args = Arrays.asList(args);
        return this;
    }

    /**
     * 静态国际化参数构造方法
     *
     * @param code 消息的编号
     * @return I18nMsg
     */
    public static I18nMsg of(String code) {
        return new I18nMsg(code);
    }
}
