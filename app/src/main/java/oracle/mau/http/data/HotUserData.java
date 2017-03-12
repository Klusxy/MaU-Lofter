package oracle.mau.http.data;

import java.util.List;

import oracle.mau.entity.UserEntity;
import oracle.mau.http.bean.BeanData;

/**
 * Created by shadow on 2017/2/28.
 */

public class HotUserData extends BeanData {
    private UserEntity user;

    /**
     * label页六个热门用户
     */
    private List<UserEntity> userList;

    public List<UserEntity> getUserList() {
        return userList;
    }

    public void setUserList(List<UserEntity> userList) {
        this.userList = userList;
    }


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
