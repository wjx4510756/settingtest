package com.example.settingtest.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;

import com.example.settingtest.bean.AppSizeBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by 11070562 on 2017/10/12.
 */

public class AppUtils {


    public static String getSize(long size) {

        if (size == 0) {
            return "";
        }

        double per = 1048576;
        String s;
        if (size > 1024 * 1024)
            s = String.format("%.2f", (double) size / per) + "M";
        else
            s = String.format("%.2f", (double) size / (1024)) + "KB";
        return s;

    }

    public static boolean clearAppData(final Context context, String pkgName) {

        Method method;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        return true;

    }


    public static void getPkgSize(final Context context, final Handler handler, List<String> pkgNames) {
        // getPackageSizeInfo是PackageManager中的一个private方法，所以需要通过反射的机制来调用
        Method method;
        PackageManager pm = context.getPackageManager();


        try {

            Method myUserId = UserHandle.class.getDeclaredMethod("myUserId");
            int userID = (Integer) myUserId.invoke(UserHandle.class);

            method = pm.getClass().getDeclaredMethod("getPackageSizeInfoAsUser", String.class, int.class, IPackageStatsObserver.class);


            for (int i = 0; i < pkgNames.size(); i++) {
                final int finalI = i;
                method.invoke(pm, pkgNames.get(i), userID, new IPackageStatsObserver.Stub() {

                    //回调是在子线程中异步完成的
                    @Override
                    public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) throws RemoteException {
                        if (succeeded && pStats != null) {
                            synchronized (AppSizeBean.class) {

                                AppSizeBean appInfo = new AppSizeBean();


                                appInfo.setCatchSize(getSize(pStats.cacheSize));//缓存大小
                                appInfo.setDataSize(getSize(pStats.dataSize));  //数据大小
                                appInfo.setCodeSize(getSize(pStats.codeSize));  //应用大小
                                appInfo.setAppSize(pStats.cacheSize + pStats.codeSize + pStats.dataSize);//应用的总大小

                                Message message = new Message();

                                message.obj = appInfo;
                                message.what = 1;
                                message.arg1 = finalI;

                                Log.d("thread", String.valueOf(Thread.currentThread()));


//                            //视觉效果
//                            try {
//                                Thread.sleep(80);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }

                                handler.sendMessage(message);

                                Log.d("TAG", appInfo.getAppSize() + "");
                            }
                        }
                    }
                });
            }

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            Log.d("TAG", "onGetStatsCompleted : onFailed catch ");
        }

    }

}
