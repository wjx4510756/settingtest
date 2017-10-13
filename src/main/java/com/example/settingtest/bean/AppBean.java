package com.example.settingtest.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by 11070562 on 2017/10/11.
 */

public class AppBean{

    private String appName;
    private String appVersion;
    private String appPackage;
    private Drawable icon;
    private boolean isSystem;

    private String appSize;
    private String catchSize;
    private String dataSize;
    private String codeSize;

    public String getCatchSize() {
        return catchSize;
    }

    public void setCatchSize(String catchSize) {
        this.catchSize = catchSize;
    }

    public String getDataSize() {
        return dataSize;
    }

    public void setDataSize(String dataSize) {
        this.dataSize = dataSize;
    }

    public String getCodeSize() {
        return codeSize;
    }

    public void setCodeSize(String codeSize) {
        this.codeSize = codeSize;
    }

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public boolean isSystem() {
        return isSystem;
    }

    public void setSystem(boolean system) {
        isSystem = system;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
