package oracle.mau.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import oracle.mau.utils.ScreenUtils;

/**
 * Created by 田帅 on 2017/2/28.
 */

public class ThinLineView extends View {
    private Context mContext;
    public ThinLineView(Context context) {
        this(context,null);
    }

    public ThinLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ThinLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawLine(0,0, ScreenUtils.getScreenWidth(mContext),0,paint);
    }
}
