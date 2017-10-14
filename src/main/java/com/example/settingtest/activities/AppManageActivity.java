package com.example.settingtest.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.settingtest.R;
import com.example.settingtest.utils.AppUtils;

/**
 * Created by 11070562 on 2017/10/12.
 */

public class AppManageActivity extends AppCompatActivity {

    private ImageView icon, back;
    private TextView appName;
    private TextView appVersion;
    private TextView mTitle;

    private TextView appSize;

    private RelativeLayout mStorage;

    private Button unInstall;

    private Intent fromIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_detail_layout);

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

        icon = (ImageView) findViewById(R.id.id_imageView);
        appName = (TextView) findViewById(R.id.id_app);
        appVersion = (TextView) findViewById(R.id.id_size);

        appSize = (TextView) findViewById(R.id.id_used);
        mTitle = (TextView) findViewById(R.id.id_title);

        mTitle.setText(R.string.app_message);

        unInstall = (Button) findViewById(R.id.id_unInstall);
        unInstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String packageName = fromIntent.getStringExtra("app_package");
                boolean isSystem = fromIntent.getBooleanExtra("is_system", true);

                if (!isSystem) {
                    Uri packageURI = Uri.parse("package:" + packageName);
                    Intent intent = new Intent(Intent.ACTION_DELETE);
                    intent.setData(packageURI);
                    intent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
                    startActivityForResult(intent, 1);
                } else {
                    Toast.makeText(AppManageActivity.this, "can't unInstall", Toast.LENGTH_SHORT).show();
                }
            }
        });


        icon.setImageBitmap((Bitmap) fromIntent.getParcelableExtra("app_icon"));
        appName.setText(fromIntent.getStringExtra("app_name"));
        appVersion.setText(fromIntent.getStringExtra("app_version"));
        appSize.setText(AppUtils.getSize(fromIntent.getLongExtra("app_size", 0)));


        mStorage = (RelativeLayout) findViewById(R.id.id_storage);

        mStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppManageActivity.this, AppStorageActivity.class);
                intent.putExtra("app_data_size", fromIntent.getStringExtra("app_data_size"));
                intent.putExtra("app_catch_size", fromIntent.getStringExtra("app_catch_size"));
                intent.putExtra("app_size", fromIntent.getLongExtra("app_size", 0));
                intent.putExtra("app_code_size", fromIntent.getStringExtra("app_code_size"));
                intent.putExtra("app_name", fromIntent.getStringExtra("app_name"));
                intent.putExtra("app_icon", fromIntent.getParcelableExtra("app_icon"));
                startActivity(intent);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Toast.makeText(this, "卸载成功", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}
