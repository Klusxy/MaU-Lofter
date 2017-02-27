package oracle.mau.main.camera.tag;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import oracle.mau.R;
import oracle.mau.main.camera.tag.adapter.TagGVAdapter;
import oracle.mau.utils.JudgeUtils;


/**
 * Created by 田帅 on 2017/2/22.
 */

public class TagPopupWindow extends PopupWindow implements AdapterView.OnItemClickListener, View.OnClickListener {
    private static final String TAG = "TagPopupWindow";
    private GridView gv_tag;
    private View mView;
    public ImageView tag_cancel;
    private Context mContext;
    private ImageView iv_tag_img;
    private TextView tag_next;
    private EditText et_tag_content;
    private int[] imgs = {R.mipmap.xx1, R.mipmap.xx2, R.mipmap.xx3, R.mipmap.xx4, R.mipmap.xx5, R.mipmap.xx6, R.mipmap.xx7, R.mipmap.xx8
            , R.mipmap.xx9, R.mipmap.xx10, R.mipmap.xx11, R.mipmap.xx12, R.mipmap.xx13, R.mipmap.xx14, R.mipmap.xx15, R.mipmap.xx16, R.mipmap.xx17
            , R.mipmap.xx18, R.mipmap.xx19, R.mipmap.xx20, R.mipmap.xx21, R.mipmap.xx22, R.mipmap.xx23, R.mipmap.xx24, R.mipmap.xx25, R.mipmap.xx26
            , R.mipmap.xx27, R.mipmap.xx28, R.mipmap.xx29, R.mipmap.xx30, R.mipmap.xx31, R.mipmap.xx32, R.mipmap.xx33, R.mipmap.xx34, R.mipmap.xx35
            , R.mipmap.xx36, R.mipmap.xx37, R.mipmap.xx38, R.mipmap.xx39, R.mipmap.xx40};
    private AddTagListener addTagListener;
    private int flag = -1;

    public interface AddTagListener{
        void addTag(int imgId, String content);
    }

    public TagPopupWindow(Activity context, AddTagListener addTagListener) {
        super(context);
        Log.i(TAG, "FinishProjectPopupWindow 方法已被调用");
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.pop_tag, null);
        this.mContext = context;
        this.addTagListener = addTagListener;
        initViews();
        initPop();
        initGridView();
    }

    private void initViews() {
        tag_cancel = (ImageView) mView.findViewById(R.id.tag_pop_cancel);
        tag_cancel.setOnClickListener(this);
        gv_tag = (GridView) mView.findViewById(R.id.gv_pop_tag_face);

        tag_next = (TextView) mView.findViewById(R.id.tag_pop_next);
        tag_next.setOnClickListener(this);
        et_tag_content = (EditText) mView.findViewById(R.id.et_pop_tag_content);
        et_tag_content.setOnClickListener(this);
    }

    private void initGridView() {
        TagGVAdapter adapter = new TagGVAdapter(mContext, imgs);
        gv_tag.setAdapter(adapter);
        gv_tag.setOnItemClickListener(this);
    }

    private void initPop() {
        //设置PopupWindow的View
        this.setContentView(mView);
        //设置PopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.Animation);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (iv_tag_img == null) {
            iv_tag_img = (ImageView) mView.findViewById(R.id.iv_pop_tag_img);
        }
        flag = position;
        iv_tag_img.setImageResource(imgs[position]);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tag_pop_cancel:
                dismiss();
                break;
            case R.id.tag_pop_next:
                if (JudgeUtils.isEmpty(et_tag_content.getText().toString())) {
                    Toast.makeText(mContext, "内容不能为空", Toast.LENGTH_SHORT).show();
                } else if (iv_tag_img == null) {
                    Toast.makeText(mContext, "标签图片不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    dismiss();
                    addTagListener.addTag(imgs[flag],et_tag_content.getText().toString());
                }
                break;
        }
    }
}
