package oracle.mau.main.home.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import oracle.mau.R;
import oracle.mau.entity.HomeEntity;
import oracle.mau.utils.ImageUtils;
import oracle.mau.view.GridViewForScrollView;

/**
 * Created by Administrator on 2017/3/2.
 */

public class HomeAddAttentionAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<HomeEntity> list = new ArrayList<HomeEntity>();
    List<String> gv_list;
    private HomeAddAttentionGVAdapter gvAdapter;
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

            vh.gv = (GridViewForScrollView) convertView.findViewById(R.id.home_gv);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(),R.mipmap.mimi);
        Bitmap circleBm = ImageUtils.circleBitmap(bm);
        vh.head.setImageBitmap(circleBm);
        vh.head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"头像点击事件",Toast.LENGTH_SHORT).show();

            }
        });


        vh.name.setText(list.get(position).getUsername());

        gvAdapter=new HomeAddAttentionGVAdapter(context);
        vh.gv.setAdapter(gvAdapter);
        gv_list = new ArrayList<String>();
        gv_list.add("");
        gv_list.add("");
        gv_list.add("");
        gv_list.add("");
        gv_list.add("");
        gv_list.add("");


        gvAdapter.setList(gv_list);
        gvAdapter.notifyDataSetChanged();
        return convertView;
    }

    class ViewHolder {
        ImageView head;
        TextView name ;

        GridViewForScrollView gv;
    }
}
