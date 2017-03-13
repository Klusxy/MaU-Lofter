package oracle.mau.main.label.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

import java.util.List;

import oracle.mau.R;
import oracle.mau.entity.UserEntity;
import oracle.mau.utils.ImageUtils;

/**
 * Created by 田帅 on 2017/3/13.
 */

public class SearchUserAdapter extends BaseAdapter {
    private Context context;
    private List<UserEntity> list;

    public SearchUserAdapter(Context context, List<UserEntity> list) {
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

        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_item_search_user,null);
            vh.iv_lv_item_search_user_img = (ImageView) convertView.findViewById(R.id.iv_lv_item_search_user_img);
            vh.tv_lv_item_search_user_name = (TextView) convertView.findViewById(R.id.tv_lv_item_search_user_name);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        ImageUtils.getBitmapUtils(context).display(vh.iv_lv_item_search_user_img, list.get(position).getUserpic(), new BitmapLoadCallBack<ImageView>() {
            @Override
            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                Bitmap circleBm = ImageUtils.circleBitmap(bitmap);
                imageView.setImageBitmap(circleBm);
            }

            @Override
            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {

            }
        });
        vh.tv_lv_item_search_user_name.setText(list.get(position).getUsername());
        return convertView;
    }

    private class ViewHolder{
        private ImageView iv_lv_item_search_user_img;
        private TextView tv_lv_item_search_user_name;
    }

}
