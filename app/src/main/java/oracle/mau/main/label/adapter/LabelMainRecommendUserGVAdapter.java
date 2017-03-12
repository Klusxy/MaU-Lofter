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

import com.bumptech.glide.Glide;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

import java.util.List;

import oracle.mau.R;
import oracle.mau.entity.UserEntity;
import oracle.mau.utils.DensityUtils;
import oracle.mau.utils.ImageUtils;
import oracle.mau.utils.ScreenUtils;

/**
 * Created by 田帅 on 2017/3/6.
 */

public class LabelMainRecommendUserGVAdapter extends BaseAdapter {
    private Context context;
    private List<UserEntity> list;

    public LabelMainRecommendUserGVAdapter(Context context, List<UserEntity> list) {
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
        if (convertView==null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.gv_item_label_main_user_recommend,null);
            vh.iv_gv_item_label_main_user_img = (ImageView) convertView.findViewById(R.id.iv_gv_item_label_main_user_img);
            vh.tv_gv_item_label_main_user_name = (TextView) convertView.findViewById(R.id.tv_gv_item_label_main_user_name);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.iv_gv_item_label_main_user_img.setFocusable(false);

        vh.iv_gv_item_label_main_user_img.setFocusableInTouchMode(false);

        int screenWidth = ScreenUtils.getScreenWidth(context);
//        Glide.with(context).load(userImg[position]).into(vh.iv_gv_item_label_main_user_img);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(screenWidth/6,screenWidth/6);
        vh.iv_gv_item_label_main_user_img.setLayoutParams(lp);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp.setMargins(DensityUtils.dp2px(context,10),10,DensityUtils.dp2px(context,10),0);
        ImageUtils.getBitmapUtils(context).display(vh.iv_gv_item_label_main_user_img, list.get(position).getUserpic(), new BitmapLoadCallBack<ImageView>() {
            @Override
            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                Bitmap circleBitmap = ImageUtils.circleBitmap(bitmap);
                imageView.setImageBitmap(circleBitmap);
            }

            @Override
            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {

            }
        });
//        vh.iv_gv_item_label_main_user_img.setImageBitmap(circleBitmap);

        vh.tv_gv_item_label_main_user_name.setText(list.get(position).getUsername());

        return convertView;
    }

    private class ViewHolder{
        private ImageView iv_gv_item_label_main_user_img;
        private TextView tv_gv_item_label_main_user_name;
    }
}
