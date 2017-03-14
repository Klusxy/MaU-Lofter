package oracle.mau.http.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import oracle.mau.entity.SpecialEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.bean.BeanParser;
import oracle.mau.http.data.SpecialData;
import oracle.mau.http.data.SpecialListData;

/**
 * Created by shadow on 2017/3/14.
 */

public class SpecialListParser extends BeanParser {

    SpecialListData sData=null;
    private ArrayList<String> piclist;
    @Override
    public BeanData parser(String result) {
        JSONArray arrjb;
        sData=new SpecialListData();
        ArrayList<SpecialEntity> listSpecial=new ArrayList<>();
        try {
            arrjb=new JSONArray(result);
            if(arrjb!=null&&arrjb.length()>0){
                for(int i=0;i<arrjb.length();i++){
                    JSONObject special=arrjb.getJSONObject(i);
                    String id=special.getString("id");
                    String title=special.getString("name");
                    String content=special.getString("content");
                    JSONArray arrpic=special.getJSONArray("imgs");
                    piclist=new ArrayList<>();
                    for(int j=0;j<arrpic.length();j++){

                       JSONObject pic=arrpic.getJSONObject(j);
                        String path=pic.getString("img_url");
                        piclist.add(path);

                    }
                    SpecialEntity specialEntity=new SpecialEntity();
                    specialEntity.setSpecialId(Integer.parseInt(id));
                    specialEntity.setSpecialTitle(title);
                    specialEntity.setSpecialContent(content);
                    specialEntity.setPiclist(piclist);

                    listSpecial.add(specialEntity);

                }
            }
        sData.setSpecialEntityList(listSpecial);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sData;
    }
}
