package oracle.mau.utils;

import android.app.Activity;
import android.content.Context;

import oracle.mau.application.MaUApplication;
import oracle.mau.entity.UserEntity;


/**
 * Created by shadow on 2017/3/12.
 */

public class GetTheUser {
    public static UserEntity getUser(Activity activity){
        MaUApplication app=(MaUApplication)activity.getApplication();
        UserEntity user=app.getUser();
        return  user;

    }
}
