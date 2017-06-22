package com.example.tiange.updatemain;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * User: xyp
 * Date: 2017/3/9
 * Time: 10:06
 */

public class Util {
    private Util util;
    private Util(){};
    public Util getInstance(){
        if(util==null){
            synchronized (Util.class){
                if(util==null){
                    util=new Util();
                }
            }
        }
        return util;
    }

    //是否处于前台进程
    public static boolean isAppOnForeground(Context context, String packageName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List appProcessList = activityManager.getRunningAppProcesses();
        if (null == appProcessList) {
            return false;
        }

        for (Object anAppProcessList : appProcessList) {
            ActivityManager.RunningAppProcessInfo appProcessInfo = (ActivityManager.RunningAppProcessInfo) anAppProcessList;

            //进程名，默认是包名或者由属性android:process=""指定
            if (appProcessInfo.processName.equals(packageName)) {
                return appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
            }
        }
        return false;
    }
}
