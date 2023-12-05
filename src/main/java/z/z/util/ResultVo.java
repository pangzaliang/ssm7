package z.z.util;
public class ResultVo<T>{

    // 是否成功
    private Boolean ok;
    // 返回消息
    private String message;
    // 返回结果数据
    private T data;

    // 构造方法
    public ResultVo(Boolean ok, String message, T data) {
        this.ok = ok;
        this.message = message;
        this.data = data;
    }

    // getter and setter
    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 返回成功结果
     * @param message 返回消息
     * @param data 返回数据
     * @return ResultVo<T>
     */
    public static <T> ResultVo<T> successResult(String message, T data) {
        return new ResultVo<>(Boolean.TRUE, message, data);
    }

    /**
     * 返回成功结果
     * @param message 返回消息
     * @return ResultVo<T>
     */
    public static <T> ResultVo<T> successResult(String message) {
        return new ResultVo<>(Boolean.TRUE, message, null);
    }

    /**
     * 返回成功结果
     * @param data 返回数据
     * @return ResultVo<T>
     */
    public static <T> ResultVo<T> successResult(T data) {
        return new ResultVo<>(Boolean.TRUE, "接口调用成功!", data);
    }

    /**
     * 返回失败结果
     * @param message 返回消息
     * @param data 返回数据
     * @return ResultVo<T>
     */
    public static <T> ResultVo<T> failResult(String message, T data) {
        return new ResultVo<>(Boolean.FALSE, message, data);
    }

    /**
     * 返回失败结果
     * @param message 返回消息
     * @return ResultVo<T>
     */
    public static <T> ResultVo<T> failResult(String message) {
        return new ResultVo<>(Boolean.FALSE, message, null);
    }

    /**
     * 返回失败结果
     * @param data 返回数据
     * @return ResultVo<T>
     */
    public static <T> ResultVo<T> failResult(T data) {
        return new ResultVo<>(Boolean.FALSE, "接口调用失败!", data);
    }
}
