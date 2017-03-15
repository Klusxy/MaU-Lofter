package oracle.mau.http.data;

import java.util.List;

import oracle.mau.entity.FollowEntity;
import oracle.mau.http.bean.BeanData;

/**
 * Created by 田帅 on 2017/3/15.
 */

public class FollowUserData extends BeanData {
    private List<FollowEntity> followEntityList;

    public List<FollowEntity> getFollowEntityList() {
        return followEntityList;
    }

    public void setFollowEntityList(List<FollowEntity> followEntityList) {
        this.followEntityList = followEntityList;
    }
}
