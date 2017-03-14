package oracle.mau.http.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.entity.LabelTagEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.bean.BeanParser;
import oracle.mau.http.data.LabelTagData;

/**
 * Created by 田帅 on 2017/3/13.
 */

public class TagParser extends BeanParser {
    @Override
    public BeanData parser(String result) {
        LabelTagData data = new LabelTagData();
        List<LabelTagEntity> tagEntityList = new ArrayList<>();
        try {
            JSONArray arr = new JSONArray(result);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject tagObject = arr.getJSONObject(i);
                String tag_id = tagObject.getString("tag_id");
                String tag_name = tagObject.getString("tag_name");
                LabelTagEntity tagEntity = new LabelTagEntity();
                tagEntity.setTagId(Integer.parseInt(tag_id));
                tagEntity.setTagTitle(tag_name);
                tagEntityList.add(tagEntity);
            }
            data.setList(tagEntityList);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }
}
