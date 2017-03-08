package oracle.mau.main.message.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import oracle.mau.R;
import oracle.mau.application.MaUApplication;
import oracle.mau.entity.ChatEntity;

/**
 * Created by shadow on 2017/3/7.
 */

public class ChatAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ChatEntity> chatList;

    public ChatAdapter(Context context,ArrayList<ChatEntity> chatList){
        this.context=context;
        this.chatList=chatList;
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh=null;
        if(view==null){
           /* vh=new ViewHolder();
            MaUApplication maUApplication=(MaUApplication)getApplication();
            int userid=maUApplication.getUser().getUserid();
            if(userid==chatList.get(i).getUser1().getUserid()){
                view= LayoutInflater.from(context).inflate(R.layout.item_chat_me,null);
            }else{
                view= LayoutInflater.from(context).inflate(R.layout.item_chat_other,null);
            }
            vh.userimg=(ImageView) view.findViewById(R.id.chat_userpic);
            vh.text=(TextView) view.findViewById(R.id.chat_text);
            view.setTag(vh);*/

        }else{
           // vh=(ViewHolder) view.getTag();
        }

        return null;
    }

    class ViewHolder{
        ImageView userimg;
        TextView text;
    }
}
