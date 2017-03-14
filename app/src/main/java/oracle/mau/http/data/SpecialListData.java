package oracle.mau.http.data;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.entity.SpecialEntity;
import oracle.mau.http.bean.BeanData;

/**
 * Created by shadow on 2017/3/14.
 */

public class SpecialListData extends BeanData {
    private ArrayList<SpecialEntity> specialEntityList;

    public ArrayList<SpecialEntity> getSpecialEntityList() {
        return specialEntityList;
    }

    public void setSpecialEntityList(ArrayList<SpecialEntity> specialEntityList) {
        this.specialEntityList = specialEntityList;
    }
}
