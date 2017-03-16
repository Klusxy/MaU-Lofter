package oracle.mau.main.account.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.entity.FollowEntity;
import oracle.mau.entity.UserEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.common.Callback;
import oracle.mau.http.common.HttpServer;
import oracle.mau.http.constants.URLConstants;
import oracle.mau.http.data.FollowUserData;
import oracle.mau.http.data.UserData;
import oracle.mau.http.parser.FollowParser;
import oracle.mau.http.parser.UserDetailParser;
import oracle.mau.main.label.activity.ArticleDetailActivity;
import oracle.mau.main.label.adapter.LabelMessageArticleGVAdapter;
import oracle.mau.utils.GetTheUser;
import oracle.mau.utils.ImageUtils;
import oracle.mau.utils.JudgeUtils;

/**
 * Created by 田帅 on 2017/3/10.
 */

public class AccountDetailActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private ImageView iv_user_img;
    /**
     * 详情用户id
     */
    private int userId;
    private UserEntity mDetailUser;
    /**
     * 用户自己
     */
    private UserEntity mUser;

    private List<FollowEntity> followUserList;
    private boolean isFollow = false;
    /**
     * 控件
     */
    private TextView tv_account_user_name;
    private TextView tv_fa_article_hiden_flag;
    private GridView gv_aad_article;
    private ImageView iv_aad_back;
    private ShineButton btn_account_user_follow;
    private TextView tv_account_user_follow_flag;


    @Override
    public int getLayoutId() {
        return R.layout.activity_account_detail;
    }

    @Override
    public void initView() {
        iv_user_img = (ImageView) findViewById(R.id.iv_account_user_img);
        tv_account_user_name = (TextView) findViewById(R.id.tv_account_user_name);
        tv_fa_article_hiden_flag = (TextView) findViewById(R.id.tv_fa_article_hiden_flag);
        gv_aad_article = (GridView) findViewById(R.id.gv_aad_article);
        gv_aad_article.setOnItemClickListener(this);
        iv_aad_back = (ImageView) findViewById(R.id.iv_aad_back);
        iv_aad_back.setOnClickListener(this);
        btn_account_user_follow = (ShineButton) findViewById(R.id.btn_account_user_follow);
        btn_account_user_follow.setOnClickListener(this);
        tv_account_user_follow_flag = (TextView) findViewById(R.id.tv_account_user_follow_flag);
        /**
         * 用户自己
         */
        mUser = GetTheUser.getUser(this);
        /**
         * 详情用户id
         */
        userId = getIntent().getIntExtra("userId", 1);
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
                mDetailUser = data.getUserEntity();
                updateUI();
                /**
                 * 请求完用户详情信息之后再去请求关注信息，避免由于异步结果返回的时间不一样导致空指针异常
                 * 请求mUser关注信息
                 */
                requestFollowData();
            }

            @Override
            public void failure(String error) {

            }
        });
    }

    /**
     * 请求关注信息
     */
    private void requestFollowData() {
        String url = URLConstants.BASE_URL + URLConstants.USER_FOLLOWS + mUser.getUserid();
        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_GET, null, new FollowParser(), url, new Callback() {
            @Override
            public void success(BeanData beanData) {
                FollowUserData data = (FollowUserData) beanData;
                followUserList = data.getFollowEntityList();
                /**
                 * 判断是否关注
                 */
                judgeIsFollow();
            }

            @Override
            public void failure(String error) {

            }
        });
    }

    /**
     * 判断是否是关注关系
     */
    private void judgeIsFollow() {
        /**
         * 首先判断是不是用户自己的详情界面
         */
        if (mUser.getUserid() != mDetailUser.getUserid()) {
            //判断是否关注
            if (followUserList != null) {
                for (FollowEntity u : followUserList) {
                    Log.d("dasdasd", "关注好友的id" + u.getFollow_user_id());
                    //判断详情用户的id 是否 在自己的关注好友列表中
                    if (mDetailUser.getUserid() == u.getFollow_user_id()) {
                        isFollow = true;
                        break;
                    }
                }
                updateFollowButton();
            }
        }
    }




    /**
     * 更新除了关注按钮之外的ui界面
     */
    private void updateUI() {
        //头像
        if (mDetailUser != null) {
            if (!JudgeUtils.isEmpty(mDetailUser.getUserpic())) {
                ImageUtils.getBitmapUtils(this).display(iv_user_img, mDetailUser.getUserpic(), new BitmapLoadCallBack<ImageView>() {
                    @Override
                    public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                        Bitmap circleBitmap = ImageUtils.circleBitmap(bitmap);
                        imageView.setImageBitmap(circleBitmap);
                    }

                    @Override
                    public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {

                    }
                });
            }
            tv_account_user_name.setText(mDetailUser.getUsername());
            if (mDetailUser.getArticleEntityList().size() == 0 || mDetailUser.getArticleEntityList() == null) {
                tv_fa_article_hiden_flag.setVisibility(View.VISIBLE);
            } else {
                //初始化文章gv
                initGV();
            }
        }


    }

    /**
     * 更新关注按钮
     */
    private void updateFollowButton() {
        btn_account_user_follow.setVisibility(View.VISIBLE);
        tv_account_user_follow_flag.setVisibility(View.VISIBLE);
        if (isFollow) {
            //关注
            btn_account_user_follow.setBtnColor(Color.RED);
            tv_account_user_follow_flag.setText("已关注");
        } else {
            //未关注
            btn_account_user_follow.setBtnColor(Color.BLACK);
            tv_account_user_follow_flag.setText("未关注");
        }
    }

    private void initGV() {
        /**
         * 设置适配器
         */
        LabelMessageArticleGVAdapter messageRecommendGVAdapter = new LabelMessageArticleGVAdapter(mContext, mDetailUser.getArticleEntityList());
        gv_aad_article.setAdapter(messageRecommendGVAdapter);
    }

    /**
     * 界面启动静态方法
     *
     * @param context
     * @param userId
     */
    public static void actionStart(Context context, int userId) {
        Intent intent = new Intent(context, AccountDetailActivity.class);
        intent.putExtra("userId", userId);
        context.startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ArticleDetailActivity.actionStart(this, mDetailUser.getArticleEntityList().get(position).getArticleId());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_aad_back:
                finish();
                break;
            case R.id.btn_account_user_follow:
                Map<String, Object> map = new HashMap<>();
                map.put("user_id", mUser.getUserid() + "");
                map.put("follew_user_id", mDetailUser.getUserid() + "");
                if (isFollow) {
                    //关注
                    cancleFollow(map);
                } else {
                    follow(map);
                }
                break;
        }
    }

    /**
     * 关注
     */
    private void follow(Map<String, Object> map) {
        String url = URLConstants.BASE_URL + URLConstants.FOLLOW_USER;
        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_POST, map, null, url, new Callback() {
            @Override
            public void success(BeanData beanData) {
                toast("关注成功");
                isFollow = true;
                tv_account_user_follow_flag.setText("已关注");
                btn_account_user_follow.setBtnFillColor(Color.RED);
                btn_account_user_follow.setBtnColor(Color.RED);
//                updateFollowButton();
            }

            @Override
            public void failure(String error) {
                btn_account_user_follow.setBtnFillColor(Color.BLACK);
                btn_account_user_follow.setBtnColor(Color.BLACK);
                toast("关注失败" + error);
            }
        });
    }

    /**
     * 取消关注
     */
    private void cancleFollow(Map<String, Object> map) {
        String url = URLConstants.BASE_URL + URLConstants.CANCLE_FOLLOW_USER;
        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_DELETE, map, null, url, new Callback() {
            @Override
            public void success(BeanData beanData) {
                toast("取消关注成功");
                isFollow = false;
                tv_account_user_follow_flag.setText("未关注");
                btn_account_user_follow.setBtnFillColor(Color.BLACK);
                btn_account_user_follow.setBtnColor(Color.BLACK);
            }

            @Override
            public void failure(String error) {
                toast("取消关注失败");
                btn_account_user_follow.setBtnFillColor(Color.RED);
                btn_account_user_follow.setBtnColor(Color.RED);
            }
        });
    }
}
