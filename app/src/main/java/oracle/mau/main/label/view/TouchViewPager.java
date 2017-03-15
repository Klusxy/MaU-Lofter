package oracle.mau.main.label.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import java.io.Serializable;
import java.util.List;

import oracle.mau.entity.LabelTagEntity;
import oracle.mau.entity.LabelTagNoListEntity;
import oracle.mau.main.label.activity.RecommendDetailActivity;

/**
 * Created by 田帅 on 2017/3/6.
 * 用户拦截画廊viewpager里的gridview触摸事件
 */

public class TouchViewPager extends ViewPager {

    private Context mContext;

    public TouchViewPager(Context context) {
        super(context);
        mContext = context;
    }

    public TouchViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }


    private int vp_label_tag_flag = 0;
    private List<LabelTagNoListEntity> tagList;

    public List<LabelTagNoListEntity> getTagList() {
        return tagList;
    }

    public void setTagList(List<LabelTagNoListEntity> tagList) {
        this.tagList = tagList;
    }

    float downX = 0;
    float downY = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                vp_label_tag_flag = 0;
                downX = ev.getX();
                downY = ev.getY();
                Log.d("dasdas" , "按下时  x坐标为  " +ev.getX());
                Log.d("dasdas" , "按下时  y坐标为  " +ev.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                vp_label_tag_flag = 1;
                Log.d("dasdas" , "移动  x坐标为  " +ev.getX());
                Log.d("dasdas" , "移动  y坐标为  " +ev.getY());
                /**
                 * 部分手机会出现该现象：  x y 没变  但是执行了move方法，导致跳转不过去，所以在这又加一层判断
                 */
                if (ev.getX() == downX) {
                    vp_label_tag_flag = 0;
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.d("dasdas" , "起  x坐标为  " +ev.getX());
                Log.d("dasdas" , "起  y坐标为  " +ev.getY());
                if (vp_label_tag_flag == 0) {
                    if (tagList!=null){
                        int item = this.getCurrentItem()%tagList.size();
                        for (LabelTagNoListEntity ll : tagList) {
                            ll.setSelectPosition(item);
                        }
                        Intent intent = new Intent(mContext, RecommendDetailActivity.class);
                        intent.putExtra("all",(Serializable) tagList);
                        intent.putExtra("position",item);
//                        intent.putExtra("tagId",tagList.get(item).getTagId());
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("tag",tagList.get(item));
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
                    }
                }
                break;
        }

        return super.dispatchTouchEvent(ev);
    }


}
