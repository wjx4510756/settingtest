package com.example.settingtest.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.settingtest.R;
import com.example.settingtest.adapter.MoreAdapter;
import com.example.settingtest.bean.SoundItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11070562 on 2017/10/8.
 */

public class MoreSettingActivity extends AppCompatActivity {


    private ListView mListView;
    private List<SoundItem> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_layout);

        initData();
        initView();
    }

    private void initView() {

        mListView = (ListView) findViewById(R.id.id_listView);
        MoreAdapter adapter = new MoreAdapter(this, mList);
        mListView.setAdapter(adapter);

    }

    private void initData() {

        mList = new ArrayList<>();
        String[] strings = getResources().getStringArray(R.array.more_title_array);

        for (int i = 0; i < strings.length; i++) {
            SoundItem soundItem = new SoundItem();
            soundItem.setTitle(strings[i]);
            soundItem.setShowDivider(true);
            soundItem.setType(MoreAdapter.TYPE_NORMAL);
            soundItem.setImage(R.mipmap.spinner_bt1);
            soundItem.setContent("");

            switch (i) {
                case 0:
                case 4:
                case 8:
                case 12:
                case 15:
                case 20:
                case 22:
                case 28:
                    soundItem.setType(MoreAdapter.TYPE_DIVIDER);
            }


            mList.add(soundItem);
        }


    }
}
