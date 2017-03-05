package oracle.mau.main.label.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import oracle.mau.R;
import oracle.mau.entity.LabelRecommendEntity;
import oracle.mau.utils.ScreenUtils;

/**
 * Created by 田帅 on 2017/3/5.
 */

public class LabelMainReommendLabelLVAdapter extends BaseAdapter {
    private Context context;

    public LabelMainReommendLabelLVAdapter(Context context, List<LabelRecommendEntity> list, int[] bgs, List<int[]> imgsList) {
        this.context = context;
        this.list = list;
        this.bgs = bgs;
        this.imgsList = imgsList;
    }

    private List<LabelRecommendEntity> list;
    private int[] bgs;
    private List<int[]> imgsList;


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
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_recommend_label_item, null);
            vh.iv_lv_lm_rl_bg = (ImageView) convertView.findViewById(R.id.iv_lv_lm_rl_bg);
            vh.tv_lv_lm_rl_title = (TextView) convertView.findViewById(R.id.tv_lv_lm_rl_title);
            vh.tv_lv_lm_rl_par_num = (TextView) convertView.findViewById(R.id.tv_lv_lm_rl_par_num);
            vh.ll_lv_lm_rl_imgs = (LinearLayout) convertView.findViewById(R.id.ll_lv_lm_rl_imgs);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        LabelRecommendEntity lr = list.get(position);
        vh.iv_lv_lm_rl_bg.setImageResource(bgs[position]);
        vh.tv_lv_lm_rl_title.setText(lr.getLrTitle());
        vh.tv_lv_lm_rl_par_num.setText(lr.getLrParticipationNum() + "参与");
        Log.d("sadasdas", lr.getLrTitle());
        /**
         * 得到屏幕的宽度
         */
        int screenWidth = ScreenUtils.getScreenWidth(context);
        int[] imgs = imgsList.get(position);
        for (int i = 0; i < imgs.length; i++) {
            ImageView iv = new ImageView(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(screenWidth / 3, screenWidth / 3);
            lp.setMargins(1, 0, 1, 0);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setLayoutParams(lp);
            iv.setImageResource(imgs[i]);
            vh.ll_lv_lm_rl_imgs.addView(iv);
        }
        return convertView;
    }

    private class ViewHolder {
        private ImageView iv_lv_lm_rl_bg;
        private TextView tv_lv_lm_rl_title;
        private TextView tv_lv_lm_rl_par_num;
        private LinearLayout ll_lv_lm_rl_imgs;
    }
}
