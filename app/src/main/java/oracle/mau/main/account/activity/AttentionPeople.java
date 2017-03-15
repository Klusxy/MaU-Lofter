package oracle.mau.main.account.activity;

import android.media.Image;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.entity.UserEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.common.Callback;
import oracle.mau.http.common.HttpServer;
import oracle.mau.http.constants.URLConstants;
import oracle.mau.http.data.AttentionPeopleData;
import oracle.mau.http.data.SpecialListData;
import oracle.mau.http.parser.AttentionPepParser;
import oracle.mau.http.parser.SpecialListParser;
import oracle.mau.main.account.adapter.AttentionPeoAdapter;
import oracle.mau.utils.GetTheUser;
import oracle.mau.view.ListViewForScrollView;

/**
 * Created by shadow on 2017/3/15.
 */

public class AttentionPeople extends BaseActivity implements PullToRefreshBase.OnRefreshListener<ScrollView>,AdapterView.OnItemClickListener ,View.OnClickListener{
    private ListViewForScrollView listPeople;
    private ArrayList<UserEntity> theUserList;
    private final int LABEL_MAIN_PULL_UPDATE = 100001;
    private PullToRefreshScrollView mPullRefreshScrollView;
    private String userid;
    private ImageView attentionBack;
    @Override
    public int getLayoutId() {
        return R.layout.activity_attention;
    }

    @Override
    public void initView() {
        listPeople = (ListViewForScrollView) findViewById(R.id.attention_list);
        listPeople.setOnItemClickListener(this);
        mPullRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.ptr_label_main_scrollview);
        mPullRefreshScrollView.smoothScrollTo(0, 0);//将ScrollView滚动至最顶端（自定义的listview影响下的效果）
        mPullRefreshScrollView.setOnRefreshListener(this);
        attentionBack=(ImageView) findViewById(R.id.attention_back);
        attentionBack.setOnClickListener(this);
        sendMessage();
    }

    @Override
    public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
            sendMessage();
    }

    public void sendMessage(){
        UserEntity user= GetTheUser.getUser(AttentionPeople.this);
        String userid=user.getUserid()+"";
        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_GET, null, new AttentionPepParser(), URLConstants.BASE_URL + URLConstants.ATTENTION_PEOPLE+userid, new Callback() {


            @Override
            public void success(BeanData beanData) {
                AttentionPeopleData uData = (AttentionPeopleData) beanData;
                theUserList=uData.getAttentionPeoList();
                AttentionPeoAdapter adapter=new AttentionPeoAdapter(AttentionPeople.this,theUserList);
                listPeople.setAdapter(adapter);

                /**
                 * 下拉刷新返回头部
                 */
                mPullRefreshScrollView.onRefreshComplete();
            }


            @Override
            public void failure(String error) {
                Toast.makeText(AttentionPeople.this,error,Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            AccountDetailActivity.actionStart(AttentionPeople.this,theUserList.get(i).getUserid());
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
