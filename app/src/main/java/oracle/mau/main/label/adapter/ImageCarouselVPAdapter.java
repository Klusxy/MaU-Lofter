package oracle.mau.main.label.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by 田帅 on 2017/2/28.
 */

public class ImageCarouselVPAdapter extends PagerAdapter {
    private List<ImageView> imageViewList;

    public ImageCarouselVPAdapter(List<ImageView> imageViewList) {
        this.imageViewList = imageViewList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageViewList.get(position % imageViewList.size()));
        return imageViewList.get(position % imageViewList.size());
    }
}
