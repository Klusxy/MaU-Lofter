package oracle.mau.main.camera.paster;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;

import oracle.mau.R;
import oracle.mau.main.camera.paster.adapter.PasterGVAdapter;

/**
 * Created by 田帅 on 2017/3/2.
 */

public class PasterPopupWindow extends PopupWindow implements View.OnClickListener , AdapterView.OnItemClickListener{

    private Context mContext;
    private AddPasterListener addPasterListener;
    private View mView;

    //gridview的数据源
    private int[] imgs= {R.mipmap.paster_1,R.mipmap.paster_2};

    /**
     * 控件
     */
    private ImageView paster_pop_cancel;
    private GridView gv_pop_paster;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.paster_pop_cancel :
                dismiss();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        addPasterListener.addPaster(imgs[position]);
        dismiss();
    }

    public interface AddPasterListener{
        void addPaster(int imgId);
    }

    public PasterPopupWindow(Context context, AddPasterListener addPasterListener) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.pop_paster, null);
        this.mContext = context;
        this.addPasterListener = addPasterListener;

        initViews();
        initPop();
        initGridView();
    }

    private void initGridView() {
        PasterGVAdapter adapter = new PasterGVAdapter(mContext, imgs);
        gv_pop_paster.setAdapter(adapter);
        gv_pop_paster.setOnItemClickListener(this);
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

    private void initViews() {
        paster_pop_cancel = (ImageView) mView.findViewById(R.id.paster_pop_cancel);
        paster_pop_cancel.setOnClickListener(this);
        gv_pop_paster = (GridView) mView.findViewById(R.id.gv_pop_paster);
    }


}
