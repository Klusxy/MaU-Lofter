package oracle.mau.main.label.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

import oracle.mau.R;
import oracle.mau.entity.LabelTagEntity;

/**
 * Created by 田帅 on 2017/2/28.
 */

public class TagGalleryVPAdapter extends PagerAdapter {

    private List<LabelTagEntity> list;
    private Context context;

    public TagGalleryVPAdapter(List<LabelTagEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        /**
         * 确保每次初始化时创建一个新view，避免容器重复添加的错误
         */
        View view = LayoutInflater.from(context).inflate(R.layout.vp_item_label_tag, null);
        TextView tv_vp_item_tag = (TextView) view.findViewById(R.id.tv_vp_item_tag);
        tv_vp_item_tag.setText(list.get(position % list.size()).getTagTitle());
        GridView gv_vp_item = (GridView) view.findViewById(R.id.gv_vp_item);
        TagGridViewAdapter adapter = new TagGridViewAdapter(context, list.get(position % list.size()).getImgs());
        gv_vp_item.setAdapter(adapter);
        container.addView(view);

        return view;
    }

}
