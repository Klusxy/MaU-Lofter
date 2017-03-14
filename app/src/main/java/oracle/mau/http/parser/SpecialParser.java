package oracle.mau.http.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.entity.SpecialEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.bean.BeanParser;
import oracle.mau.http.data.SpecialData;

/**
 * Created by 田帅 on 2017/3/13.
 */

public class SpecialParser extends BeanParser {
    @Override
    public BeanData parser(String result) {
        SpecialData data = new SpecialData();
        List<SpecialEntity> specialEntityList = new ArrayList<>();
        try {
            JSONArray arr = new JSONArray(result);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject specialObject = arr.getJSONObject(i);
                String special_id = specialObject.getString("special_id");
                String special_content = specialObject.getString("special_content");
                String special_title = specialObject.getString("special_title");
                String create_time = specialObject.getString("create_time");
                SpecialEntity se = new SpecialEntity();
                se.setSpecialId(Integer.parseInt(special_id));
                se.setSpecialTitle(special_title);
                se.setSpecialContent(special_content);
                se.setSpecialDate(create_time);
                specialEntityList.add(se);
            }
            data.setSpecialEntityList(specialEntityList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}
