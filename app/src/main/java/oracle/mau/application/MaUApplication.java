package oracle.mau.application;

import android.app.Application;

import entity.UserEntity;

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

    }
}
