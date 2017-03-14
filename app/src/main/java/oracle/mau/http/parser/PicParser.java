package oracle.mau.http.parser;

import org.json.JSONException;
import org.json.JSONObject;

import oracle.mau.http.bean.BeanData;
import oracle.mau.http.bean.BeanParser;
import oracle.mau.http.data.PicData;

/**
 * Created by 田帅 on 2017/3/13.
 */

public class PicParser extends BeanParser {
    @Override
    public BeanData parser(String result) {
        PicData data = new PicData();
        try {
            JSONObject jsonObject = new JSONObject(result);
            String picUrl = jsonObject.getString("message");
            data.setPicUrl(picUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}
