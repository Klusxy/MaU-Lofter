package oracle.mau.main.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;
import oracle.mau.R;

/**
 * Created by Administrator on 2017/3/10.
 */

public class HomeAddAttentionGVAdapter extends BaseAdapter {
    private Context context;
    private List<String> list=new ArrayList<String>();
    private LayoutInflater inflater;
    public HomeAddAttentionGVAdapter (Context context){
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    public void setList(List<String> list) {
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
       ViewHolder vh=null;
        if (convertView==null){
            vh=new ViewHolder();
            convertView=inflater.inflate(R.layout.activity_homeaddattention_gv,null);
            vh.home_addattention_gv_item=(ImageView)convertView.findViewById(R.id.home_addattention_gv_item);
        convertView.setTag(vh);
        }else {
            vh=(ViewHolder)convertView.getTag();
        }
        vh.home_addattention_gv_item.setImageResource(R.mipmap.mimi);
        return convertView;
    }
    class ViewHolder{
        ImageView home_addattention_gv_item;
    }
}
