package oracle.mau.http.data;

import java.util.ArrayList;

import oracle.mau.entity.UserEntity;
import oracle.mau.http.bean.BeanData;

/**
 * Created by shadow on 2017/3/15.
 */

public class AttentionPeopleData extends BeanData {
    private ArrayList<UserEntity> attentionPeoList;

    public ArrayList<UserEntity> getAttentionPeoList() {
        return attentionPeoList;
    }

    public void setAttentionPeoList(ArrayList<UserEntity> attentionPeoList) {
        this.attentionPeoList = attentionPeoList;
    }
}
