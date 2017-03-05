package oracle.mau.main.camera.activity;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.utils.JudgeUtils;

/**
 * Created by 田帅 on 2017/2/28.
 */

public class ReleaseArticleActivity extends BaseActivity implements View.OnClickListener{
    private final  int ADD_LOCATIONE_CODE = 10001;
    private final  int ADD_ARTICLE_CODE = 10002;
    private final  int ADD_LABEL_CODE = 10003;
    private Uri mImageUri;            //目标图片的Uri
    private String mImagePath;        //目标图片的路径
    /**
     * 修改后的图片缩略图
     */
    private ImageView iv_ra_pic;
    /**
     * 添加位置
     */
    private LinearLayout ll_ra_add_location;
    private TextView tv_ta_location;
    /**
     * 添加文章
     */
    private RelativeLayout rl_ra_add_article;
    private EditText et_ra_article;

    /**
     * 添加标签
     */
    private LinearLayout ll_ra_add_label;
    private TextView tv_ra_label;

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
        tv_ta_location = (TextView) findViewById(R.id.tv_ta_location);
        rl_ra_add_article = (RelativeLayout) findViewById(R.id.rl_ra_add_article);
        rl_ra_add_article.setOnClickListener(this);
        et_ra_article = (EditText) findViewById(R.id.et_ra_article);
        ll_ra_add_label = (LinearLayout) findViewById(R.id.ll_ra_add_label);
        ll_ra_add_label.setOnClickListener(this);
        tv_ra_label = (TextView) findViewById(R.id.tv_ra_label);
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 添加位置
             */
            case R.id.ll_ra_add_location :
                //跳转到添加位置界面
                Intent locationIntent = new Intent(this,AddLocationActivity.class);
                startActivityForResult(locationIntent,ADD_LOCATIONE_CODE);
                break;
            /**
             * 添加文章
             */
            case R.id.rl_ra_add_article :
                //跳转到添加文字界面
                Intent articleIntent = new Intent(this,AddArticleActivity.class);
                articleIntent.putExtra("tag_image_path",mImagePath);
                if (!JudgeUtils.isEmpty(et_ra_article.getText().toString())){
                    articleIntent.putExtra("article",et_ra_article.getText().toString());
                }
                startActivityForResult(articleIntent,ADD_ARTICLE_CODE);
                break;
            /**
             * 添加标签
             */
            case R.id.ll_ra_add_label:
                //跳转到添加标签界面
                Intent labelIntent = new Intent(this,AddLabelActivity.class);
                startActivityForResult(labelIntent,ADD_LABEL_CODE);
                break;
        }
    }

    /**
     * 回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            /**
             * 添加位置返回结果
             */
            case AddLocationActivity.ADDLOCATION_BACKCODE:
                tv_ta_location.setText(data.getStringExtra("location"));
                break;
            /**
             * 添加文字返回结果
             */
            case AddArticleActivity.ADDARTICLE_BACKCODE:
                et_ra_article.setText(data.getStringExtra("article"));
                et_ra_article.setSelection(data.getStringExtra("article").length());//将光标移至文字末尾
                break;
            /**
             * 添加标签返回结果
             */
            case AddLabelActivity.ADDLABEL_BACKCODE :
                int labelTypeId = data.getIntExtra("labelTypeId",-1);
                String label = data.getStringExtra("label");
                tv_ra_label.setText(label);
                break;
        }
    }
}
