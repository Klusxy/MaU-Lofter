package oracle.mau.http.common;


import oracle.mau.http.bean.BeanData;

/**
 * 实现把解析结果给界面
 * Created by 田帅 on 2017/1/3.
 */

public interface Callback {
    /**
     * 把解析结果传给界面
     * @param beanData
     */
    void success(BeanData beanData);

    /**
     * 访问失败方法
     * @param error
     */
    void failure(String error);
}
