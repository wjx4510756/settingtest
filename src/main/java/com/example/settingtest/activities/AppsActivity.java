package com.example.settingtest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.settingtest.R;

/**
 * Created by 11070562 on 2017/10/8.
 */

public class AppsActivity extends AppCompatActivity {


    private ImageView back;
    private TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_layout);


        initView();
    }

    private void initView() {

        back = (ImageView) findViewById(R.id.id_back);
        mTitle = (TextView) findViewById(R.id.id_title);

        mTitle.setText(getResources().getString(R.string.apps));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    public void onClick(View view) {


        Intent intent = new Intent(this, AppManageActivity.class);
        switch (view.getId()) {
            case R.id.id_installed:

                intent.putExtra("title", getResources().getString(R.string.installed));
                startActivity(intent);

                break;

            case R.id.id_all:

                intent.putExtra("title", getResources().getString(R.string.all));
                startActivity(intent);

                break;

            case R.id.id_default:


                intent.putExtra("title", getResources().getString(R.string.default_setting));
                startActivity(intent);

                break;

            case R.id.id_system:

                intent.putExtra("title", getResources().getString(R.string.system_setting));
                startActivity(intent);
                break;
        }
    }
}
