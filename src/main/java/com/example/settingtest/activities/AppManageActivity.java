package com.example.settingtest.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.settingtest.R;

/**
 * Created by 11070562 on 2017/10/12.
 */

public class AppManageActivity extends AppCompatActivity {

    private ImageView icon;
    private TextView appName;
    private TextView appVersion;

    private TextView appSize;

    private RelativeLayout mStorage;
    private RelativeLayout mNotify;
    private RelativeLayout mOpen;


    private Intent fromIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_detail_layout);

        fromIntent = getIntent();

        initView();

    }

    private void initView() {

        icon = (ImageView) findViewById(R.id.id_imageView);
        appName = (TextView) findViewById(R.id.id_app);
        appVersion = (TextView) findViewById(R.id.id_size);

        appSize = (TextView) findViewById(R.id.id_used);


        icon.setImageBitmap((Bitmap) fromIntent.getParcelableExtra("app_icon"));
        appName.setText(fromIntent.getStringExtra("app_name"));
        appVersion.setText(fromIntent.getStringExtra("app_version"));
        appSize.setText(fromIntent.getStringExtra("app_size"));

        mStorage = (RelativeLayout) findViewById(R.id.id_storage);

        mStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppManageActivity.this, AppStorageActivity.class);
                intent.putExtra("app_data_size", fromIntent.getStringExtra("app_data_size"));
                intent.putExtra("app_catch_size", fromIntent.getStringExtra("app_catch_size"));
                intent.putExtra("app_size", fromIntent.getStringExtra("app_size"));
                intent.putExtra("app_code_size", fromIntent.getStringExtra("app_code_size"));
                intent.putExtra("app_name",fromIntent.getStringExtra("app_name"));
                intent.putExtra("app_icon",fromIntent.getParcelableExtra("app_icon"));
                startActivity(intent);

            }
        });


    }
}
