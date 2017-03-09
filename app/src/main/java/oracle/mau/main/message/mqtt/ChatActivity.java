package oracle.mau.main.message.mqtt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.mau.R;
import oracle.mau.application.MaUApplication;
import oracle.mau.base.BaseActivity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.common.Callback;
import oracle.mau.http.common.HttpServer;
import oracle.mau.http.constants.URLConstants;

/**
 * Created by shadow on 2017/3/6.
 */

public class ChatActivity extends BaseActivity implements View.OnClickListener {
    private ImageView imgback;
    private TextView txttitle;
    private ImageView imguser;
    private EditText edittext;
    private Button btnsend;
    private ListView listView;
    private int id;//当前用户的id

    @Override
    public int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    public void initView() {
        imgback=(ImageView) findViewById(R.id.iv_chat_back);
        imgback.setOnClickListener(this);
        txttitle=(TextView) findViewById(R.id.tv_chat_title);
        imguser=(ImageView) findViewById(R.id.iv_chat_voice);
        edittext=(EditText) findViewById(R.id.et_chat_content);
        btnsend=(Button)findViewById(R.id.btn_chat_send);
        btnsend.setOnClickListener(this);
        listView=(ListView) findViewById(R.id.lv_chat);
        MaUApplication app=(MaUApplication)getApplication();
       id= app.getUser().getUserid();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_chat_back:
                finish();
                break;
            case R.id.btn_chat_send:
                break;
        }
    }
}
