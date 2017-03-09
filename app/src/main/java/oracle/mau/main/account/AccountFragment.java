package oracle.mau.main.account;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import oracle.mau.R;
import oracle.mau.base.BaseFragment;
import oracle.mau.utils.ImageUtils;

/**
 * Created by 田帅 on 2017/2/20.
 */

public class AccountFragment extends BaseFragment {
    private ImageView iv_user_img;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_account;
    }

    /**
     * 创建对象方法
     * @return
     */
    public static AccountFragment newInstance(int userId) {
        AccountFragment a = new AccountFragment();
        Bundle b = new Bundle();
        b.putInt("userID",userId);
        a.setArguments(b);
        return a;
    }

    @Override
    protected void initView() {
        iv_user_img = (ImageView) rootView.findViewById(R.id.iv_account_user_img);
        updateUI();
    }

    private void updateUI() {
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.caise1);
        Bitmap circleBitmap = ImageUtils.circleBitmap(bitmap);
        iv_user_img.setImageBitmap(circleBitmap);
    }
}
