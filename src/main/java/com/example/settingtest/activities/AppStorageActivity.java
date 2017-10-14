package com.example.settingtest.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.settingtest.R;
import com.example.settingtest.utils.AppUtils;

/**
 * Created by 11070562 on 2017/10/13.
 */

public class AppStorageActivity extends AppCompatActivity {

    private Intent fromIntent;

    private TextView title;


    private TextView data, catchSize, appSize, codeSize, appName;
    private ImageView icon, back;

    private Button clearData, clearCatch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_storage);

        fromIntent = getIntent();

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

        title = (TextView) findViewById(R.id.id_title);
        title.setText(R.string.storage);

        appName = (TextView) findViewById(R.id.id_app_name);
        icon = (ImageView) findViewById(R.id.id_icon);

        appName.setText(fromIntent.getStringExtra("app_name"));
        icon.setImageBitmap((Bitmap) fromIntent.getParcelableExtra("app_icon"));

        data = (TextView) findViewById(R.id.id_data_size);
        catchSize = (TextView) findViewById(R.id.id_catch_size);
        appSize = (TextView) findViewById(R.id.id_app_size);
        codeSize = (TextView) findViewById(R.id.id_code_size);

        data.setText(fromIntent.getStringExtra("app_data_size"));
        catchSize.setText(fromIntent.getStringExtra("app_catch_size"));
        appSize.setText(AppUtils.getSize(fromIntent.getLongExtra("app_size", 0)));
        codeSize.setText(fromIntent.getStringExtra("app_code_size"));


        clearData = (Button) findViewById(R.id.id_clear_data);
        clearCatch = (Button) findViewById(R.id.id_clear_catch);

    }
}
