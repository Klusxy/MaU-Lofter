package oracle.mau.main.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import oracle.mau.R;


/**
 * Created by Administrator on 2017/3/2.
 */

public class SubscriptionFragment extends Fragment implements AdapterView.OnItemLongClickListener{



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      View  view=inflater.inflate(R.layout.fragment_homesubscription,container,false);

        return view;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }



}
