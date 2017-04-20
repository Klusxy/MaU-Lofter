package oracle.mau.main.camera.activity;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.entity.UserEntity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.common.Callback;
import oracle.mau.http.common.HttpServer;
import oracle.mau.http.constants.URLConstants;
import oracle.mau.http.data.PicData;
import oracle.mau.http.parser.PicParser;
import oracle.mau.main.MainActivity;
import oracle.mau.main.camera.constant.PhotoConstant;
import oracle.mau.utils.GetTheUser;
import oracle.mau.utils.JudgeUtils;
import oracle.mau.view.BottomMenuDialog;

/**
 * Created by 田帅 on 2017/2/28.
 */

public class ReleaseArticleActivity extends BaseActivity implements View.OnClickListener {
    private final int ADD_LOCATIONE_CODE = 10001;
    private final int ADD_ARTICLE_CODE = 10002;
    private final int ADD_LABEL_CODE = 10003;
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
    private int labelTypeId = -1;

    /**
     * 单选还是多选
     */
    private String type = "";
    private List<String> imageList;
    private Button btn_ra_image_size;

    /**
     * 顶部取消按钮
     */
    private TextView tv_ra_cancle;
    private BottomMenuDialog bottomMenuDialog;

    /**
     * 发布文章按钮
     */
    private Button btn_ra_bottom_release;
    /**
     * 进度条
     */
    private AVLoadingIndicatorView avi;
    /**
     * 存储上传图片之后响应回来的地址
     */
    private List<String> responcePicList = new ArrayList<>();


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
        Log.d("shadow",mImagePath);
        mImageUri = Uri.parse(mImagePath);
        /**
         * 得到是否多选
         */
        type = getIntent().getStringExtra("type");
        /**
         * 多选情况下，显示几种图片，并将集合list获取到
         */
        if (PhotoConstant.MORE.equals(type)) {
            imageList = getIntent().getStringArrayListExtra("images");
            btn_ra_image_size = (Button) findViewById(R.id.btn_ra_image_size);
            btn_ra_image_size.setVisibility(View.VISIBLE);
            btn_ra_image_size.setText(imageList.size() + "");
        }
        /**
         * 上传图片
         */
        sendPic();
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
        tv_ra_cancle = (TextView) findViewById(R.id.tv_ra_cancle);
        tv_ra_cancle.setOnClickListener(this);
        btn_ra_bottom_release = (Button) findViewById(R.id.btn_ra_bottom_release);
        btn_ra_bottom_release.setOnClickListener(this);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
    }

    /**
     * 上传文件
     */
    private void sendPic() {

        if (imageList == null) {
            //单张自定义图片
            Map<String, Object> map = new HashMap<>();
            map.put("File", mImagePath);
            send(map);
        } else {
            for (int i = 0; i < imageList.size(); i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("File" + i, imageList.get(i));
                Log.d("sdadasdas", "第一张图片地址" + imageList.get(i));
                send(map);
            }
        }

    }

    int flag = 0 ;
    private void send(Map<String, Object> map) {
        PicParser parser = new PicParser();
        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_POST, map, parser, URLConstants.BASE_URL + URLConstants.SEND_ARTICLE_PIC, new Callback() {
            @Override
            public void success(BeanData beanData) {
                PicData picData = (PicData) beanData;
                responcePicList.add(picData.getPicUrl());
                flag++;
            }

            @Override
            public void failure(String error) {

            }
        });
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 添加位置
             */
            case R.id.ll_ra_add_location:
                //跳转到添加位置界面
                Intent locationIntent = new Intent(this, AddLocationActivity.class);
                startActivityForResult(locationIntent, ADD_LOCATIONE_CODE);
                break;
            /**
             * 添加文章
             */
            case R.id.rl_ra_add_article:
                //跳转到添加文字界面
                Intent articleIntent = new Intent(this, AddArticleActivity.class);
                articleIntent.putExtra("tag_image_path", mImagePath);
                if (!JudgeUtils.isEmpty(et_ra_article.getText().toString())) {
                    articleIntent.putExtra("article", et_ra_article.getText().toString());
                }
                startActivityForResult(articleIntent, ADD_ARTICLE_CODE);
                break;
            /**
             * 添加标签
             */
            case R.id.ll_ra_add_label:
                //跳转到添加标签界面
                Intent labelIntent = new Intent(this, AddLabelActivity.class);
                startActivityForResult(labelIntent, ADD_LABEL_CODE);
                break;
            /**
             * 取消按钮
             */
            case R.id.tv_ra_cancle:
                bottomMenuDialog = new BottomMenuDialog.Builder(ReleaseArticleActivity.this)
                        .setTitle("确定放弃编辑吗")
                        .addMenu("不，我点错了", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                bottomMenuDialog.dismiss();
                            }
                        }).addMenu("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                bottomMenuDialog.dismiss();
                                finish();
                            }
                        }).create();

                bottomMenuDialog.show();
                break;
            /**
             * 发布文章
             */
            case R.id.btn_ra_bottom_release:
                sendArticle();
                break;
        }
    }

    /**
     * 发送文章
     */
    private void sendArticle() {
        //内容是否为空
        if (JudgeUtils.isEmpty(et_ra_article.getText().toString())) {
            toast("请输入文章内容");
        } else
            //标签是否为空
            if ("添加标签".equals(tv_ra_label.getText().toString())) {
                toast("请选择文章标签");
            } else
                //位置是否为空
                if ("添加位置".equals(tv_ta_location.getText().toString())) {
                    toast("请添加位置");
                } else if (responcePicList.size()==0) {
                    toast("图片还未上传完成");
                }else {
                    avi.show();
                    Map<String, Object> map = new HashMap<>();
                    UserEntity user = GetTheUser.getUser(this);
                    map.put("user_id", user.getUserid()+"");
                    map.put("article_content", et_ra_article.getText().toString());
                    map.put("article_location", tv_ta_location.getText().toString());
                    map.put("article_tag_id", labelTypeId+"");
                    List<Map<String,String>> imgList = new ArrayList<>();
                    /**
                     * 添加图片地址
                     */
                    for (int i = 0; i<responcePicList.size() ; i++) {
                        Map<String,String> imgMap = new HashMap<>();
                        imgMap.put("img_url",responcePicList.get(i));
                        imgList.add(imgMap);
                    }
                    map.put("MauArticleImg", imgList);
                    HttpServer.sendPostRequest(HttpServer.HTTPSERVER_POST, map, null, URLConstants.BASE_URL + URLConstants.SEND_ARTICLE_CONTENT, new Callback() {
                        @Override
                        public void success(BeanData beanData) {
                            toast("发布成功");
                            avi.hide();
                            finish();
                        }

                        @Override
                        public void failure(String error) {
                            toast("发布失败");
                            avi.hide();
                        }
                    });
                }
    }

    /**
     * 回调
     *
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
            case AddLabelActivity.ADDLABEL_BACKCODE:
                labelTypeId = data.getIntExtra("labelTypeId", -1);
                String label = data.getStringExtra("label");
                tv_ra_label.setText(label);
                break;
        }
    }
}
