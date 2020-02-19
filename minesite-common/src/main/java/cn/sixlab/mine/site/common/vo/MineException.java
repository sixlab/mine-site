package cn.sixlab.mine.site.common.vo;

public class MineException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    // 异常编号
    private Integer code;

    // 错误消息
    private String msg;

    public MineException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public MineException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public MineException(Throwable cause) {
        super(cause);
    }

    public static MineException error(Integer code, String msg) {
        return new MineException(code, msg);
    }

    public static MineException error(String msg) {
        return new MineException(msg);
    }

    public Integer getCode() {
        return code;
    }

    public MineException setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public MineException setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
