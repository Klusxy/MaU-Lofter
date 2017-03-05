package oracle.mau.main.message.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by shadow on 2017/3/3.
 */

public class NewsListAdapter extends BaseAdapter {
    private Context context;
    private String[] msgtext;
    private int[] msgpic;
     public void NewsListAdapter(Context context,String[] msgtext,int[] msgpic){
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
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
