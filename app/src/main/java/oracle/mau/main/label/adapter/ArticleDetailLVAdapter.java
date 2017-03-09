package oracle.mau.main.label.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import oracle.mau.R;
import oracle.mau.entity.CommentEntity;

/**
 * Created by 田帅 on 2017/3/9.
 */

public class ArticleDetailLVAdapter extends BaseAdapter {

    private Context context;
    private List<CommentEntity> list;

    public ArticleDetailLVAdapter(Context context, List<CommentEntity> list) {
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
        if (convertView==null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_item_article_detail_comment,null);
            vh.tv_ad_comment_user_name = (TextView) convertView.findViewById(R.id.tv_ad_comment_user_name);
            vh.tv_ad_comment_content = (TextView) convertView.findViewById(R.id.tv_ad_comment_content);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv_ad_comment_user_name.setText(list.get(position).getUserName()+":");
        vh.tv_ad_comment_content.setText(list.get(position).getCommentContent());
        return convertView;
    }

    private class ViewHolder{
        private TextView tv_ad_comment_user_name;
        private TextView tv_ad_comment_content;
    }
}
