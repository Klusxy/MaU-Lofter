package oracle.mau.main.label.adapter;


/**
 * Created by 田帅 on 2017/3/7.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import oracle.mau.R;
import oracle.mau.entity.LabelTagEntity;

public class RDDragListViewAdapter extends BaseAdapter {

    private Context context;
    List<LabelTagEntity> items;//适配器的数据源


    public RDDragListViewAdapter(Context context, List<LabelTagEntity> list){
        this.context = context;
        this.items = list;
    }



    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return items.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    public void remove(int arg0) {//删除指定位置的item
        items.remove(arg0);
        this.notifyDataSetChanged();//不要忘记更改适配器对象的数据源
    }

    public void insert(LabelTagEntity item, int arg0) {//在指定位置插入item
        items.add(arg0, item);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LabelTagEntity item = (LabelTagEntity)getItem(position);
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.dsl_item_recommend_detail, null);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_dsl_label_type_name);
//            viewHolder.ivCountryLogo = (ImageView) convertView.findViewById(R.id.ivCountryLogo);
//            viewHolder.ivDelete = (ImageView) convertView.findViewById(R.id.click_remove);
            viewHolder.ivDragHandle = (ImageView) convertView.findViewById(R.id.iv_dsl_drag_sort);
            viewHolder.iv_dsl_drag_selected = (ImageView) convertView.findViewById(R.id.iv_dsl_drag_selected);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (!item.isDrag()) {
            if (position == item.getSelectPosition()) {
                viewHolder.iv_dsl_drag_selected.setVisibility(View.VISIBLE);
            }
        }
        viewHolder.tvTitle.setText(item.getTagTitle());
//        viewHolder.ivCountryLogo.setImageResource(item.src);



        return convertView;
    }

    public class ViewHolder {
        TextView tvTitle;
//        ImageView ivCountryLogo;
//        ImageView ivDelete;
        public ImageView ivDragHandle;
        public ImageView iv_dsl_drag_selected;
    }
}
