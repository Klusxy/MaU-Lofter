package oracle.mau.main;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wang.avi.AVLoadingIndicatorView;
import com.yongchun.library.view.ImageSelectorActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.common.Callback;
import oracle.mau.http.common.HttpServer;
import oracle.mau.main.account.AccountFragment;
import oracle.mau.main.camera.activity.CropActivity;
import oracle.mau.main.camera.activity.ReleaseArticleActivity;
import oracle.mau.main.camera.constant.PhotoConstant;
import oracle.mau.main.camera.utils.FileUtils;
import oracle.mau.main.home.HomeFragment;
import oracle.mau.main.label.fragment.LabelFragment;
import oracle.mau.main.message.MessageFragment;
import oracle.mau.view.BottomMenuDialog;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private RadioGroup rg_main;
    private RadioButton rb_main_label;
    //判断是否第一次加载界面
    private boolean isFirst = true;
    /**
     * 照相机模块
     */
    //照相机按钮
    private Button btn_main_camera;
    private BottomMenuDialog bottomMenuDialog;
    //照片存储地址，这个照片没有添加标签
    public static final String IMAGE_SAVE = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Camera";
    public final static String IMAGE_URI = "iamge_uri";
    public final static String CROP_IMAGE_URI = "crop_image_uri";

    public Uri mCameraImageUri;
    //	public Uri mImageUri;

    public final static int REQ_CODE_GALLERY = 201;
    public final static int REQ_CODE_CAMERA = 203;
    public final static int REQ_CODE_PHOTO_CROP = 102;

//    private AVLoadingIndicatorView avi;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        rg_main.setOnCheckedChangeListener(this);
        rb_main_label = (RadioButton) findViewById(R.id.rb_main_label);
        btn_main_camera = (Button) findViewById(R.id.btn_main_camera);
        btn_main_camera.setOnClickListener(this);
//        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
//        avi.show();
        //设置UI
        configUI();
        //配置camera模块相关内容
        configCamera();
//        Map<String,Object> map = new HashMap<>();
//        map.put("id","2");
//        map.put("UserImg","string");
//        map.put("UserName","string");
//        map.put("UserPwd","string");
//        map.put("UserTel","string");
//        String url = "http://115.159.0.152:8080/v1/mau_user/";
//        String url = "http://115.159.0.152:8080/v1/mau_article_tag";
//        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_GET, null, null, url, new Callback() {
//            @Override
//            public void success(BeanData beanData) {
//
//            }
//
//            @Override
//            public void failure(String error) {
//
//            }
//        });
    }

    /**
     * activity界面初始化时，配置camera模块相关内容
     */
    private void configCamera() {
        //创建App缓存文件夹
        PhotoConstant.CACHEPATH = new FileUtils(this).makeAppDir();
        Display display = getWindowManager().getDefaultDisplay();
        PhotoConstant.displayWidth = display.getWidth();
        PhotoConstant.displayHeight = display.getHeight();
        PhotoConstant.scale = getResources().getDisplayMetrics().density;
    }

    /**
     * intent跳转静态方法
     *
     * @param context 上下文
     *                测试
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    /**
     * 初始化之后设置界面
     */
    private void configUI() {
        if (isFirst) {
            rb_main_label.setSelected(true);
            replaceFragment(new LabelFragment(), R.id.fl_main_fragment);
            isFirst = false;
        }
    }

    /**
     * 替换碎片方法
     *
     * @param fg 新碎片
     * @param id 被替换的layout
     */
    public void replaceFragment(Fragment fg, int id) {
        //得到碎片管理器
        FragmentManager fm = getSupportFragmentManager();
        //开启一个事物
        FragmentTransaction ft = fm.beginTransaction();
        //1、替换哪块地方(要替换的那个视图的id)  2、替换的值
        ft.replace(id, fg);
        //提交
        ft.commit();
    }

    /**
     * 底部标签按钮点击事件
     *
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_main_home:
                replaceFragment(new HomeFragment(), R.id.fl_main_fragment);
                break;
            case R.id.rb_main_label:
                replaceFragment(new LabelFragment(), R.id.fl_main_fragment);
                break;
            case R.id.rb_main_message:
                replaceFragment(new MessageFragment(), R.id.fl_main_fragment);
//                avi.hide();
                break;
            case R.id.rb_main_account:
                replaceFragment(new AccountFragment(), R.id.fl_main_fragment);
                break;
        }
    }

    /**
     * 按钮点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_camera:
                openDialog();
                break;
        }
    }

    /**
     * 打开弹出框
     */
    private void openDialog() {
        bottomMenuDialog = new BottomMenuDialog.Builder(MainActivity.this)
                .setTitle("选择图片")
                .addMenu("发布自定义图片文章", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomMenuDialog.dismiss();
                        openOnePhotoLib();
                    }
                }).addMenu("发布多张图片文章", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomMenuDialog.dismiss();
                        openMorePhotoLib();
                    }
                }).addMenu("拍一张", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomMenuDialog.dismiss();
                        openCamera();
                    }
                }).create();

        bottomMenuDialog.show();
    }

    /**
     * 选取多张图片
     */
    private void openMorePhotoLib() {
        /**
         * 参数分别代表
         * 最多可选择图片数（int），
         * 单选多选（int），model可以设置ImageSelectorActivity.MODE_MULTIPLE和mageSelectorActivity.MODE_SINGLE
         *
         * 是否显示拍照选项（boolean），
         * 是否显示预览（boolean），
         * 是否裁剪（boolean）等
         *
         */
        ImageSelectorActivity.start(this, 9, ImageSelectorActivity.MODE_MULTIPLE, true, true, true);
    }

    /**
     * 打开照相机
     */
    private void openCamera() {
        Intent localIntent1 = new Intent("android.media.action.IMAGE_CAPTURE");
        //设置相机图片的输出路径
        mCameraImageUri = Uri.fromFile(new File(IMAGE_SAVE, new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()) + ".jpg"));
        localIntent1.putExtra("output", mCameraImageUri);
        MainActivity.this.startActivityForResult(localIntent1, REQ_CODE_CAMERA);
    }

    /**
     * 打开系统图库
     */
    private void openOnePhotoLib() {
        Intent localIntent2 = new Intent();
        localIntent2.setType("image/*");
        localIntent2.setAction("android.intent.action.GET_CONTENT");
        MainActivity.this.startActivityForResult(Intent.createChooser(localIntent2, "选择照片"), REQ_CODE_GALLERY);
    }

    /**
     * intent回传
     * 打开图库之后回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            /**
             * 单选之后
             */
            if (requestCode == REQ_CODE_GALLERY) {
                Uri localUri = data.getData();
                if (localUri == null) {
                    return;
                }
                readLocalImage(localUri);
            }
            /**
             * 照相机
             */
            else if (requestCode == REQ_CODE_CAMERA) {
                // 从相机返回,从设置相机图片的输出路径中提取数据
                readLocalImage(mCameraImageUri);
            }
            /**
             * 多选照片
             */
            else if (requestCode == ImageSelectorActivity.REQUEST_IMAGE) {
                ArrayList<String> images = (ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT);
                Log.d("sdadasdas", "list长度   返回来的长度" + images.size() + "  " + images.size());
                Intent intent = new Intent(this, ReleaseArticleActivity.class);
                intent.putExtra("tag_image_path", images.get(0));
                intent.putExtra("type",PhotoConstant.MORE);
                intent.putStringArrayListExtra("images",images);
                startActivity(intent);
            }
        }
    }
    //选择照片后进行图片裁剪
    private void readLocalImage(Uri uri) {
        if (uri != null) {
            startPhotoCrop(uri, null, REQ_CODE_PHOTO_CROP); // 图片裁剪
        }
    }
    /**
     * 开始裁剪
     *
     * @param uri
     * @param duplicatePath
     * @param reqCode
     * @return void
     * @Title: startPhotoCrop
     * @date 2012-12-12 上午11:15:38
     */
    private void startPhotoCrop(Uri uri, String duplicatePath, int reqCode) {
        Intent intent = new Intent(this, CropActivity.class);//跳转到裁剪界面
        intent.putExtra(IMAGE_URI, uri);
        startActivityForResult(intent, reqCode);
    }
}
