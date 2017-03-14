package oracle.mau.main.account.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.entity.UserEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.common.Callback;
import oracle.mau.http.common.HttpServer;
import oracle.mau.http.constants.URLConstants;
import oracle.mau.http.data.UserData;
import oracle.mau.http.parser.UserDetailParser;
import oracle.mau.utils.ImageUtils;

/**
 * Created by 田帅 on 2017/3/10.
 */

public class AccountDetailActivity extends BaseActivity {

    private ImageView iv_user_img;
    private int userId;
    private UserEntity mUser;

    /**
     * 控件
     */
    private TextView tv_account_user_name;
    private TextView tv_fa_article_hiden_flag;



    @Override
    public int getLayoutId() {
        return R.layout.activity_account_detail;
    }

    @Override
    public void initView() {
        iv_user_img = (ImageView) findViewById(R.id.iv_account_user_img);
        tv_account_user_name = (TextView) findViewById(R.id.tv_account_user_name);
        tv_fa_article_hiden_flag = (TextView) findViewById(R.id.tv_fa_article_hiden_flag);
        userId = getIntent().getIntExtra("userId",1);
        /**
         * 请求用户信息
         */
        requestData();
    }

    /**
     * 请求用户信息
     */
    private void requestData() {
        String url = URLConstants.BASE_URL + URLConstants.USER_DETAIL + userId;
        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_GET, null, new UserDetailParser(), url, new Callback() {
            @Override
            public void success(BeanData beanData) {
                UserData data = (UserData) beanData;
                mUser = data.getUserEntity();
                updateUI();
            }

            @Override
            public void failure(String error) {

            }
        });
    }

    private void updateUI() {
        //头像
        ImageUtils.getBitmapUtils(this).display(iv_user_img, mUser.getUserpic(), new BitmapLoadCallBack<ImageView>() {
            @Override
            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                Bitmap circleBitmap = ImageUtils.circleBitmap(bitmap);
                imageView.setImageBitmap(circleBitmap);
            }

            @Override
            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {

            }
        });
        tv_account_user_name.setText(mUser.getUsername());
        if (mUser.getArticleEntityList().size()==0 || mUser.getArticleEntityList()==null) {
            tv_fa_article_hiden_flag.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 界面启动静态方法
     * @param context
     * @param userId
     */
    public static void actionStart(Context context , int userId){
        Intent intent = new Intent(context,AccountDetailActivity.class);
        intent.putExtra("userId",userId);
        context.startActivity(intent);
    }

}
