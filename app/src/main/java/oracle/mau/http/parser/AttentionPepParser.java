package oracle.mau.http.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import oracle.mau.entity.UserEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.bean.BeanParser;
import oracle.mau.http.data.AttentionPeopleData;
import oracle.mau.http.data.SpecialListData;

/**
 * Created by shadow on 2017/3/15.
 */

public class AttentionPepParser extends BeanParser {
    AttentionPeopleData aData=null;
    @Override
    public BeanData parser(String result) {
        JSONArray arrjb;
        aData=new AttentionPeopleData();
        ArrayList<UserEntity> userList=new ArrayList<>();
        try {
            arrjb=new JSONArray(result);
            if(arrjb!=null&&arrjb.length()>0){
                for(int i=0;i<arrjb.length();i++){
                    JSONObject people=arrjb.getJSONObject(i);
                    String userid=people.getString("id");
                    String username=people.getString("user_name");
                    String userpic=people.getString("user_img");
                    UserEntity user=new UserEntity();
                    user.setUserid(Integer.parseInt(userid));
                    user.setUsername(username);
                    user.setUserpic(userpic);
                    userList.add(user);

                }
            }
            aData.setAttentionPeoList(userList);
        } catch (JSONException e) {e.printStackTrace();

        }

        return aData;
    }
}

