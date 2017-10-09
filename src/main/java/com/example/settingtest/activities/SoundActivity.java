package com.example.settingtest.activities;

import android.content.Intent;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.settingtest.R;
import com.example.settingtest.adapter.SoundAdapter;
import com.example.settingtest.bean.SoundItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11070562 on 2017/10/8.
 */

public class SoundActivity extends AppCompatActivity {

    private int[] images;
    private List<SoundItem> mList;

    private static final String PHONE_RING = "phone";
    private static final String SMS_RING = "sms";
    private static final String CALENDAR_RING = "calendar";
    private static final String NOTIFY_RING = "notify";

    private ListView mListView;

    private AudioManager audioManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sound_layout);

        initData();
        initView();


    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.id_sound_list);
        SoundAdapter soundAdapter = new SoundAdapter(this, mList);
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
                        intent.putExtra("from",11);
                        startActivity(intent);
                        break;
                    case 12:
                        intent.putExtra("from",12);
                        intent.putExtra("title", mList.get(12).getTitle());
                        startActivity(intent);
                        break;
                    case 13:
                        intent.putExtra("from",13);
                        intent.putExtra("title", mList.get(13).getTitle());
                        startActivity(intent);
                        break;
                    case 14:
                        intent.putExtra("from",14);
                        intent.putExtra("title", mList.get(14).getTitle());
                        startActivity(intent);
                        break;
                }


            }
        });
    }

    private void initData() {

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
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

        String[] strings = getResources().getStringArray(R.array.sound_title_array);

        for (int i = 0; i < images.length; i++) {
            SoundItem item = new SoundItem();
            item.setTitle(strings[i]);
            item.setImage(images[i]);
            item.setContent(getResources().getString(R.string.defaultW));
            item.setSelect(false);
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
                case 11:
                case 12:
                case 13:
                case 14:
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
            mList.add(item);
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
