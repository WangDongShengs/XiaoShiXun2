package com.wds.common;

import android.app.ActivityManager;
import android.app.Application;
import android.content.pm.PackageManager;
import android.util.Log;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import java.util.Iterator;
import java.util.List;

public class MyApp extends Application {
    private static MyApp app;

    public static MyApp getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;

        initUMeng();
        initIM();
    }

    private static final String TAG = "MyApp";
    private void initIM() {
        EMOptions options = new EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
// 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true);
// 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true);

        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
// 如果APP启用了远程的service，此application:onCreate会被调用2次
// 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
// 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        if (processAppName == null ||!processAppName.equalsIgnoreCase(this.getPackageName())) {
            Log.e(TAG, "enter the service process!");

            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
//初始化
        EMClient.getInstance().init(this, options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }


    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }

    private void initUMeng() {

        // 友盟AppKey 5eddab46dbc2ec083df19242
        //签名：78bf69b85ea58b7e29c95a9e6041127e
        // 微博
        //App Key：754288490
        //App Secret：113f55dbf54c417f71bfc5165495a05f

        UMConfigure.init(this,"5eddab46dbc2ec083df19242","umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0


        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo("1549154155", "78bf69b85ea58b7e29c95a9e6041127e","https://api.weibo.com/oauth2/default.html");
        //PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        //PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
        //PlatformConfig.setAlipay("2015111700822536");
        //PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
        //PlatformConfig.setPinterest("1439206");
        //PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f");
        //PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
        //PlatformConfig.setVKontakte("5764965","5My6SNliAaLxEm3Lyd9J");
        //PlatformConfig.setDropbox("oz8v5apet3arcdy","h7p2pjbzkkxt02a");*/
        // PlatformConfig.setYnote("9c82bf470cba7bd2f1819b0ee26f86c6ce670e9b");
    }
}
