package oracle.mau.http.parser;

import org.json.JSONException;
import org.json.JSONObject;

import oracle.mau.entity.UserEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.bean.BeanParser;
import oracle.mau.http.data.UserData;

/**
 * Created by 田帅 on 2017/3/13.
 */

public class UserParser extends BeanParser {
    @Override
    public BeanData parser(String result) {
        UserData userData = new UserData();
        try {
            JSONObject json = new JSONObject(result);
            JSONObject jsonObject = json.getJSONObject("message");
            String user_id = jsonObject.getString("user_id");
            String user_tel = jsonObject.getString("user_tel");
            String user_pwd = jsonObject.getString("user_pwd");
            String user_name = jsonObject.getString("user_name");
            String user_img = jsonObject.getString("user_img");
            UserEntity e = new UserEntity();
            e.setUserid(Integer.parseInt(user_id));
            e.setUsertel(user_tel);
            e.setUserpwd(user_pwd);
            e.setUsername(user_name);
            e.setUserpic(user_img);
            userData.setUserEntity(e);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userData;
    }
}
