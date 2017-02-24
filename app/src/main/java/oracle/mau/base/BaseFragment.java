package oracle.mau.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by 田帅 on 2017/1/3.
 */
public abstract class BaseFragment extends Fragment {
    private Context mContext;
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("BaseFragment",getClass().getSimpleName());
//        if (rootView == null){
            rootView = inflater.inflate(getLayoutResource(), container, false);
//        }
        initView();
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();
    }

    //获取布局文件
    protected abstract int getLayoutResource();
    //初始化view
    protected abstract void initView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected void toast(String msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
