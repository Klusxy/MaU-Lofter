package oracle.mau.main.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import oracle.mau.R;
import oracle.mau.entity.HomeEntity;

/**
 * Created by Administrator on 2017/3/2.
 */

public class HomeAddAttentionAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<HomeEntity> list = new ArrayList<HomeEntity>();

    public HomeAddAttentionAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    //添加数据的方法
    public void addData(List<HomeEntity> list) {
        this.list.clear();
        this.list = list;
    }

    //返回所有数据的方法
    public List<HomeEntity> getList() {
        return list;
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
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_home_addattention_lv, null);
            vh.head = (ImageView) convertView.findViewById(R.id.home_addattention_head);
            vh.name = (TextView) convertView.findViewById(R.id.home_addattention_username);
            vh.liked = (TextView) convertView.findViewById(R.id.home__addattention_like);
            vh.dislike = (Button) convertView.findViewById(R.id.home_addattention_dislike);
            vh.attention = (Button) convertView.findViewById(R.id.home_addattention);
            vh.gv = (GridView) convertView.findViewById(R.id.home_gv);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.head.setImageResource(list.get(position).getPic());
        vh.name.setText(list.get(position).getUsername());
        vh.liked.setText(list.get(position).getLikeNum()+"");//注此处为整型
       // vh.gv.set

        return convertView;
    }

    class ViewHolder {
        ImageView head;
        TextView name, liked;
        Button dislike, attention;
        GridView gv;
    }
}
