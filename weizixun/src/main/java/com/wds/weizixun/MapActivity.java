package com.wds.weizixun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.walknavi.WalkNavigateHelper;
import com.baidu.mapapi.walknavi.adapter.IWEngineInitListener;
import com.baidu.mapapi.walknavi.adapter.IWRoutePlanListener;
import com.baidu.mapapi.walknavi.model.WalkRoutePlanError;
import com.baidu.mapapi.walknavi.params.WalkNaviLaunchParam;
import com.wds.utils.PoiOverlay;
import com.wds.utils.WalkingRouteOverlay;


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
    private PoiSearch mPoiSearch;
    private WalkNaviLaunchParam mParam;
    private RoutePlanSearch mSearch;

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
       // initIntent();
    }

    private void initIntent() {
        //im 跳转来确定位置
        double lat = getIntent().getDoubleExtra("lat", 0);
        double aLong = getIntent().getDoubleExtra("long", 1);
        LatLng latLng = new LatLng(lat, aLong);
        baiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(latLng));
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
                pio();
                break;
            case R.id.btn_guide:
                guide();
                break;
            case R.id.btn_path:
                path();
                break;
        }
    }

    private void path() {
        //创建路线规划检索实例
        mSearch = RoutePlanSearch.newInstance();
        //创建路线规划检索结果监听器
        OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
            @Override
            public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
                //创建WalkingRouteOverlay实例
                WalkingRouteOverlay overlay = new WalkingRouteOverlay(baiduMap);
                if (walkingRouteResult.getRouteLines().size() > 0) {
                    //获取路径规划数据,(以返回的第一条数据为例)
                    //为WalkingRouteOverlay实例设置路径数据
                    overlay.setData(walkingRouteResult.getRouteLines().get(0));
                    //在地图上绘制WalkingRouteOverlay
                    overlay.addToMap();
                }
            }

            @Override
            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

            }

            @Override
            public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

            }

            @Override
            public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

            }

            @Override
            public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

            }

            @Override
            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

            }

        };
        //设置路线规划检索监听器
        mSearch.setOnGetRoutePlanResultListener(listener);
        //准备起终点信息
        PlanNode stNode = PlanNode.withCityNameAndPlaceName("北京", "西二旗地铁站");
        PlanNode enNode = PlanNode.withCityNameAndPlaceName("北京", "百度科技园");
        //发起检索
        mSearch.walkingSearch((new WalkingRoutePlanOption())
                .from(stNode)
                .to(enNode));
    }

    private void guide() {
        // 获取导航控制类
        // 引擎初始化
        WalkNavigateHelper.getInstance().initNaviEngine(this, new IWEngineInitListener() {

            @Override
            public void engineInitSuccess() {
                //引擎初始化成功的回调
                routeWalkPlanWithParam();
                Log.e(TAG, "engineInitSuccess: " );
            }

            @Override
            public void engineInitFail() {
                //引擎初始化失败的回调
                Log.e(TAG, "engineInitFail: ");
            }
        });
    }

    private static final String TAG = "MapActivity";
    private void routeWalkPlanWithParam() {
        //起终点位置
        LatLng startPt = new LatLng(40.047416, 116.312143);
        LatLng endPt = new LatLng(40.048424, 116.313513);
        //构造WalkNaviLaunchParam
        mParam = new WalkNaviLaunchParam().stPt(startPt).endPt(endPt);
        //发起算路
        WalkNavigateHelper.getInstance().routePlanWithParams(mParam, new IWRoutePlanListener() {
            @Override
            public void onRoutePlanStart() {
                //开始算路的回调
                Log.e(TAG, "onRoutePlanStart: " );
            }

            @Override
            public void onRoutePlanSuccess() {
                //算路成功
                //跳转至诱导页面
                Intent intent = new Intent(MapActivity.this, WNaviGuideActivity.class);
                startActivity(intent);
                Log.e(TAG, "onRoutePlanSuccess: ");
            }

            @Override
            public void onRoutePlanFail(WalkRoutePlanError walkRoutePlanError) {
                //算路失败的回调
                Log.e(TAG, "onRoutePlanFail: " );
            }
        });

    }

    //PIO检索
    private void pio() {
        //创建POI检索实例
        mPoiSearch = PoiSearch.newInstance();
        //创建POI检索监听器
        OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                    baiduMap.clear();

                    //创建PoiOverlay对象
                    PoiOverlay poiOverlay = new PoiOverlay(baiduMap);

                    //设置Poi检索数据
                    poiOverlay.setData(poiResult);

                    //将poiOverlay添加至地图并缩放至合适级别
                    poiOverlay.addToMap();
                    poiOverlay.zoomToSpan();
                }
            }
            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }
            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
            //废弃
            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }
        };

        //设置检索监听器
        mPoiSearch.setOnGetPoiSearchResultListener(listener);
        /**
         *  PoiCiySearchOption 设置检索属性
         *  city 检索城市
         *  keyword 检索内容关键字
         *  pageNum 分页页码
         */
      /*  mPoiSearch.searchInCity(new PoiCitySearchOption()
                .city("北京") //必填
                .keyword("美食") //必填
                .pageNum(10));*/


        /**
         * POI周边检索
         * 以天安门为中心，搜索半径100米以内的餐厅
         */
       /* mPoiSearch.searchNearby(new PoiNearbySearchOption()
                .location(new LatLng(39.915446, 116.403869))
                .radius(10000)
                .keyword("餐厅")
                .pageNum(10));*/


        /**
         * POI区域检索（矩形区域检索）
         * 设置矩形检索区域
         */
        LatLngBounds searchBounds = new LatLngBounds.Builder()
                .include(new LatLng( 39.92235, 116.380338 ))
                .include(new LatLng( 39.947246, 116.414977))
                .build();

        /**
         * 在searchBounds区域内检索餐厅
         */
        mPoiSearch.searchInBound(new PoiBoundSearchOption()
                .bound(searchBounds)
                .keyword("餐厅"));
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

        //释放检索实例
        mPoiSearch.destroy();
        //释放检索实例
        mSearch.destroy();
    }
}
