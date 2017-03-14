package oracle.mau.main.label.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import oracle.mau.R;
import oracle.mau.entity.LabelTagEntity;

/**
 * Created by 田帅 on 2017/3/13.
 */

public class SearchTagAdapter extends BaseAdapter {
    private Context context;
    private List<LabelTagEntity> list;

    public SearchTagAdapter(Context context, List<LabelTagEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh ;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_item_search_tag,null);
            vh.tv_lv_item_search_tag_name = (TextView) convertView.findViewById(R.id.tv_lv_item_search_tag_name);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv_lv_item_search_tag_name.setText(list.get(position).getTagTitle());
        return convertView;
    }
    private class ViewHolder{
        private TextView tv_lv_item_search_tag_name;
    }
}
