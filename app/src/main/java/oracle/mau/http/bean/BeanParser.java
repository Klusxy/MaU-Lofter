package oracle.mau.http.bean;


/**
 * 实现把json字符串转换为java对象
 * Created by 田帅 on 2017/1/3.
 */

public abstract class BeanParser {
    /**
     *
     * @param result  json字符串(从服务器过来的) -->转成java对象
     * @return BeanData :转换之后的java对象
     */
    public abstract BeanData parser(String result);
}
