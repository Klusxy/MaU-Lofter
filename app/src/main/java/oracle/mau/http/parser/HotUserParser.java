package oracle.mau.http.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.entity.UserEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.bean.BeanParser;
import oracle.mau.http.data.HotUserData;

/**
 * Created by shadow on 2017/2/28.
 */

public class HotUserParser extends BeanParser {
    @Override
    public BeanData parser(String result) {
        HotUserData uData = new HotUserData();
        try {
            JSONArray arr = new JSONArray(result);

            List<UserEntity> list = new ArrayList<>();

            for (int i = 0 ;i<arr.length() ; i++) {
                JSONObject jsonObject = arr.getJSONObject(i);
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
                list.add(e);
            }
            uData.setUserList(list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return uData;
    }
}
