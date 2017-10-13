package com.example.settingtest.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by 11070562 on 2017/10/12.
 */

public class AppItem {

    private Drawable icon;
    private String title;
    private String content;


    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
