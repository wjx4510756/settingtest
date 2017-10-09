package com.example.settingtest.bean;

/**
 * Created by 11070562 on 2017/10/8.
 */

public class Item {

    private int image;
    private String title;
    private int more;
    private boolean showDivider;

    public boolean isShowDivider() {
        return showDivider;
    }

    public void setShowDivider(boolean showDivider) {
        this.showDivider = showDivider;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int type;

    public int getMore() {
        return more;
    }

    public void setMore(int more) {
        this.more = more;
    }



    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
