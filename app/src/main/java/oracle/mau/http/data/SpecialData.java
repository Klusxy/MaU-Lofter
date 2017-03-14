package oracle.mau.http.data;

import java.util.List;

import oracle.mau.entity.SpecialEntity;
import oracle.mau.http.bean.BeanData;

/**
 * Created by 田帅 on 2017/3/13.
 */

public class SpecialData extends BeanData {
    private List<SpecialEntity> specialEntityList;

    public List<SpecialEntity> getSpecialEntityList() {
        return specialEntityList;
    }

    public void setSpecialEntityList(List<SpecialEntity> specialEntityList) {
        this.specialEntityList = specialEntityList;
    }
}
