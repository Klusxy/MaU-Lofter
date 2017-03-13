package oracle.mau.http.parser;


import org.json.JSONException;
import org.json.JSONObject;

import oracle.mau.http.bean.BeanData;
import oracle.mau.http.bean.BeanParser;
import oracle.mau.http.data.CommonData;


/**
 * Created by 田帅 on 2017/2/8.
 */

public class CommonParser extends BeanParser {
    @Override
    public BeanData parser(String result) {
            CommonData commonData = new CommonData();
        try {
            JSONObject json = new JSONObject(result);
            int status = json.getInt("status");
            String message = json.getString("message");
            commonData.setStatus(status);
            commonData.setMessage(message);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return commonData;
    }
}
