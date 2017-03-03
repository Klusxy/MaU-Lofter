package oracle.mau.application;

import android.app.Application;

import cn.smssdk.SMSSDK;
import oracle.mau.entity.UserEntity;

/**
 * Created by 田帅 on 2017/2/14.
 */

public class MaUApplication extends Application {
private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SMSSDK.initSDK(this, "1bb329bbf8c6e", "c9279b59e737ccd1291e9a477e798cfa");


    }
}
