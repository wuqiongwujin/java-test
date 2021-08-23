package weimob.extension;

/**
 * @Description
 * @Author Cain
 * @date 2021/5/6
 */
public class BaseResponse<T> {
    private CodeVo code;
    private T data;

    public BaseResponse() {
    }

    public CodeVo getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public void setCode(CodeVo code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    protected boolean canEqual(Object other) {
        return other instanceof BaseResponse;
    }

    public String toString() {
        return "BaseResponse(code=" + this.getCode() + ", data=" + this.getData() + ")";
    }
}
