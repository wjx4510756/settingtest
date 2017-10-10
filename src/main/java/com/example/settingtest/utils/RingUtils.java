package com.example.settingtest.utils;

import android.content.Context;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import com.example.settingtest.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11070562 on 2017/10/9.
 */

public class RingUtils {

    private List<String> ringList;
    private RingtoneManager manager;
    private Context context;
    private Ringtone ringtone;

    public RingUtils() {
        ringList = new ArrayList<>();
        context = MyApplication.getContext();
        manager = new RingtoneManager(context);
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
        manager = new RingtoneManager(context);
        return ringList;
    }

    public int getCurrentIndex(int type) {
        manager = new RingtoneManager(context);
        manager.setType(type);
        Uri ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(context, type);
        return manager.getRingtonePosition(ringtoneUri);
    }

    public String getCurrentRing(int type) {

        return getRingList(type).get(getCurrentIndex(type));
    }

    public void setRingtone(int type, int position) {
        RingtoneManager.setActualDefaultRingtoneUri(context, type, manager.getRingtoneUri(position));
    }

//    public void playRingtone(int type, int position) {
//
//        manager = new RingtoneManager(context);
//        manager.setType(type);
//        Uri ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(context, RingtoneManager.TYPE_RINGTONE);
//        ringtone = RingtoneManager.getRingtone(context, ringtoneUri);
//
//        ringtone.play();
//
//    }

}

