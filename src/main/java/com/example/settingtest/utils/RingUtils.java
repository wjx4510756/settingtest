package com.example.settingtest.utils;

import android.database.Cursor;
import android.media.RingtoneManager;

import com.example.settingtest.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11070562 on 2017/10/9.
 */

public class RingUtils {

    private List<String> ringList;
    private RingtoneManager manager;

    public RingUtils() {
        ringList = new ArrayList<>();
        manager = new RingtoneManager(MyApplication.getContext());
    }

    public List<String> getRingList(int type) {

        manager.setType(type);
        Cursor cursor = manager.getCursor();

        if (cursor.moveToFirst()) {
            do {
                String string = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX);
                ringList.add(string);
            } while (cursor.moveToNext());
        }

        return ringList;
    }


}

