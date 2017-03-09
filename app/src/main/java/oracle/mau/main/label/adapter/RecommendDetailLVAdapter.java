package oracle.mau.main.label.adapter;

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

import com.bumptech.glide.Glide;

import java.util.List;

import oracle.mau.R;
import oracle.mau.entity.LabelRecommendEntity;
import oracle.mau.utils.ImageUtils;

/**
 * Created by 田帅 on 2017/3/9.
 */

public class RecommendDetailLVAdapter extends BaseAdapter {

    private Context context;
    private List<LabelRecommendEntity> list;

    public RecommendDetailLVAdapter(Context context, List<LabelRecommendEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.ptr_lv_item_recommend_detail,null);
            vh.iv_rd_lv_item_user_img = (ImageView) convertView.findViewById(R.id.iv_rd_lv_item_user_img);
            vh.tv_rd_lv_item_user_name = (TextView) convertView.findViewById(R.id.tv_rd_lv_item_user_name);
            vh.iv_rd_lv_item_article_img = (ImageView) convertView.findViewById(R.id.iv_rd_lv_item_article_img);
            vh.tv_rd_lv_item_article_content = (TextView) convertView.findViewById(R.id.tv_rd_lv_item_article_content);
            vh.tv_rd_lv_item_tag_name = (TextView) convertView.findViewById(R.id.tv_rd_lv_item_tag_name);
            vh.tv_rd_lv_item_article_location = (TextView) convertView.findViewById(R.id.tv_rd_lv_item_article_location);
            vh.tv_rd_lv_item_article_date = (TextView) convertView.findViewById(R.id.tv_rd_lv_item_article_date);
            vh.iv_rd_lv_item_comment = (ImageView) convertView.findViewById(R.id.iv_rd_lv_item_comment);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(),R.mipmap.caise1);
        Bitmap circleBm = ImageUtils.circleBitmap(bm);
        vh.iv_rd_lv_item_user_img.setImageBitmap(circleBm);
        vh.iv_rd_lv_item_user_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"头像点击事件",Toast.LENGTH_SHORT).show();
            }
        });
        vh.iv_rd_lv_item_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"评论按钮点击事件",Toast.LENGTH_SHORT).show();
            }
        });
        vh.tv_rd_lv_item_user_name.setText("测试用户");

        Glide.with(context).load(R.mipmap.chuntian1).into(vh.iv_rd_lv_item_article_img);
        vh.tv_rd_lv_item_article_content.setText("测试文章\n测试文章\n测试文章\n测试文章");
        vh.tv_rd_lv_item_tag_name.setText("测试标签");
        vh.tv_rd_lv_item_article_location.setText("天安门广场");
        vh.tv_rd_lv_item_article_date.setText("2099-03-23");
        return convertView;
    }

    private class ViewHolder{
        /**
         * 用户头像、用户名
         */
        private ImageView iv_rd_lv_item_user_img;
        private TextView tv_rd_lv_item_user_name;
        /**
         * 文章图片、文章内容、文章地址、文章日期
         */
        private ImageView iv_rd_lv_item_article_img;
        private TextView tv_rd_lv_item_article_content;
        private TextView tv_rd_lv_item_article_location;
        private TextView tv_rd_lv_item_article_date;
        /**
         * 标签名
         */
        private TextView tv_rd_lv_item_tag_name;
        private ImageView iv_rd_lv_item_comment;
    }
}
