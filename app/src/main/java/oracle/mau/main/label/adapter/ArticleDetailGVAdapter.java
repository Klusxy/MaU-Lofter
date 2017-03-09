package oracle.mau.main.label.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

import java.util.List;

import oracle.mau.R;
import oracle.mau.utils.DensityUtils;
import oracle.mau.utils.ImageUtils;
import oracle.mau.utils.ScreenUtils;

/**
 * Created by 田帅 on 2017/3/9.
 */

public class ArticleDetailGVAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;

    public ArticleDetailGVAdapter(Context context, List<String> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.gv_item_article_img, null);
            vh.iv_gv_item_article_img = (ImageView) convertView.findViewById(R.id.iv_gv_item_article_img);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        int screenWidth = ScreenUtils.getScreenWidth(context);

        LinearLayout.LayoutParams lp ;
        if (list.size() == 1) {
            lp =new LinearLayout.LayoutParams(screenWidth, screenWidth*3/4);
            vh.iv_gv_item_article_img.setLayoutParams(lp);
        }
        if (list.size() == 2) {
            lp =new LinearLayout.LayoutParams(screenWidth/2, screenWidth*3/8);
            vh.iv_gv_item_article_img.setLayoutParams(lp);
        }
        if (list.size() >= 3) {
            lp =new LinearLayout.LayoutParams(screenWidth/3, screenWidth/4);
            vh.iv_gv_item_article_img.setLayoutParams(lp);
        }
        ImageUtils.getBitmapUtils(context).display(vh.iv_gv_item_article_img, list.get(position));
        return convertView;
    }

    private class ViewHolder {
        private ImageView iv_gv_item_article_img;
    }
}
