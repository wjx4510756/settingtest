package com.example.settingtest.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.settingtest.MyApplication;
import com.example.settingtest.R;
import com.example.settingtest.adapter.SoundAdapter;
import com.example.settingtest.bean.SoundItem;
import com.example.settingtest.utils.RingUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11070562 on 2017/10/8.
 */

public class SoundActivity extends AppCompatActivity {

    private int[] images;
    private List<SoundItem> mList;
    private SoundAdapter soundAdapter;

    private static final String PHONE_RING = "phone";
    private static final String SMS_RING = "sms";
    private static final String CALENDAR_RING = "calendar";
    private static final String NOTIFY_RING = "notify";

    private String phoneRing;
    private String smsRing;
    private String calendarRing;
    private String notifyRing;

    private SharedPreferences preferences;

    private RingUtils ringUtils;


    private ListView mListView;
    private ImageView back;

    private AudioManager audioManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sound_layout);

        initData();
        initView();


    }

    private void initView() {

        back = (ImageView) findViewById(R.id.id_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mListView = (ListView) findViewById(R.id.id_sound_list);
        soundAdapter = new SoundAdapter(this, mList);
        mListView.setAdapter(soundAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SoundActivity.this, RingSelectActivity.class);
                intent.putExtra("type", RingtoneManager.TYPE_NOTIFICATION);
                switch (i) {
                    case 11:
                        intent.putExtra("type", RingtoneManager.TYPE_RINGTONE);
                        intent.putExtra("title", mList.get(11).getTitle());
                        intent.putExtra("from", 11);
                        startActivityForResult(intent, i);
                        break;
                    case 12:
                        intent.putExtra("from", 12);
                        intent.putExtra("title", mList.get(12).getTitle());
                        startActivityForResult(intent, i);

                        break;
                    case 13:
                        intent.putExtra("from", 13);
                        intent.putExtra("title", mList.get(13).getTitle());
                        startActivityForResult(intent, i);
                        break;
                    case 14:
                        intent.putExtra("from", 14);
                        intent.putExtra("title", mList.get(14).getTitle());
                        startActivityForResult(intent, i);
                        break;
                }


            }
        });
    }

    private void initData() {

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        ringUtils = new RingUtils();

        int currentRingVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
//        int currentMusicVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
//        int currentAlarmVolume = audioManager.getStreamVolume(AudioManager.STREAM_ALARM);

        mList = new ArrayList<>();

        images = new int[23];
        for (int i = 0; i < 23; i++) {
            images[i] = 0;
        }
        images[4] = R.drawable.ic_settings_sound_ring;
        images[5] = R.drawable.ic_settings_sound_music;
        images[6] = R.drawable.ic_settings_sound_alarm;


        preferences = MyApplication.getPreferences();
        String defaultW = getResources().getString(R.string.defaultW);

//        phoneRing = preferences.getString("phoneRing", defaultW);
        phoneRing = ringUtils.getCurrentRing(RingtoneManager.TYPE_RINGTONE);

        smsRing = preferences.getString("smsRing", defaultW);
//        smsRing = ringUtils.getCurrentRing(RingtoneManager.TYPE_NOTIFICATION);

        calendarRing = preferences.getString("calendarRing", defaultW);
        notifyRing = preferences.getString("notifyRing", defaultW);

        String[] strings = getResources().getStringArray(R.array.sound_title_array);

        for (int i = 0; i < images.length; i++) {
            SoundItem item = new SoundItem();
            item.setTitle(strings[i]);
            item.setImage(images[i]);
            item.setContent(getResources().getString(R.string.defaultW));
            item.setSelect(false);
            item.setShowDivider(true);
            switch (i) {
                case 0:
                case 3:
                case 7:
                case 9:
                case 10:
                case 16:
                case 22:
                    item.setType(SoundAdapter.TYPE_DIVIDER);
                    break;
                case 4:
                case 5:
                case 6:
                    item.setType(SoundAdapter.TYPE_SPECIAL);
                    break;
                case 8:
                    item.setType(SoundAdapter.TYPE_MORE);
                    break;
                case 11:
                    item.setContent(phoneRing);
                    item.setType(SoundAdapter.TYPE_MORE);
                    break;
                case 12:
                    item.setContent(smsRing);
                    item.setType(SoundAdapter.TYPE_MORE);
                    break;
                case 13:
                    item.setContent(calendarRing);
                    item.setType(SoundAdapter.TYPE_MORE);
                    break;
                case 14:
                    item.setContent(notifyRing);
                    item.setType(SoundAdapter.TYPE_MORE);
                    break;
                case 1:
                case 2:
                    //设置静音Switch状态
                    if (currentRingVolume == 0)
                        item.setSelect(true);
                default:
                    item.setType(SoundAdapter.TYPE_NORMAL);
                    break;
            }

            switch (i) {
                case 2:
                case 6:
                case 15:
                case 21:
                    item.setShowDivider(false);
            }
            mList.add(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("TAG", "onActivityResult: ");
        if (resultCode == RESULT_OK) {

            String ring = data.getStringExtra("ring");
            switch (requestCode) {
                case 11:
                    mList.get(11).setContent(ring);
                    break;
                case 12:
                    mList.get(12).setContent(ring);
                    break;
                case 13:
                    mList.get(13).setContent(ring);
                    break;
                case 14:
                    mList.get(14).setContent(ring);
                    break;
            }

            soundAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
