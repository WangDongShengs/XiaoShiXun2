package com.wds.weizixun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MapActivity extends AppCompatActivity {

    @BindView(R.id.mv_map)
    MapView mvMap;
    @BindView(R.id.btn_location)
    Button btnLocation;
    @BindView(R.id.btn_marker)
    Button btnMarker;
    @BindView(R.id.btn_pio)
    Button btnPio;
    @BindView(R.id.btn_guide)
    Button btnGuide;
    @BindView(R.id.btn_path)
    Button btnPath;
    private Unbinder bind;
    private BaiduMap baiduMap;
    private LocationClient mLocationClient;
    private BDLocation mLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        bind = ButterKnife.bind(this);
        baiduMap = mvMap.getMap();
        //普通地图 ,baiduMap是地图控制器对象
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //卫星地图
        //baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        //空白地图
        //baiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
        //开启交通图
        //baiduMap.setTrafficEnabled(true);
        //开启热力图
        //baiduMap.setBaiduHeatMapEnabled(true);
        //开启地图的定位图层
        baiduMap.setMyLocationEnabled(true);
        initLocation();
    }

    private void initLocation() {
        //定位初始化
        mLocationClient = new LocationClient(this);

        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

        //设置locationClientOption
        mLocationClient.setLocOption(option);

        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        //开启地图定位图层
        mLocationClient.start();
    }
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mvMap == null) {
                return;
            }
            mLocation=location;
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            baiduMap.setMyLocationData(locData);
        }
    }

    @OnClick({R.id.btn_location, R.id.btn_marker, R.id.btn_pio, R.id.btn_guide, R.id.btn_path})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_location:
                locationToMe();
                break;
            case R.id.btn_marker:
                addMarker();
                break;
            case R.id.btn_pio:
                break;
            case R.id.btn_guide:
                break;
            case R.id.btn_path:
                break;
        }
    }
    //覆盖物
    private void addMarker() {
        //获取当前地图屏幕中心点的坐标
        LatLng target = baiduMap.getMapStatus().target;
        //定义maker 坐标点
        LatLng point = new LatLng(target.latitude, target.longitude);
        //构建marker图标
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.btn_icon_read);
        //构建markerOption,用于在地图上添加marker
        MarkerOptions markerOptions = new MarkerOptions().position(point).icon(bitmapDescriptor);
        //在地图上添加marker，并显示
        baiduMap.addOverlay(markerOptions);
    }
    //定位
    private void locationToMe() {
        //如果已经定位了，只需要将地图界面移动到用户所在位置即可
        //改变地图手势的中心点（地图的中心点）
        //mLocation 是定位时获取到的用户位置信息对象

        LatLng latLng = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
        //改变地图手势中心点
        baiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(latLng));

    }


    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mvMap.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mvMap.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mvMap.onDestroy();
        bind.unbind();
        mLocationClient.stop();
    }
}
