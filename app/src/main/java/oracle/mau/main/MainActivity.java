package oracle.mau.main;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.main.account.AccountFragment;
import oracle.mau.main.home.HomeFragment;
import oracle.mau.main.label.LabelFragment;
import oracle.mau.main.message.MessageFragment;
import oracle.mau.view.BottomMenuDialog;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener,View.OnClickListener{
    private RadioGroup rg_main;
    private RadioButton rb_main_label;
    //判断是否第一次加载界面
    private boolean isFirst = true;
    //照相机按钮
    private Button btn_main_camera;
    private BottomMenuDialog bottomMenuDialog;

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
        //设置UI
        configUI();
    }

    /**
     * intent跳转静态方法
     * @param context  上下文
     *                 测试
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
     * @param fg  新碎片
     * @param id  被替换的layout
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
                break;
            case R.id.rb_main_account:
                replaceFragment(new AccountFragment(), R.id.fl_main_fragment);
                break;
        }
    }

    /**
     * 按钮点击事件
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
                .addMenu("从手机相册选择", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomMenuDialog.dismiss();
                        Toast.makeText(v.getContext(), "从手机相册选择" , Toast.LENGTH_SHORT).show();
                    }
                }).addMenu("拍一张", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomMenuDialog.dismiss();
                        Toast.makeText(v.getContext(), "拍一张" , Toast.LENGTH_SHORT).show();
                    }
                }).create();

        bottomMenuDialog.show();
    }
}
