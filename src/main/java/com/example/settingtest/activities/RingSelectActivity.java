package com.example.settingtest.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

    private ListView mListView;
    private TextView mTitle;
    private ImageView back;

    private String title;

    private SimpleAdapter adapter;
    private List<String> songList;
    private List<Map<String, Object>> mList;
    private RingUtils ringUtils;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private int currentSelectedIndex;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ring_select_layout);

        initData();
        initView();
    }

    private void initData() {

        preferences = MyApplication.getPreferences();

        songList = new ArrayList<>();
        mList = new ArrayList<>();

        ringUtils = new RingUtils();

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        int type = intent.getIntExtra("type", -1);
        int from = intent.getIntExtra("from", -1);

        switch (from) {
            case 11:
                currentSelectedIndex = preferences.getInt("phone", 0);
                break;
            case 12:
            case 13:
            case 14:
        }

        songList = ringUtils.getRingList(type);

        for (int i = 0; i < songList.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("ring", songList.get(i));
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

    }
}
