package oracle.mau.http.data;

import oracle.mau.entity.UserEntity;
import oracle.mau.http.bean.BeanData;

/**
 * Created by 田帅 on 2017/3/13.
 */

public class UserData extends BeanData {
    private UserEntity userEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
