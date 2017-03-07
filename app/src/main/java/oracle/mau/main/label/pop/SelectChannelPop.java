package oracle.mau.main.label.pop;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupWindow;

import com.mobeta.android.dslv.DragSortListView;

import java.util.List;

import oracle.mau.R;
import oracle.mau.entity.LabelTagEntity;
import oracle.mau.main.label.adapter.RDDragListViewAdapter;
import oracle.mau.utils.DensityUtils;
import oracle.mau.utils.ScreenUtils;

/**
 * Created by 田帅 on 2017/3/7.
 */

public class SelectChannelPop extends PopupWindow implements AdapterView.OnItemClickListener{
    private Context mContext;
    private ItemClickListener onItemClickListener;
    private View mView;
    private DragSortListView mDragSortListView;
    private List<LabelTagEntity> list;
    private int position;
    private RDDragListViewAdapter mAdapter;


    //监听器在手机拖动停下的时候触发
    private DragSortListView.DropListener onDrop =
            new DragSortListView.DropListener() {
                @Override
                public void drop(int from, int to) {//from to 分别表示 被拖动控件原位置 和目标位置
                    if (from != to) {
                        LabelTagEntity item = (LabelTagEntity) mAdapter.getItem(from);//得到listview的适配器
                        mAdapter.remove(from);//在适配器中”原位置“的数据。
                        mAdapter.insert(item, to);//在目标位置中插入被拖动的控件。
                    }
                }
            };

    public SelectChannelPop(Context context,  ItemClickListener onItemClickListener ,List<LabelTagEntity> list,int position) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.pop_category_select_channel, null);
        this.mContext = context;
        this.list = list;
        this.position = position;
        this.onItemClickListener = onItemClickListener;
        initPop();
        initViews();
    }

    private void initViews() {
        mDragSortListView = (DragSortListView) mView.findViewById(R.id.dsl_recommend_detail_pop);
        //得到滑动listview并且设置监听器。
        mDragSortListView.setDropListener(onDrop);
        mDragSortListView.setOnItemClickListener(this);
        mAdapter = new RDDragListViewAdapter(mContext, list);
        mDragSortListView.setAdapter(mAdapter);
        mDragSortListView.setDragEnabled(true); //设置是否可拖动。
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LabelTagEntity item = (LabelTagEntity)mAdapter.getItem(position);
        onItemClickListener.onItemClick(item.getTagTitle() , position);
    }


    public interface ItemClickListener{
        void onItemClick(String tagTitle , int posi);
    }
}
