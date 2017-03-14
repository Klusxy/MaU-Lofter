package oracle.mau.main.label.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import oracle.mau.entity.LabelTagEntity;

/**
 * Created by 田帅 on 2017/3/13.
 */

public class SearchTagAdapter extends BaseAdapter {
    private Context context;
    private List<LabelTagEntity> list;

    public SearchTagAdapter(Context context, List<LabelTagEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
