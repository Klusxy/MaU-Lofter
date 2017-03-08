package oracle.mau.main.home.adapter;


import android.content.Context;
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
import oracle.mau.view.BottomMenuDialog;

/**
 * Created by Administrator on 2017/3/1.
 */

public class HomeAttentionFragmentAdapter extends BaseAdapter implements View.OnClickListener {
    Context context;
    LayoutInflater inflater;
    List<HomeEntity> list=new ArrayList<HomeEntity>();
    private BottomMenuDialog bottomMenuDialog;
//AttentionFragment attentionFragment=new AttentionFragment();



    public HomeAttentionFragmentAdapter(Context context ) {
        this.context = context;
        inflater=LayoutInflater.from(context);
    }
//添加数据的方法
    public void addData(List<HomeEntity> list){
        this.list.clear();
        this.list=list;
    }
    //返回所有数据的方法
    public List<HomeEntity> getList(){
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
        if (convertView==null){
            vh = new ViewHolder();
            convertView=inflater.inflate(R.layout.fragment_homeattention_lv,null);
            //ImageView
            vh.home_head=(ImageView)convertView.findViewById(R.id.home_head);
            vh.home_iv=(ImageView)convertView.findViewById(R.id.home_iv);
            vh.home_love=(ImageView)convertView.findViewById(R.id.home_love);
            vh.home_comment =(ImageView)convertView.findViewById(R.id.home_comment);
            vh.home_transmit=(ImageView)convertView.findViewById(R.id.home_transmit);
            vh.home_popupwindow=(ImageView)convertView.findViewById(R.id.home_popupwindow);
            vh.home_popupwindow.setOnClickListener(this);
            //TextView
            vh.home_username=(TextView)convertView.findViewById(R.id.home_username);
            vh.home_time=(TextView)convertView.findViewById(R.id.home_time);
            vh.home_commandname=(TextView)convertView.findViewById(R.id.home_commandname);
            vh.home_content=(TextView)convertView.findViewById(R.id.home_content);
            vh.home_sign=(TextView)convertView.findViewById(R.id.home_sign);
            vh.home_hot=(TextView)convertView.findViewById(R.id.home_hot);



            convertView.setTag(vh);
        }else {
            vh=(ViewHolder)convertView.getTag();
        }

        vh.home_head.setImageResource(R.mipmap.ic_launcher);
        vh.home_iv.setImageResource(list.get(position).getPic());
        vh.home_love.setImageResource(R.mipmap.dashboard_like_off_hover);
        vh.home_comment.setImageResource(R.mipmap.dashboard_reply_hover);
        vh.home_transmit.setImageResource(R.mipmap.dashboard_reblog_hover);
        vh.home_popupwindow.setImageResource(R.mipmap.dots_rest_person_page_hover);




        vh.home_username.setText(list.get(position).getUsername());
        vh.home_time.setText(list.get(position).getTime());
        vh.home_commandname.setText(list.get(position).getCommandname());
        vh.home_content.setText(list.get(position).getContent());
        vh.home_sign.setText(list.get(position).getSign());
        vh.home_hot.setText(list.get(position).getHot());
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_popupwindow:
                openDialog();
                break;
        }
    }
    /**
     * 打开弹出框
     */
    private void openDialog() {
        bottomMenuDialog = new BottomMenuDialog.Builder(context)

                .addMenu("推荐给我的粉丝", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomMenuDialog.dismiss();
                        home_recommand();
                    }
                }).addMenu("复制链接", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomMenuDialog.dismiss();
                        CopyUri();
                    }
                }).create();

        bottomMenuDialog.show();
    }

    private void home_recommand() {
        Toast.makeText(context,"推荐给我的粉丝", Toast.LENGTH_SHORT).show();
    }
    private void CopyUri() {
        Toast.makeText(context,"复制链接", Toast.LENGTH_SHORT).show();
    }
    class ViewHolder{
        ImageView home_head,home_iv,home_love,home_comment,home_transmit,home_popupwindow;
        TextView home_username,home_time,home_commandname,home_content,home_sign,home_hot;

    }

}
