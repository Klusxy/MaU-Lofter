package oracle.mau.main.account.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

import java.util.ArrayList;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.entity.UserEntity;
import oracle.mau.utils.ImageUtils;

/**
 * Created by shadow on 2017/3/15.
 */

public class AttentionPeoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<UserEntity> peopleList;
    public AttentionPeoAdapter(Context context,ArrayList<UserEntity> peopleList){
        this.context=context;
        this.peopleList=peopleList;
    }
    @Override
    public int getCount() {
        return peopleList.size();
    }

    @Override
    public Object getItem(int i) {
        return peopleList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
private ViewHolder vh=null;
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            vh=new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.item_attention,null);
            vh.img=(ImageView) view.findViewById(R.id.attention_item_pic);

            vh.txt=(TextView) view.findViewById(R.id.attention_item_txt);

            view.setTag(vh);
        }else{
            vh=(ViewHolder)view.getTag();
        }
        ImageUtils.getBitmapUtils(context).display(vh.img, peopleList.get(i).getUserpic(), new BitmapLoadCallBack<ImageView>() {
            @Override
            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
               Bitmap circle= ImageUtils.circleBitmap(bitmap);
                vh.img.setImageBitmap(circle);
            }

            @Override
            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {

            }
        });

        vh.txt.setText(peopleList.get(i).getUsername());
        return view;
    }

    class ViewHolder{
        ImageView img;
        TextView txt;
    }
}
