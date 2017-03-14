package oracle.mau.main.label.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

import java.util.List;

import oracle.mau.R;
import oracle.mau.entity.ArticleEntity;
import oracle.mau.entity.LabelRecommendDetailEntity;
import oracle.mau.entity.LabelRecommendEntity;
import oracle.mau.utils.ImageUtils;
import oracle.mau.utils.JudgeUtils;
import oracle.mau.utils.ScreenUtils;

/**
 * Created by 田帅 on 2017/3/9.
 */

public class RecommendDetailLVAdapter extends BaseAdapter {

    private Context context;
    private List<ArticleEntity> list;

    public RecommendDetailLVAdapter(Context context, List<ArticleEntity> list) {
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
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.ptr_lv_item_recommend_detail, null);
            vh.iv_rd_lv_item_user_img = (ImageView) convertView.findViewById(R.id.iv_rd_lv_item_user_img);
            vh.tv_rd_lv_item_user_name = (TextView) convertView.findViewById(R.id.tv_rd_lv_item_user_name);
            vh.iv_rd_lv_item_article_img = (ImageView) convertView.findViewById(R.id.iv_rd_lv_item_article_img);
            vh.tv_rd_lv_item_article_content = (TextView) convertView.findViewById(R.id.tv_rd_lv_item_article_content);
            vh.tv_rd_lv_item_tag_name = (TextView) convertView.findViewById(R.id.tv_rd_lv_item_tag_name);
            vh.tv_rd_lv_item_article_location = (TextView) convertView.findViewById(R.id.tv_rd_lv_item_article_location);
            vh.tv_rd_lv_item_article_date = (TextView) convertView.findViewById(R.id.tv_rd_lv_item_article_date);
            vh.tv_rd_lv_item_article_img_count = (TextView) convertView.findViewById(R.id.tv_rd_lv_item_article_img_count);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        /**
         * 文章图片
         */
        int screenWidth = ScreenUtils.getScreenWidth(context);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(screenWidth, screenWidth);
        vh.iv_rd_lv_item_article_img.setLayoutParams(lp);
        if (list.get(position)!=null){
            if (list.get(position).getImgList()!=null&&list.get(position).getImgList().size()!=0){
                if (!JudgeUtils.isEmpty(list.get(position).getImgList().get(0))) {
                    ImageUtils.getBitmapUtils(context).display(vh.iv_rd_lv_item_article_img, list.get(position).getImgList().get(0));
                }
            }
        }
        vh.tv_rd_lv_item_article_img_count.setText(list.get(position).getImgList().size() + "");
        /**
         * 圆形头像
         */
        ImageUtils.getBitmapUtils(context).display(vh.iv_rd_lv_item_user_img, list.get(position).getArticleUser().getUserpic(), new BitmapLoadCallBack<ImageView>() {
            @Override
            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                Bitmap circleBm = ImageUtils.circleBitmap(bitmap);
                imageView.setImageBitmap(circleBm);
            }

            @Override
            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {

            }
        });
        vh.iv_rd_lv_item_user_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "头像点击事件", Toast.LENGTH_SHORT).show();
            }
        });
        vh.tv_rd_lv_item_user_name.setText(list.get(position).getArticleUser().getUsername());

        vh.tv_rd_lv_item_article_content.setText(list.get(position).getArticleContent());
        vh.tv_rd_lv_item_tag_name.setText(list.get(position).getArticleTag().getTagTitle());
        vh.tv_rd_lv_item_article_location.setText(list.get(position).getArticleLocation());
        vh.tv_rd_lv_item_article_date.setText(list.get(position).getArticleDate());
        return convertView;
    }

    private class ViewHolder {
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
        private TextView tv_rd_lv_item_article_img_count;
    }
}
