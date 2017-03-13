package oracle.mau.main.label.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;

import oracle.mau.R;
import oracle.mau.entity.ArticleEntity;
import oracle.mau.utils.ImageUtils;
import oracle.mau.utils.ScreenUtils;
import oracle.mau.view.XCRoundRectImageView;

/**
 * Created by 田帅 on 2017/3/13.
 */

public class LabelMessageRecommendGVAdapter extends BaseAdapter {

    private Context context;
    private List<ArticleEntity> articleList;

    public LabelMessageRecommendGVAdapter(Context context, List<ArticleEntity> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    @Override
    public int getCount() {
        return articleList.size();
    }

    @Override
    public Object getItem(int position) {
        return articleList.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.gv_item_label_main_article,null);
            vh.iv_gv_item_label_main_article_img = (XCRoundRectImageView) convertView.findViewById(R.id.iv_gv_item_label_main_article_img);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        int screenWidth = ScreenUtils.getScreenWidth(context);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(screenWidth/4,screenWidth/4);
        vh.iv_gv_item_label_main_article_img.setLayoutParams(lp);
//        ImageUtils.getBitmapUtils(context).display(vh.iv_gv_item_label_main_article_img,articleList.get(position).getArticleImg());
        return convertView;
    }

    private class ViewHolder{
        private XCRoundRectImageView iv_gv_item_label_main_article_img;
    }

}
