package oracle.mau.main.message.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import oracle.mau.R;

/**
 * Created by shadow on 2017/3/3.
 */

public class NewsListAdapter extends BaseAdapter {
    private Context context;
    private String[] msgtext;
    private int[] msgpic;
     public NewsListAdapter(Context context,String[] msgtext,int[] msgpic){
         this.context=context;
         this.msgpic=msgpic;
         this.msgtext=msgtext;
     }

    @Override
    public int getCount() {
        return msgtext.length;
    }

    @Override
    public Object getItem(int i) {
        return msgtext[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //xml--view
        ViewHolder vh=null;
        if(view==null){
            vh=new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.item_message_news, null);
            vh.imgLabel=(ImageView) view.findViewById(R.id.img_label);
            vh.text=(TextView) view.findViewById(R.id.text_news);
            view.setTag(vh);
        }else{
            vh=(ViewHolder) view.getTag();
        }
        vh.imgLabel.setImageResource(msgpic[i]);
        vh.text.setText(msgtext[i]);

        return view;
    }


    class ViewHolder{
        ImageView imgLabel;
        TextView text;

    }
}
