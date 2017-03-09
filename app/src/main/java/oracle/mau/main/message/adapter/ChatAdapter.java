package oracle.mau.main.message.adapter;

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

import java.util.ArrayList;

import oracle.mau.R;
import oracle.mau.application.MaUApplication;
import oracle.mau.entity.ChatEntity;
import oracle.mau.http.constants.URLConstants;
import oracle.mau.utils.ImageUtils;

/**
 * Created by shadow on 2017/3/7.
 */

public class ChatAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ChatEntity> chatList;
    private int id;
    private Bitmap circleBitmap;

    public ChatAdapter(Context context,ArrayList<ChatEntity> chatList,int id){
        this.context=context;
        this.chatList=chatList;
        this.id=id;
    }
    @Override
    public int getCount() {
        return chatList.size();
    }

    @Override
    public Object getItem(int i) {
        return chatList.get(i);
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


            if(id==chatList.get(i).getUser1().getUserid()){
                view= LayoutInflater.from(context).inflate(R.layout.item_chat_me,null);
            }else{
                view= LayoutInflater.from(context).inflate(R.layout.item_chat_other,null);
            }
            vh.userimg=(ImageView) view.findViewById(R.id.chat_userpic);
            vh.text=(TextView) view.findViewById(R.id.chat_text);
            view.setTag(vh);

        }else{
           vh=(ViewHolder) view.getTag();
        }
        String picPath="";
        if(id==chatList.get(i).getUser1().getUserid()){
             picPath= URLConstants.BASE_URL+chatList.get(i).getUser1().getUserpic();

        }else{
            picPath= URLConstants.BASE_URL+chatList.get(i).getUser2().getUserpic();
        }

        ImageUtils.getBitmapUtils(context).display(
                vh.userimg, picPath, new BitmapLoadCallBack<ImageView>() {
                    @Override
                    public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                        circleBitmap=ImageUtils.circleBitmap(bitmap);
                        vh.userimg.setImageBitmap(circleBitmap);
                        vh.userimg.invalidate();

                    }

                    @Override
                    public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {

                    }
                }
        );

            vh.text.setText(chatList.get(i).getText());
            return view;

    }

    class ViewHolder{
        ImageView userimg;
        TextView text;
    }
}
