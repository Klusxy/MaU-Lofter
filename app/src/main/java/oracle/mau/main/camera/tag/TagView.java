package oracle.mau.main.camera.tag;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by 田帅 on 2017/2/23.
 * 标签控件
 */

public class TagView extends RelativeLayout implements View.OnClickListener{

    protected TextView mTextView;//文字描述显示View
    public ImageView mImageView;//最左边的标签图片
    public ImageView tagImageView; //链接标签图片和文字的图片

    public boolean isShow = false;

    public TagViewListener getTagViewListener() {
        return tagViewListener;
    }

    public void setTagViewListener(TagViewListener tagViewListener) {
        this.tagViewListener = tagViewListener;
    }

    //标签的点击事件
    private TagViewListener tagViewListener;

    @Override
    public void onClick(View v) {
        if (null != tagViewListener) {
            tagViewListener.onTagViewClicked(v, taginfo);
        }
    }

    public interface TagViewListener {
        public void onTagViewClicked(View view, TagInfo info);
    }

    public TagView(Context context) {
        this(context,null);
    }

    public TagView(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public TagView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOnClickListener(this);
    }

    private TagInfo taginfo;

    public void setData(TagInfo mTagInfo) {
        this.taginfo = mTagInfo;
        updateUI();
    }

    public TagInfo getData() {
        return taginfo;
    }


    protected final void updateUI() {
        if (mTextView!=null && mImageView !=null  && taginfo!=null) {
            mTextView.setText(taginfo.bname);
            mImageView.setImageResource(taginfo.bid);
        }
    }
}
