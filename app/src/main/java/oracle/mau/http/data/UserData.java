package oracle.mau.http.data;

import oracle.mau.entity.UserEntity;
import oracle.mau.http.bean.BeanData;

/**
 * Created by shadow on 2017/2/28.
 */

public class UserData extends BeanData {
    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
