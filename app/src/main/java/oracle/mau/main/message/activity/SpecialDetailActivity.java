package oracle.mau.main.message.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.entity.SpecialEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.common.Callback;
import oracle.mau.http.common.HttpServer;
import oracle.mau.http.constants.URLConstants;
import oracle.mau.http.data.SpecialListData;
import oracle.mau.http.parser.SpecialListParser;
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
    private ArrayList<SpecialEntity> listSpecial=new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_special_detail;
    }

    @Override
    public void initView() {
        sendMessage();
        btnBack=(ImageView)findViewById(R.id.detail_ad_back);
        btnBack.setOnClickListener(this);
        title=(TextView) findViewById(R.id.tv_ad_user_name_top);

        content=(TextView) findViewById(R.id.sp_detail_content);

        img1=(ImageView) findViewById(R.id.sp_img1);

        img2=(ImageView) findViewById(R.id.sp_img2);

        img3=(ImageView) findViewById(R.id.sp_img3);


    }



    public void sendMessage(){

        Intent intent=getIntent();
        String specialid = intent.getStringExtra("special_id");
;
        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_GET, null, new SpecialListParser(), URLConstants.BASE_URL + URLConstants.gET_SPECIAL_DETAIL+specialid, new Callback() {


            @Override
            public void success(BeanData beanData) {
                SpecialListData uData = (SpecialListData) beanData;
               // Log.d("vvvvv",uData+"");
                listSpecial=uData.getSpecialEntityList();
                specialEntity=listSpecial.get(0);
                title.setText(specialEntity.getSpecialTitle());
                content.setText(specialEntity.getSpecialContent());
                ImageUtils.getBitmapUtils(SpecialDetailActivity.this).display(img1,specialEntity.getPiclist().get(0));
                ImageUtils.getBitmapUtils(SpecialDetailActivity.this).display(img2,specialEntity.getPiclist().get(1));
                ImageUtils.getBitmapUtils(SpecialDetailActivity.this).display(img3,specialEntity.getPiclist().get(2));
            }


            @Override
            public void failure(String error) {
                Toast.makeText(SpecialDetailActivity.this,error,Toast.LENGTH_LONG).show();
            }
        });


    }

    public static  void actionGetid(Context context,String  specialid){
        Intent intentgetDetail=new Intent(context,SpecialDetailActivity.class);
        intentgetDetail.putExtra("special_id",specialid);
        context.startActivity(intentgetDetail);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.detail_ad_back:
                finish();
                break;

        }
    }
}
