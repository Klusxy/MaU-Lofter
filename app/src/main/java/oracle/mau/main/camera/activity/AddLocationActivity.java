package oracle.mau.main.camera.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import oracle.mau.R;
import oracle.mau.base.BaseActivity;
import oracle.mau.main.camera.adapter.AddLocationAdapter;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 田帅 on 2017/2/28.
 */

public class AddLocationActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener{

    /**
     * 返回按钮
     */
    private ImageView iv_add_location_back;
    /**
     * 百度定位
     */
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    /**
     * 定位之后获取到的Poi集合
     */
    private List<String> locationList;
    private AddLocationAdapter mAdapter;
    private ListView lv_add_location_location;

    /**
     * 返回码
     */
    public final static int ADDLOCATION_BACKCODE = 10002;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_location;
    }

    @Override
    public void initView() {
        lv_add_location_location = (ListView) findViewById(R.id.lv_add_location_location);
        lv_add_location_location.setOnItemClickListener(this);
        iv_add_location_back = (ImageView) findViewById(R.id.iv_add_location_back);
        iv_add_location_back.setOnClickListener(this);
        /**
         * 初始化百度定位功能
         */
        initBaiduLocation();
    }

    /**
     * 初始化百度定位功能
     */
    private void initBaiduLocation() {
        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系


        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocationClient.setLocOption(option);
        /**
         * 开始定位
         */
        mLocationClient.start();
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add_location_back:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("location",locationList.get(position));
        setResult(ADDLOCATION_BACKCODE,intent);
        finish();
    }

    /**
     * 获取位置之后回调
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            locationList = new ArrayList<>();
            locationList.add(location.getAddrStr());
            List<Poi> poiList = location.getPoiList();
            for (Poi poi : poiList) {
                locationList.add(poi.getName());
            }
            /**
             * 设置listview
             */
            configListView();
        }
    }

    /**
     * 设置listview
     */
    private void configListView() {
        mAdapter = new AddLocationAdapter(this, locationList);
        lv_add_location_location.setAdapter(mAdapter);
    }
}
