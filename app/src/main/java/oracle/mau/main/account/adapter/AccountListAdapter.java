package oracle.mau.main.account.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import oracle.mau.R;
import oracle.mau.main.message.adapter.NewsListAdapter;

/**
 * Created by shadow on 2017/3/14.
 */

public class AccountListAdapter extends BaseAdapter {
    private Context context;
    private String [] arrtext;
    private int [] arrpic;
public AccountListAdapter(Context context,String [] arrtext,int[] arrpic){
    this.context=context;
    this.arrtext=arrtext;
    this.arrpic=arrpic;
}
    @Override
    public int getCount() {
        return arrtext.length;
    }

    @Override
    public Object getItem(int i) {
        return arrtext[i];
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
            view=LayoutInflater.from(context).inflate(R.layout.item_message_news,null);
            vh.img=(ImageView) view.findViewById(R.id.img_label);
            vh.text=(TextView) view.findViewById(R.id.text_news);
            view.setTag(vh);
        }else{
            vh=(ViewHolder) view.getTag();
        }
        vh.img.setImageResource(arrpic[i]);
        vh.text.setText(arrtext[i]);

        return view ;
    }


    class ViewHolder{
        ImageView img;
        TextView text;


    }
}
