package com.example.settingtest.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.settingtest.MyApplication;
import com.example.settingtest.R;
import com.example.settingtest.utils.RingUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 11070562 on 2017/10/9.
 */

public class RingSelectActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private ListView mListView;
    private TextView mTitle;
    private ImageView back;

    private String title;
    private int from;

    private RingUtils ringUtils;

    private SimpleAdapter adapter;
    private List<String> songList;
    private List<Map<String, Object>> mList;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private AudioManager manager;

    private int currentSelectedIndex;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ring_select_layout);


        initData();

        initView();

    }

    private void initData() {

        mediaPlayer = new MediaPlayer();

        preferences = MyApplication.getPreferences();
        editor = MyApplication.getEditor();

        manager = (AudioManager) getSystemService(AUDIO_SERVICE);

        songList = new ArrayList<>();
        mList = new ArrayList<>();


        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        int type = intent.getIntExtra("type", -1);
        from = intent.getIntExtra("from", -1);

        ringUtils = new RingUtils();
        songList = ringUtils.getRingList(type);

        switch (from) {
            case 11:
                currentSelectedIndex = ringUtils.getCurrentIndex(RingtoneManager.TYPE_RINGTONE);
//                currentSelectedIndex = preferences.getInt("phone", 0);
                break;
            case 12:
                currentSelectedIndex = preferences.getInt("sms", 0);
                break;
            case 13:
                currentSelectedIndex = preferences.getInt("calendar", 0);
                break;
            case 14:
                currentSelectedIndex = preferences.getInt("notify", 0);
                break;
        }


        for (int i = 0; i < songList.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("ring", songList.get(i));
            map.put("select", null);
            if (i == currentSelectedIndex)
                map.put("select", R.drawable.selected);
            mList.add(map);
        }

    }

    private void initView() {

        mListView = (ListView) findViewById(R.id.id_listView);
        mTitle = (TextView) findViewById(R.id.id_title);
        back = (ImageView) findViewById(R.id.id_back);

        mTitle.setText(title);

        adapter = new SimpleAdapter(this, mList, R.layout.item_ring,
                new String[]{"ring", "select"}, new int[]{R.id.id_title, R.id.id_select});

        mListView.setAdapter(adapter);

        mListView.setSelection(currentSelectedIndex);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (from) {
                    case 11:
                        editor.putInt("phone", i);
                        editor.putString("phoneRing", songList.get(i));

                        ringUtils.setRingtone(RingtoneManager.TYPE_RINGTONE, i);

                        break;
                    case 12:
                        editor.putInt("sms", i);
                        editor.putString("smsRing", songList.get(i));
                        break;
                    case 13:
                        editor.putInt("calendar", i);
                        editor.putString("calendarRing", songList.get(i));
                        break;
                    case 14:
                        editor.putInt("notify", i);
                        editor.putString("notifyRing", songList.get(i));
                        break;
                }

                if (currentSelectedIndex != i) {
                    mList.get(i).put("select", R.drawable.selected);
                    mList.get(currentSelectedIndex).put("select", null);
                    currentSelectedIndex = i;
                    adapter.notifyDataSetChanged();
                }
                editor.commit();

                Intent intent = new Intent();
                intent.putExtra("ring", songList.get(currentSelectedIndex));
                setResult(RESULT_OK, intent);
            }
        });

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
