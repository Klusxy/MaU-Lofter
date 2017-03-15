package oracle.mau.http.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.entity.FollowEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.bean.BeanParser;
import oracle.mau.http.data.FollowUserData;

/**
 * Created by 田帅 on 2017/3/15.
 */

public class FollowParser extends BeanParser {
    @Override
    public BeanData parser(String result) {
        FollowUserData data = new FollowUserData();
        List<FollowEntity> list = new ArrayList<>();
        try {
            JSONArray arr = new JSONArray(result);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject userObject = arr.getJSONObject(i);
                String follow_user_id = userObject.getString("id");
                FollowEntity entity = new FollowEntity();
                entity.setFollow_user_id(Integer.parseInt(follow_user_id));
                list.add(entity);
            }
            data.setFollowEntityList(list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}
