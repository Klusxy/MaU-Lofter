package oracle.mau.main.camera.tag;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import oracle.mau.R;


/**
 * Created by 田帅 on 2017/2/23.
 */

public class TagViewLeft extends TagView {
    public TagViewLeft(Context context) {
        this(context,null);
    }

    public TagViewLeft(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TagViewLeft(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.tag_view_left, this);
        this.mTextView = (TextView) findViewById(R.id.tv_tagview_content);
        this.mTextView.getBackground().setAlpha(178);
        this.mTextView.setVisibility(View.VISIBLE);
        this.mImageView = (ImageView) findViewById(R.id.iv_tagview_tag);
        this.tagImageView = (ImageView) findViewById(R.id.iv_tagview_flag);
        updateUI();
    }
}
