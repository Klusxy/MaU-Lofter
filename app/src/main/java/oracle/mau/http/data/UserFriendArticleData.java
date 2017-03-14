package oracle.mau.http.data;

import java.util.List;

import oracle.mau.entity.UserFriendArticleEntity;
import oracle.mau.http.bean.BeanData;

/**
 * Created by 田帅 on 2017/3/14.
 */

public class UserFriendArticleData extends BeanData {
    private List<UserFriendArticleEntity> userFriendArticleEntityList;

    public List<UserFriendArticleEntity> getUserFriendArticleEntityList() {
        return userFriendArticleEntityList;
    }

    public void setUserFriendArticleEntityList(List<UserFriendArticleEntity> userFriendArticleEntityList) {
        this.userFriendArticleEntityList = userFriendArticleEntityList;
    }
}
