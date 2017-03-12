package oracle.mau.http.data;

import java.util.List;

import oracle.mau.entity.LabelTagEntity;
import oracle.mau.http.bean.BeanData;

/**
 * Created by 田帅 on 2017/3/12.
 */

public class LabelTagData extends BeanData
{
    private List<LabelTagEntity> list;

    public List<LabelTagEntity> getList() {
        return list;
    }

    public void setList(List<LabelTagEntity> list) {
        this.list = list;
    }
}
