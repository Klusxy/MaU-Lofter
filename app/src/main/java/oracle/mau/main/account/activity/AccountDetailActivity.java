package oracle.mau.main.account.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.utils.ImageUtils;

/**
 * Created by 田帅 on 2017/3/10.
 */

public class AccountDetailActivity extends BaseActivity {

    private ImageView iv_user_img;
    private int userId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_account_detail;
    }

    @Override
    public void initView() {
        iv_user_img = (ImageView) findViewById(R.id.iv_account_user_img);
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

    }

    private void updateUI() {
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.caise1);
        Bitmap circleBitmap = ImageUtils.circleBitmap(bitmap);
        iv_user_img.setImageBitmap(circleBitmap);
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
