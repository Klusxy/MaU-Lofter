package oracle.mau.main.camera.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import oracle.mau.R;

/**
 * Created by 田帅 on 2017/2/28.
 */

public class AddLocationAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public AddLocationAdapter(Context context, List<String> list) {
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
        ViewHolder vh;
        if (convertView==null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_item_location,null);
            vh.tv_add_location_location = (TextView) convertView.findViewById(R.id.tv_add_location_location);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv_add_location_location.setText(list.get(position));
        return convertView;
    }
    private class ViewHolder{
        private TextView tv_add_location_location;
    }
}
