package oracle.mau.main.label.pop;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import oracle.mau.R;
import oracle.mau.utils.DensityUtils;
import oracle.mau.utils.ScreenUtils;

/**
 * Created by 田帅 on 2017/3/7.
 */

public class SelectChannelPop extends PopupWindow {
    private Context mContext;
    private ItemClickListener onItemClickListener;
    private View mView;

    public SelectChannelPop(Context context,  ItemClickListener onItemClickListener) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.pop_category_select_channel, null);
        this.mContext = context;
        this.onItemClickListener = onItemClickListener;
        initPop();
        initViews();
    }

    private void initViews() {

    }

    private void initPop() {
        //设置PopupWindow的View
        this.setContentView(mView);
        //设置PopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        /**
         * 减去滚动条40dp和顶部导航栏48dp  和状态栏高度
         */
        this.setHeight(ScreenUtils.getScreenHeight(mContext)- DensityUtils.dp2px(mContext,88)-ScreenUtils.getStatusHeight(mContext));
        //设置PopupWindow弹出窗体可点击
//        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.LabelRecommendDetailAnimation);
        /**
         * 不设置会有黑边
         */
        //实例化一个ColorDrawable颜色为白色
        ColorDrawable dw = new ColorDrawable(Color.WHITE);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
    }


    public interface ItemClickListener{
        void onItemClick(int position);
    }
}
