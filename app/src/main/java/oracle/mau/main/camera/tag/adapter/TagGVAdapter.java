package oracle.mau.main.camera.tag.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import oracle.mau.R;


/**
 * Created by 田帅 on 2017/2/22.
 */

public class TagGVAdapter extends BaseAdapter {
    private Context context;
    private int[] imgs;

    public TagGVAdapter(Context context, int[] imgs) {
        this.context = context;
        this.imgs = imgs;
    }

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
        ViewHolder vh ;
        if (convertView==null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.gv_item_tag,null);
            vh.iv_gv_item_tag = (ImageView) convertView.findViewById(R.id.iv_gv_item_tag);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.iv_gv_item_tag.setImageResource(imgs[position]);
        return convertView;
    }
    private class ViewHolder{
        private ImageView iv_gv_item_tag;
    }
}
