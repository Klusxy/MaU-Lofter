package oracle.mau.main.message.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.entity.SpecialEntity;
import oracle.mau.utils.ImageUtils;

/**
 * Created by shadow on 2017/3/14.
 */

public class SpecialDetailActivity extends BaseActivity implements View.OnClickListener{
    private ImageView btnBack;
    private TextView title;
    private TextView content;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private Button btnOrder;
    private SpecialEntity specialEntity;
    @Override
    public int getLayoutId() {
        return R.layout.activity_special_detail;
    }

    @Override
    public void initView() {
        Intent intent=getIntent();
        specialEntity=(SpecialEntity) intent.getSerializableExtra("specialEntity");
        btnBack=(ImageView)findViewById(R.id.iv_ad_back);
        title=(TextView) findViewById(R.id.tv_ad_user_name_top);
        title.setText(specialEntity.getSpecialTitle());
        content=(TextView) findViewById(R.id.sp_detail_content);
        content.setText(specialEntity.getSpecialContent());
        img1=(ImageView) findViewById(R.id.sp_img1);
        ImageUtils.getBitmapUtils(this).display(img1,specialEntity.getPiclist().get(0));
        img2=(ImageView) findViewById(R.id.sp_img2);
        ImageUtils.getBitmapUtils(this).display(img2,specialEntity.getPiclist().get(1));
        img3=(ImageView) findViewById(R.id.sp_img3);
        ImageUtils.getBitmapUtils(this).display(img3,specialEntity.getPiclist().get(2));
        btnOrder=(Button)findViewById(R.id.sp_detailbtn_order);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_ad_back:
                finish();
                break;
            case R.id.sp_detailbtn_order:
                break;
        }
    }
}
