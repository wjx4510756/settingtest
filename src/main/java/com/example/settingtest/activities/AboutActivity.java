package com.example.settingtest.activities;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.settingtest.AboutData;
import com.example.settingtest.R;
import com.example.settingtest.adapter.AboutAdapter;
import com.example.settingtest.bean.AboutItem;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11070562 on 2017/10/8.
 */

public class AboutActivity extends AppCompatActivity {


    private ImageView back;
    private TextView mTitle;
    private ListView listView;

    private List<AboutItem> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);

        initData();

        initView();

    }

    private void initData() {

        String jsonData = null;

        try {
            AssetManager manager = this.getAssets();
            InputStream is = manager.open("aboutPhone.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonData = new String(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }


        Gson gson = new Gson();

        AboutData aboutData = gson.fromJson(jsonData, AboutData.class);

        mList = new ArrayList<>();
        String[] strings = getResources().getStringArray(R.array.about_phone_array);

        String[] content = aboutData.toStrings();


        String decompileTime = aboutData.getDecompileTime();

        for (int i = 0; i < strings.length; i++) {
            AboutItem item = new AboutItem();
            item.setContent(content[i]);
            item.setTitle(strings[i]);
            item.setShowDivider(true);
            item.setType(AboutAdapter.TYPE_NORMAL);
            switch (i) {
                case 14:
                    item.setContent(decompileTime);
                case 21:
                case 23:
                    item.setShowDivider(false);
            }

            switch (i) {
                case 15:
                case 22:
                case 24:
                    item.setType(AboutAdapter.TYPE_DIVIDER);
            }

            mList.add(item);
        }


    }

    private void initView() {
        mTitle = (TextView) findViewById(R.id.id_title);
        back = (ImageView) findViewById(R.id.id_back);

        mTitle.setText(R.string.about_phone);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        listView = (ListView) findViewById(R.id.id_listView);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View header = inflater.inflate(R.layout.about_header, listView, false);
        listView.addHeaderView(header);

        AboutAdapter adapter = new AboutAdapter(this, mList);
        listView.setAdapter(adapter);

    }
}
