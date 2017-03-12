package oracle.mau.http.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import oracle.mau.http.bean.BeanData;
import oracle.mau.http.bean.BeanParser;
import oracle.mau.http.data.LabelTagData;

/**
 * Created by 田帅 on 2017/3/12.
 */

public class LabelTagParser extends BeanParser {
    @Override
    public BeanData parser(String result) {
        LabelTagData data = new LabelTagData();
        try {
            JSONArray arr = new JSONArray(result);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject jsonObject = arr.getJSONObject(i);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}
