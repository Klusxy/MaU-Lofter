package oracle.mau.http.parser;

import org.json.JSONException;
import org.json.JSONObject;

import oracle.mau.http.bean.BeanData;
import oracle.mau.http.bean.BeanParser;
import oracle.mau.http.data.CommonData;
import oracle.mau.http.data.UserData;

/**
 * Created by shadow on 2017/2/28.
 */

public class UserParser extends BeanParser {
    @Override
    public BeanData parser(String result) {
        UserData uData = new UserData();
        try {
            JSONObject json = new JSONObject(result);
            int code = json.getInt("code");
            int flag = json.getInt("flag");
            uData.setCode(code);
            uData.setFlag(flag);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return uData;
    }
}
