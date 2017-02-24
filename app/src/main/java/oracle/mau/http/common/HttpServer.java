package oracle.mau.http.common;

/**
 * 实现通过http协议向服务器发送消息
 * 异步
 * Created by 田帅 on 2017/2/8.
 */

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.PreferencesCookieStore;

import java.io.File;
import java.util.Map;

import oracle.mau.http.bean.BeanData;
import oracle.mau.http.bean.BeanParser;


public class HttpServer {
    /**
     * 向服务器发送消息超时时间
     */
    private final static int TIMEOUT = 5000;
    /**
     * 设置请求响应编码
     */
    private final static String CHARSET = "UTF-8";
    /**
     * 用来实现携带cookie
     * (浏览器中：访问服务器的时候，往往会保存30分钟)
     */
    public static PreferencesCookieStore preferencesCookieStore;

    /**
     * 实现向服务器发送请求
     *
     * @param map:实现向服务器传值
     * @param parser:解析器（{flag:1002,code:99292==>java对象）
     * @param url:接口地址
     * @param callback:作用是用来实现把解析之后的结果传给界面               如何将解析好的值传回给界面
     *                                                   不能改void  因为网络访问时异步的
     *                                                   用接口回调方法
     *                                                   参数->解析->callback
     */
    public static void sendPostRequest(Map<String, Object> map, final BeanParser parser, String url, final Callback callback) {
        //创建一个参数对象，用来存储需要传递的参数,如果想把值传给服务器，那么就必须把值存在RequestParams中
        RequestParams rp = new RequestParams();
        if (map != null) {
            for (String key : map.keySet()) {
                //判断请求的map中包含不包含文件
                if (key.contains("File")) {
                    rp.addBodyParameter(key, new File((String) map.get(key)));
                } else {
                    rp.addBodyParameter(key, (String) map.get(key));
                }
            }
        }
        //该对象用来向服务器发送请求
        HttpUtils httpUtils = new HttpUtils();

        //设置请求
        //cookie
        if (preferencesCookieStore != null) {
            httpUtils.configCookieStore(preferencesCookieStore);
        }
        //文本编码
        httpUtils.configResponseTextCharset(CHARSET);
        //超时
        httpUtils.configTimeout(TIMEOUT);

        //向服务器发送请求方法
        httpUtils.send(HttpRequest.HttpMethod.POST, url, rp, new RequestCallBack<String>() {
            //发送请求成功
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //responseInfo.result  就是json字符串
                //从服务器返回来的
                String json = responseInfo.result;
                //解析
                BeanData beandata = null;
                if (parser != null) {
                    beandata = parser.parser(json);
                }
                //传给界面
                if (callback != null) {
                    callback.success(beandata);
                }
            }

            //发送请求失败
            @Override
            public void onFailure(HttpException e, String s) {
                //匿名对象访问方法参数要求是final
                //将失败信息传给callback
                callback.failure(s);
            }
        });
    }
}
