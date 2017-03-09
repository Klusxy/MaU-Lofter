package oracle.mau.application;

import android.app.Application;
import android.content.SharedPreferences;

import cn.smssdk.SMSSDK;
import oracle.mau.entity.UserEntity;
import oracle.mau.main.message.mqtt.PushService;

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

        startMqttServer();

    }

    private void startMqttServer() {
        SharedPreferences.Editor editor = getSharedPreferences(PushService.TAG,
                MODE_PRIVATE).edit();
        editor.putString(PushService.PREF_DEVICE_ID, "123");
        editor.commit();
        PushService.actionStart(getApplicationContext());}
}
