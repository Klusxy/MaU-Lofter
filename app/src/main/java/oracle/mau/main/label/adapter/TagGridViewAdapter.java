package oracle.mau.main.label.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import oracle.mau.R;

/**
 * Created by 田帅 on 2017/2/28.
 */

public class TagGridViewAdapter extends BaseAdapter {
    private Context context;
    private int parentWidth = 0;

    public TagGridViewAdapter(Context context, int[] imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    private int[] imgs;

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return imgs[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.gv_label_tag_item, null);
            vh.iv_gv_item_tag_img = (ImageView) convertView.findViewById(R.id.iv_gv_item_tag_img);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        /**
         * 获取容器的宽度
         */
        parentWidth = parent.getMeasuredWidth();
        /**
         * 动态设置imageview的大小
         */
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(parentWidth / 2, parentWidth / 2);
        vh.iv_gv_item_tag_img.setLayoutParams(lp);
        vh.iv_gv_item_tag_img.setImageResource(imgs[position]);
        return convertView;
    }

    private class ViewHolder {
        private ImageView iv_gv_item_tag_img;
    }


}
