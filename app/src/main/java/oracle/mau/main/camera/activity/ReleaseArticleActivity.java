package oracle.mau.main.camera.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.main.MainActivity;

/**
 * Created by 田帅 on 2017/2/28.
 */

public class ReleaseArticleActivity extends BaseActivity implements View.OnClickListener{
    private final static int ADD_LOCATIONE_CODE = 10001;
    private Uri mImageUri;            //目标图片的Uri
    private String mImagePath;        //目标图片的路径
    /**
     * 修改后的图片缩略图
     */
    private ImageView iv_ra_pic;
    /**
     * 添加位置的线性布局
     */
    private LinearLayout ll_ra_add_location;
    @Override
    public int getLayoutId() {
        return R.layout.activity_release_article;
    }

    @Override
    public void initView() {
        /**
         * 得到图片的路径
         */
        mImagePath = getIntent().getStringExtra("tag_image_path");
        mImageUri = Uri.parse(mImagePath);
        iv_ra_pic = (ImageView) findViewById(R.id.iv_ra_pic);
        iv_ra_pic.setImageURI(mImageUri);
        ll_ra_add_location = (LinearLayout) findViewById(R.id.ll_ra_add_location);
        ll_ra_add_location.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 添加位置
             */
            case R.id.ll_ra_add_location :
                //跳转到添加位置界面
                Intent intent = new Intent(this,AddLocation.class);
                startActivityForResult(intent,ADD_LOCATIONE_CODE);
                break;
        }
    }
}
