package oracle.mau.http.bean;

/**
 * 所有数据类的父类
 * Created by 田帅 on 2017/1/3.
 */

public abstract class BeanData {
    private int flag;
    private int code;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
