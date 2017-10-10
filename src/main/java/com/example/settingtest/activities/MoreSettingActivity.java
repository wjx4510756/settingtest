package com.example.settingtest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

    private TextView mTitle;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_layout);

        initData();
        initView();
    }

    private void initView() {


        mTitle = (TextView) findViewById(R.id.id_title);
        back = (ImageView) findViewById(R.id.id_back);
        mTitle.setText(getResources().getString(R.string.more_settings));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        mListView = (ListView) findViewById(R.id.id_listView);
        MoreAdapter adapter = new MoreAdapter(this, mList);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                switch (i) {
                    case 1:
                        Intent intent = new Intent(MoreSettingActivity.this, AboutActivity.class);
                        startActivity(intent);

                        break;
                    case 2:
                        Intent intent1 = new Intent(MoreSettingActivity.this, AppsActivity.class);
                        startActivity(intent1);
                        break;
                    case 3:


                        break;
                    default:
                        Toast.makeText(MoreSettingActivity.this, mList.get(i).getTitle(), Toast.LENGTH_LONG).show();
                }
            }
        });

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
