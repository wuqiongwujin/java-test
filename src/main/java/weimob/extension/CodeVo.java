package weimob.extension;

/**
 * @Description
 * @Author Cain
 * @date 2021/5/6
 */
public class CodeVo {
    private String errcode;
    private String errmsg;

    public CodeVo() {
    }

    public String getErrcode() {
        return this.errcode;
    }

    public String getErrmsg() {
        return this.errmsg;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CodeVo)) {
            return false;
        } else {
            CodeVo other = (CodeVo)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$errcode = this.getErrcode();
                Object other$errcode = other.getErrcode();
                if (this$errcode == null) {
                    if (other$errcode != null) {
                        return false;
                    }
                } else if (!this$errcode.equals(other$errcode)) {
                    return false;
                }

                Object this$errmsg = this.getErrmsg();
                Object other$errmsg = other.getErrmsg();
                if (this$errmsg == null) {
                    if (other$errmsg != null) {
                        return false;
                    }
                } else if (!this$errmsg.equals(other$errmsg)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof CodeVo;
    }


    public String toString() {
        return "CodeVo(errcode=" + this.getErrcode() + ", errmsg=" + this.getErrmsg() + ")";
    }
}
