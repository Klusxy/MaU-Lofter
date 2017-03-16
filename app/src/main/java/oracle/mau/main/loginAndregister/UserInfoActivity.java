package oracle.mau.main.loginAndregister;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.http.bean.BeanData;
import oracle.mau.http.common.Callback;
import oracle.mau.http.common.HttpServer;
import oracle.mau.http.constants.URLConstants;
import oracle.mau.http.data.HotUserData;
import oracle.mau.http.data.PicData;
import oracle.mau.http.parser.HotUserParser;
import oracle.mau.http.parser.PicParser;
import oracle.mau.main.MainActivity;
import oracle.mau.utils.CameraUtil;
import oracle.mau.utils.ImageUtils;
import oracle.mau.view.BottomMenuDialog;

/**
 * Created by shadow on 2017/2/28.
 */

public class UserInfoActivity extends BaseActivity implements View.OnClickListener {
    private ImageView userimg;
    private EditText editname;
    private EditText editpwd;
    private EditText edityespwd;
    private TextView btnOk;
    private String userTel;
    private String backPath = "";
    private BottomMenuDialog bottomMenuDialog;

    /**
     * 默认头像地址为这个
     */
    private String imgPath = "http://omjbpdxi4.bkt.clouddn.com/touxiang.png";
    //照片在手机中的位置
    private String imgTelPath = "";

    /**
     * 照相机
     * @return
     */
    public Uri mCameraImageUri;
    public static final String IMAGE_SAVE = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Camera";

    /**
     * 把图片设置为圆形
     * @return
     */
    private Bitmap circle=null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_userinfo;
    }

    @Override
    public void initView() {

        userimg = (ImageView) findViewById(R.id.imageButton1);
        userimg.setOnClickListener(this);
        editname = (EditText) findViewById(R.id.edit_reg_name);
        editpwd = (EditText) findViewById(R.id.edit_reg_pwd);
        edityespwd=(EditText)findViewById(R.id.edit_reg_yes_pwd);
        btnOk = (TextView) findViewById(R.id.btn_reg_button);
        btnOk.setOnClickListener(this);

        Intent intent = getIntent();
        userTel = intent.getStringExtra("usertel");
        updateUserImg(imgPath);
    }


    /**
     * 更新用户头像
     *
     * @param
     */
    private void updateUserImg(String imgTelPath) {
        ImageUtils.getBitmapUtils(UserInfoActivity.this).display(userimg, imgTelPath, new BitmapLoadCallBack<ImageView>() {
            @Override
            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                Bitmap bm = ImageUtils.circleBitmap(bitmap);
                imageView.setImageBitmap(bm);
            }

            @Override
            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {

            }
        });
        Log.d("ooo",imgTelPath);
        /**
         * 判断是否是默认照片
         */
        if (!imgTelPath.equals(imgPath)) {
            /**
             * 发送照片到服务器
             */

            sendPic(imgTelPath);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton1:
                openDialog();
                break;
            case R.id.btn_reg_button:
                if(editpwd.getText().toString().equals(edityespwd.getText().toString())){
                    sendMessage();
                }else{
                    toast("两次密码输入冲突");
                }

                break;
        }
    }

    /**
     * 打开弹出框
     */
    private void openDialog() {
        bottomMenuDialog = new BottomMenuDialog.Builder(UserInfoActivity.this)
                .setTitle("设置头像")
                .addMenu("打开图库", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomMenuDialog.dismiss();
                        openOnePhotoLib();
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
     * 打开照相机
     */
    /**
     * 打开照相机
     */
    private void openCamera() {
        Intent localIntent1 = new Intent("android.media.action.IMAGE_CAPTURE");
        //设置相机图片的输出路径
        mCameraImageUri = Uri.fromFile(new File(IMAGE_SAVE, new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()) + ".jpg"));
        localIntent1.putExtra("output", mCameraImageUri);
        startActivityForResult(localIntent1, 2);
    }

    /**
     * 打开系统图库
     */
    private void openOnePhotoLib() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }


    /**
     * 用于接收用户选择完的头像
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 系统图库
         */
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                //得到所有公开的内容提供者
                ContentResolver con = getContentResolver();
                //利用uri去查  得到cursor
                Cursor cur = con.query(uri, null, null, null, null);
                if(cur!=null)
                {
                    if (cur.moveToNext()) {
                        imgTelPath = cur.getString(1);
                        updateUserImg(imgTelPath);

/**
 * 有点问题，不知道为什么再updateUserImg中将选好的图片放到当前页面，
 * 放不上去，所以在此处，又做了一次，图片的放置。
 */
                       circle= ImageUtils.circleBitmap(BitmapFactory.decodeFile(imgTelPath));
                        userimg.setImageBitmap( circle);
                    }
                }else{
                    String sub=data.getDataString();
                    imgTelPath=sub.split("///")[1];
                    updateUserImg(imgTelPath);
                    circle= ImageUtils.circleBitmap(BitmapFactory.decodeFile(imgTelPath));
                    userimg.setImageBitmap( circle);

                }
            }
        }
        /**
         * 照相机
         */
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                if (mCameraImageUri!=null) {
                    imgTelPath = getRealFilePath(this,mCameraImageUri);
                    updateUserImg(imgTelPath);
                }
            }
        }
    }

    private void oldCode() {
        //        if (requestCode == 3) {
//
//            //Bitmap bb=	BitmapFactory.decodeFile("file:///storage/emulated/0/DCIM/Camera/IMG_20170210_170034.jpg");
//            if (resultCode == RESULT_OK) {
//
//				/* 代码区 */
//
//                Uri uri = data.getData();
//                Cursor c = getContentResolver().query(uri, null,
//                        null, null, null);
//                if(c!=null){
//                    if (c.moveToNext()) {
//                        imgPath = c.getString(1);
//
//                        //btn_pic.setImageURI(Uri.parse(imgPath));
//                        userimg.setImageBitmap(BitmapFactory.decodeFile(imgPath));
//                        sendPic(imgPath);
//                    }
//                }
//                else{
//                    String sub=data.getDataString();
//                    imgPath=sub.split("///")[1];
//                    userimg.setImageBitmap(BitmapFactory.decodeFile(imgPath));
//                }
//
//            }
//        } else {
//            System.out.println("请求码错误");
//        }
//        /**
//         * 弹出对话框，询问是否打开相册
//         */
//        public void showDialog() {
//            AlertDialog.Builder ab = new AlertDialog.Builder(this);
//            ab.setTitle("头像设置");
//            ab.setPositiveButton("相册", new DialogInterface.OnClickListener() {
//
//
//                public void onClick(DialogInterface arg0, int arg1) {
//                    Intent intent = new Intent();
//                    intent.setType("image/*");
//                    intent.setAction(Intent.ACTION_GET_CONTENT);
//                    startActivityForResult(intent, 3);
//                }
//            });
//
//            ab.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//
//
//                public void onClick(DialogInterface arg0, int arg1) {
//
//                }
//            });
//
//            ab.show();
//        }
    }

    /**
     * 上传头像
     *
     * @param path
     */
    public void sendPic(String path) {
        Map<String, Object> p = new HashMap<>();
        p.put("File", path);
        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_POST, p, new PicParser(), URLConstants.BASE_URL + URLConstants.SEND_ARTICLE_PIC, new Callback() {

            @Override
            public void success(BeanData beanData) {
                PicData uData = (PicData) beanData;
                imgPath = uData.getPicUrl();
                toast("上传头像成功");
            }

            @Override
            public void failure(String error) {
                toast("上传头像失败，请重新选择头像");
            }
        });
    }

    /**
     * 点击完成按钮后向服务器发送用户的注册信息
     */
    public void sendMessage() {
        Map<String, Object> params = new HashMap<String, Object>();
        String userName = editname.getText().toString();
        String userPwd = editpwd.getText().toString();
        params.put("user_img", imgPath);
        params.put("user_name", userName);
        params.put("user_pwd", userPwd);
        params.put("user_tel", userTel);


        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_POST, params, null, URLConstants.BASE_URL + URLConstants.USERRESGISTER, new Callback() {

            @Override
            public void success(BeanData beanData) {
                /**
                 *  注册成功后跳转到登录界面
                */
                toast("注册成功");
                Intent intentLog = new Intent(UserInfoActivity.this, LoginActivity.class);
                startActivity(intentLog);
                finish();

            }

            @Override
            public void failure(String error) {
                toast("用户已被注册或其他原因导致注册失败");
            }
        });


    }

    /**
     * 通过uri得到真实路径
     * @param context
     * @param uri
     * @return
     */
    public static String getRealFilePath(final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
