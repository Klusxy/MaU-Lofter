package oracle.mau.main.camera.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import oracle.mau.R;
import oracle.mau.main.MainActivity;
import oracle.mau.main.camera.constant.PhotoConstant;
import oracle.mau.main.camera.filter.BitmapFilter;
import oracle.mau.main.camera.paster.PasterPopupWindow;
import oracle.mau.main.camera.tag.TagInfo;
import oracle.mau.main.camera.tag.TagPopupWindow;
import oracle.mau.main.camera.tag.TagView;
import oracle.mau.main.camera.tag.TagViewLeft;
import oracle.mau.main.camera.view.TouchImageView;


/**
 * Created by 田帅 on 2017/2/22.
 */

public class EditPicActivity extends Activity implements View.OnClickListener, TagView.TagViewListener {
    private String TAG = "EditPicActivity";
    /**
     * 点击底部标签弹出的pop
     */
    private TagPopupWindow mTagPopupWindow;

    private Uri mImageUri;            //目标图片的Uri
    private String mImagePath;        //目标图片的路径

    private RelativeLayout rl_edicpic_main_bg;
    /**
     * 目标图片
     */
    private ImageView mImageView;
    /**
     * 目标图片所在的父布局
     */
    private RelativeLayout mImageRootLayout;
    /**
     * 控件的位置  坐上定点坐标
     */
    private float mPointX = 0;
    private float mPointY = 0;
    /**
     * 触摸点在tagView中的位置
     */
    private int _xDelta;
    private int _yDelta;
    /**
     * 底部标签按钮、滤镜按钮、贴纸按钮
     */
    private Button btn_edit_tag;
    private Button btn_edit_filter;
    private Button btn_editpic_paster;
    private TouchImageView mParserView = null;
    /**
     * 顶部返回按钮和确定按钮
     */
    private ImageView ed_cancel;
    private TextView ed_next;
    /**
     * 显示滤镜画廊的水平滚动条和线性布局
     */
    private HorizontalScrollView hc_et;
    private LinearLayout ll_et_gallery;
    /**
     * 无效果的bm和添加滤镜之后的bm
     * 存放各类效果的集合
     */
    private Bitmap originalBitmap;
    private Bitmap sBitmap;
    private List<Bitmap> filterImageList;
    private List<String> filterTextList;
    private LayoutInflater mInflater;
    private final int UPDATE_FILTER_GALLERY = 100001;
    private boolean isFirstFilter = true;
    /**
     * 用来将图片存入缓存中
     */
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (UPDATE_FILTER_GALLERY == (int) msg.obj) {
                /**
                 * 初始化滤镜画廊
                 */
                mInflater = LayoutInflater.from(EditPicActivity.this);
                initFilterGallery();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Make UI fullscreen.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_editpic);
        /**
         * 得到图片的路径
         */
        mImagePath = getIntent().getStringExtra(MainActivity.CROP_IMAGE_URI);
        mImageUri = Uri.parse(mImagePath);

        //初始化控件
        initViews();
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (rl_edicpic_main_bg == null) {
            rl_edicpic_main_bg = (RelativeLayout) findViewById(R.id.rl_edicpic_main_bg);
        }
        rl_edicpic_main_bg.setBackgroundColor(Color.BLACK);
    }

    private void initViews() {
        mImageView = (ImageView) findViewById(R.id.iv_ep_st_image);
        mImageRootLayout = (RelativeLayout) findViewById(R.id.rl_ep_mImageRootLayout);
        if (mImageUri != null) {
            /**
             * 设置mRootLayout的位置
             */
            RelativeLayout.LayoutParams lpp = new RelativeLayout.LayoutParams(PhotoConstant.displayWidth, PhotoConstant.displayWidth);
            lpp.addRule(RelativeLayout.CENTER_VERTICAL);
            mImageRootLayout.setLayoutParams(lpp);
            /**
             * 动态设置图片的大小和位置
             */
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(PhotoConstant.displayWidth, PhotoConstant.displayWidth);
            lp.addRule(RelativeLayout.CENTER_VERTICAL);
            mImageView.setLayoutParams(lp);
            mImageView.setImageURI(mImageUri);
        }
        btn_edit_tag = (Button) findViewById(R.id.btn_ep_tag);
        btn_edit_tag.setOnClickListener(this);
        mImageRootLayout.setBackgroundColor(Color.BLACK);
        ed_cancel = (ImageView) findViewById(R.id.ed_cancel);
        ed_cancel.setOnClickListener(this);
        ed_next = (TextView) findViewById(R.id.ed_next);
        ed_next.setOnClickListener(this);
        btn_edit_filter = (Button) findViewById(R.id.btn_ep_filter);
        btn_edit_filter.setOnClickListener(this);
        hc_et = (HorizontalScrollView) findViewById(R.id.hc_ep_filter);
        hc_et.setVisibility(View.GONE);
        ll_et_gallery = (LinearLayout) findViewById(R.id.ll_ep_gallery);
        new Thread() {
            @Override
            public void run() {
                //将file转成bm
                originalBitmap = BitmapFactory.decodeFile(mImagePath);
                /**
                 * 初始化画廊的数据
                 */
                initFilter();
            }
        }.start();
        btn_editpic_paster = (Button) findViewById(R.id.btn_editpic_paster);
        btn_editpic_paster.setOnClickListener(this);
    }

    /**
     * 初始化滤镜画廊
     */
    private void initFilterGallery() {
        for (int i = 0; i < filterImageList.size(); i++) {
            View view = mInflater.inflate(R.layout.activity_filter_gallery_item,
                    ll_et_gallery, false);
            final ImageView img = (ImageView) view
                    .findViewById(R.id.iv_filter_gallery_item_image);
            img.setImageBitmap(filterImageList.get(i));
            TextView textView = (TextView) view.findViewById(R.id.iv_filter_gallery_item_text);
            textView.getBackground().setAlpha(178);
            textView.setText(filterTextList.get(i));
            ll_et_gallery.addView(view);
            img.setTag(i);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int col = (int) v.getTag();
                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(PhotoConstant.displayWidth, PhotoConstant.displayWidth);
                    lp.addRule(RelativeLayout.CENTER_VERTICAL);
                    mImageView.setLayoutParams(lp);
                    mImageView.setImageBitmap(filterImageList.get(col));
                }
            });
        }
    }

    /**
     * 初始化滤镜
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initFilter() {
        filterImageList = new ArrayList<>();
        filterTextList = new ArrayList<>();
        //无滤镜效果
//        Drawable bd = new BitmapDrawable(getResources(), originalBitmap);
//        filterImageList.add(bd);
        filterImageList.add(originalBitmap);
        filterTextList.add("原图");

        //黑白效果
        sBitmap = BitmapFilter.changeStyle(originalBitmap, BitmapFilter.GRAY_STYLE);
//        bd = new BitmapDrawable(getResources(), sBitmap);
        filterImageList.add(sBitmap);
        filterTextList.add("黑白");

        //怀旧效果
        sBitmap = BitmapFilter.changeStyle(originalBitmap, BitmapFilter.OLD_STYLE);
//        bd = new BitmapDrawable(getResources(), sBitmap);
        filterImageList.add(sBitmap);
        filterTextList.add("怀旧");

        //冰冻效果
        sBitmap = BitmapFilter.changeStyle(originalBitmap, BitmapFilter.ICE_STYLE);
//        bd = new BitmapDrawable(getResources(), sBitmap);
        filterImageList.add(sBitmap);
        filterTextList.add("冰冻");

        //连环画效果
        sBitmap = BitmapFilter.changeStyle(originalBitmap, BitmapFilter.CARTON_STYLE);
//        bd = new BitmapDrawable(getResources(), sBitmap);
        filterImageList.add(sBitmap);
        filterTextList.add("连环画");

        //柔滑美白
        sBitmap = BitmapFilter.changeStyle(originalBitmap, BitmapFilter.SOFTNESS_STYLE);
//        bd = new BitmapDrawable(getResources(), sBitmap);
        filterImageList.add(sBitmap);
        filterTextList.add("柔滑美白");

        //羽化效果
        sBitmap = BitmapFilter.changeStyle(originalBitmap, BitmapFilter.ECLOSION_STYLE);
//        bd = new BitmapDrawable(getResources(), sBitmap);
        filterImageList.add(sBitmap);
        filterTextList.add("羽化");

        //光照效果
        sBitmap = BitmapFilter.changeStyle(originalBitmap, BitmapFilter.LIGHT_STYLE);
//        bd = new BitmapDrawable(getResources(), sBitmap);
        filterImageList.add(sBitmap);
        filterTextList.add("光照");
        //哈哈镜效果
        sBitmap = BitmapFilter.changeStyle(originalBitmap, BitmapFilter.HAHA_STYLE);
//        bd = new BitmapDrawable(getResources(), sBitmap);
        filterImageList.add(sBitmap);
        filterTextList.add("哈哈镜");
        //完成之后  告诉主线程刷新ui
        Message msg = new Message();
        msg.obj = UPDATE_FILTER_GALLERY;
        mHandler.sendMessage(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 跳转添加标签页面
             */
            case R.id.btn_ep_tag:
                mTagPopupWindow = new TagPopupWindow(this, new TagPopupWindow.AddTagListener() {
                    @Override
                    public void addTag(int imgId, String content) {
                        /**
                         * 编辑标签信息
                         */
                        editTagInfo(imgId, content);
                    }
                });
                mTagPopupWindow.showAtLocation(this.findViewById(R.id.btn_ep_tag),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            /**
             * 返回按钮
             */
            case R.id.ed_cancel:
                finish();
                break;
            /**
             * 确定按钮
             */
            case R.id.ed_next:
                saveImageAndFinish();

                break;
            /**
             * 滤镜按钮
             */
            case R.id.btn_ep_filter:
                if (filterImageList.size() < 9) {
                    Toast.makeText(this, "滤镜缩略图正在加载，请稍后再试", Toast.LENGTH_SHORT).show();
                } else if (isFirstFilter) {
                    hc_et.setVisibility(View.VISIBLE);
                    isFirstFilter = false;
                } else {
                    hc_et.setVisibility(View.GONE);
                    isFirstFilter = true;
                }
                break;
            /**
             * 贴纸按钮
             */
            case R.id.btn_editpic_paster:
                PasterPopupWindow pasterPopupWindow = new PasterPopupWindow(this, new PasterPopupWindow.AddPasterListener() {
                    @Override
                    public void addPaster(int imgId) {
                        //添加贴纸到图片上
                        addPasterToImg(imgId);
                    }
                });
                pasterPopupWindow.showAtLocation(this.findViewById(R.id.btn_ep_tag),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
        }
    }

    /**
     * 添加贴纸方法
     *
     * @param imgId 贴纸图片id
     */
    private void addPasterToImg(int imgId) {
        //设置图片显示宽高为屏幕宽度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(PhotoConstant.displayWidth, PhotoConstant.displayWidth);

        Bitmap watermark = BitmapFactory.decodeResource(getResources(), imgId);

        int ww = watermark.getWidth();
        int wh = watermark.getHeight();


        //如果水印图片太大则压缩
        if (ww > PhotoConstant.displayWidth || wh > PhotoConstant.displayWidth) {
            // 缩放图片的尺寸
            float scaleWidth = (float) PhotoConstant.displayWidth / ww;
            float scaleHeight = (float) PhotoConstant.displayWidth / wh;
            float scale = Math.min(scaleWidth, scaleHeight) * (float) 0.8;    //屏幕宽度的80%
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            // 产生缩放后的Bitmap对象
            watermark = Bitmap.createBitmap(watermark, 0, 0, ww, wh, matrix, false);
        }

        TouchImageView touchImageView = new TouchImageView(this, watermark);
        mImageRootLayout.addView(touchImageView, params);
        mParserView = touchImageView;
    }

    /**
     * 保存图片并跳转到发布页面
     */
    private void saveImageAndFinish() {
        final Bitmap croppedImage = getLayoutBitmap(mImageRootLayout);

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                saveDrawableToCache(croppedImage, mImagePath);
                croppedImage.recycle();
            }
        });

        Intent intent = new Intent(this, ReleaseArticleActivity.class);
        intent.putExtra("tag_image_path", mImagePath);
        startActivity(intent);
        finish();
    }

    /**
     * 编辑标签信息
     *
     * @param imgId   标签表情对应在网格的位置的图片id
     * @param content 添加的标签信息
     */
    private void editTagInfo(int imgId, String content) {
        /**
         * 获得mImageView的左上定点坐标
         */
        int[] location = new int[2];
        mImageView.getLocationOnScreen(location);
        mPointX = (mImageView.getWidth()) / 2;
        mPointY = (location[1] + mImageView.getHeight()) / 2;
        /**
         * 编辑标签信息
         */
        TagInfo tagInfo = new TagInfo();
        tagInfo.bid = imgId;
        tagInfo.bname = content;
        tagInfo.direct = TagInfo.Direction.Left;
        //根据方向来设置标签的位置
        switch (tagInfo.direct) {
            case Left:
                tagInfo.leftMargin = (int) (mPointX - 15 * PhotoConstant.scale);    //根据屏幕密度计算使动画中心在点击点，15dp是margin
                tagInfo.topMargin = (int) (mPointY - 15 * PhotoConstant.scale);
                tagInfo.rightMargin = 0;
                tagInfo.bottomMargin = 0;
                break;
            case Right:

                break;
        }
        addTag(tagInfo);
    }


    /**
     * 保存图片到缓存中
     *
     * @param bitmap
     * @param filePath
     */
    private void saveDrawableToCache(Bitmap bitmap, String filePath) {

        try {
            File file = new File(filePath);

            file.createNewFile();

            OutputStream outStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * 获取Layout的截图
     */
    public Bitmap getLayoutBitmap(View view) {
        view.invalidate();
        //shark_5
        view.setDrawingCacheEnabled(true);
//        view.measure(
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//        view.layout(0, 0, view.getMeasuredWidth(),
//                view.getMeasuredHeight());

        view.buildDrawingCache();

        Bitmap bitmap = view.getDrawingCache();

        return bitmap;
    }

    /**
     * 添加标签
     */
    private void addTag(final TagInfo tagInfo) {
        TagView tagView = null;
        switch (tagInfo.direct) {
            case Left:
                tagView = new TagViewLeft(this, null);
                break;
            case Right:

                break;
        }
        tagView.setData(tagInfo);
        tagView.setTagViewListener(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(tagInfo.leftMargin, tagInfo.topMargin, tagInfo.rightMargin, tagInfo.bottomMargin);

        mImageRootLayout.addView(tagView, params);
        //添加TagView的移动事件
        tagView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                final int X = (int) event.getRawX();
                final int Y = (int) event.getRawY();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v
                                .getLayoutParams();
                        /**
                         * 触摸点在控件中的坐标
                         */
                        _xDelta = X - lParams.leftMargin;
                        _yDelta = Y - lParams.topMargin;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        updateTagView(v, X, Y);
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 更新标签控件的位置
     *
     * @param v 标签控件
     * @param x 触摸点的相对坐标X
     * @param y 触摸点的相对坐标Y
     */
    private void updateTagView(View v, int x, int y) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) v
                .getLayoutParams();
        lp.leftMargin = x - _xDelta;
        lp.topMargin = y - _yDelta;
        lp.rightMargin = 0;
        lp.bottomMargin = 0;
        /**
         * 判断左右边界
         */
        if (lp.leftMargin < 0) {
            lp.leftMargin = 0;
        } else if (lp.leftMargin > (mImageView.getWidth() - v.getWidth())) {
            lp.leftMargin = mImageView.getWidth() - v.getWidth();
        }
        /**
         * 判断上下边界
         */
        if (lp.topMargin < mImageView.getY()) {
            lp.topMargin = (int) mImageView.getY();
        } else if (lp.topMargin > (mImageView.getY() + mImageView.getHeight() - v.getHeight())) {
            lp.topMargin = (int) mImageView.getY() + mImageView.getHeight() - v.getHeight();
        }
        /**
         * 更新位置
         */
        v.setLayoutParams(lp);
    }

    /**
     * 标签的点击事件
     *
     * @param view
     * @param info
     */
    @Override
    public void onTagViewClicked(View view, TagInfo info) {
        Toast.makeText(this, "点点点", Toast.LENGTH_SHORT).show();
    }
}
