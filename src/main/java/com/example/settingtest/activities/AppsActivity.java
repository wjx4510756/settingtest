package com.example.settingtest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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


        Intent intent = new Intent(this, AppListActivity.class);
        switch (view.getId()) {
            case R.id.id_installed:

                intent.putExtra("title", getResources().getString(R.string.installed));
                intent.putExtra("from", 1);
                startActivity(intent);

                break;

            case R.id.id_all:

                intent.putExtra("title", getResources().getString(R.string.all));
                intent.putExtra("from", 2);
                startActivity(intent);

                break;

            case R.id.id_default:


//                intent.putExtra("title", getResources().getString(R.string.default_setting));
//                startActivity(intent);
                Toast.makeText(this, getResources().getString(R.string.default_setting), Toast.LENGTH_SHORT).show();
                break;

            case R.id.id_system:

//                intent.putExtra("title", getResources().getString(R.string.system_setting));
//                startActivity(intent);
                Toast.makeText(this, getResources().getString(R.string.system_setting), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
