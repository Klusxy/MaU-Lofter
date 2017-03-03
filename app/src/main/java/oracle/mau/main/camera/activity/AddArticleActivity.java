package oracle.mau.main.camera.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.utils.JudgeUtils;

/**
 * Created by 田帅 on 2017/2/28.
 */

public class AddArticleActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv_aa_pic;
    private Uri mImageUri;            //目标图片的Uri
    private String mImagePath;        //目标图片的路径
    private EditText et_aa_article;
    private TextView tv_aa_complete;
    /**
     * 返回码
     */
    public final static int ADDARTICLE_BACKCODE = 10003;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_article;
    }

    @Override
    public void initView() {
        iv_aa_pic = (ImageView) findViewById(R.id.iv_aa_pic);
        iv_aa_pic.setImageURI(mImageUri);
        et_aa_article = (EditText) findViewById(R.id.et_aa_article);
        tv_aa_complete = (TextView) findViewById(R.id.tv_aa_complete);
        tv_aa_complete.setOnClickListener(this);
        /**
         * 得到图片的路径
         */
        mImagePath = getIntent().getStringExtra("tag_image_path");
        mImageUri = Uri.parse(mImagePath);
        String article = getIntent().getStringExtra("article");
        et_aa_article.setText(article);
        if (!JudgeUtils.isEmpty(article)) {
            et_aa_article.setSelection(article.length());//将光标移至文字末尾
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_aa_complete:
                Intent intent = new Intent();
                intent.putExtra("article", et_aa_article.getText().toString());
                setResult(ADDARTICLE_BACKCODE, intent);
                finish();
                break;
        }
    }
}
