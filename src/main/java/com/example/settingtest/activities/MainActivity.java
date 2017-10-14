package com.example.settingtest.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.settingtest.R;
import com.example.settingtest.adapter.MyAdapter;
import com.example.settingtest.bean.Item;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private List<Item> mList;

    private TextView mTitle;

    private String[] strings;
    private int[] images = {R.mipmap.ic_launcher,
            R.drawable.ic_settings_airplane_item,
            R.drawable.ic_settings_wifi_bbk_item,
            R.drawable.ic_settings_network,
            R.drawable.ic_settings_tether_item,
            R.drawable.ic_settings_bluetooth_item,
            R.mipmap.ic_launcher,
            R.drawable.com_android_systemui,
            R.drawable.ic_settings_zenmode_item,
            R.drawable.ic_settings_game,
            R.drawable.ic_settings_sound_item,
            R.drawable.ic_settings_light_item,
            R.drawable.ic_settings_personality,
            R.mipmap.ic_launcher,
            R.drawable.ic_system_update,
            R.drawable.ic_settings_finger,
            R.drawable.com_iqoo_powersaving,
            R.drawable.ic_settings_location,
            R.drawable.ic_settings_ram_item,
            R.drawable.ic_settings_general_item,
            R.mipmap.ic_launcher,
            R.drawable.ic_settings_account_item,
            R.drawable.ic_settings_call,
            R.drawable.com_android_contacts,
            R.drawable.com_android_mms,
            R.drawable.com_vivo_gallery,
            R.drawable.com_bbk_calendar,
            R.drawable.com_bbk_voiceassistant,
            R.mipmap.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_SETTINGS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_SETTINGS}, 0);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {


                }
                return;
            }
        }
    }

    private void initData() {
        mList = new ArrayList<>();
        Resources res = getResources();
        strings = res.getStringArray(R.array.item_array);
        for (int i = 0; i < images.length; i++) {
            Item item = new Item();
            item.setImage(images[i]);
            item.setTitle(strings[i]);
            item.setMore(R.mipmap.spinner_bt1);
            item.setType(MyAdapter.TYPE_NORMAL);
            item.setShowDivider(true);
            switch (i) {
                case 0:
                case 6:
                case 13:
                case 20:
                case 28:
                    item.setType(MyAdapter.TYPE_DIVIDER);
                    break;
                case 1:
                    item.setType(MyAdapter.TYPE_SPECIAL);
                    break;
                case 5:
                case 12:
                case 19:
                case 27:
                    item.setShowDivider(false);
            }
            mList.add(item);
        }
    }

    private void initView() {

        mTitle = (TextView) findViewById(R.id.textView);

        mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListView.setSelection(0);
            }
        });

        mListView = (ListView) findViewById(R.id.id_listView);

        MyAdapter adapter = new MyAdapter(this, mList);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    //voice
                    case 10:
                        Intent intent = new Intent(MainActivity.this, SoundActivity.class);
                        startActivity(intent);
                        break;
                    //more settings
                    case 19:
                        intent = new Intent(MainActivity.this, MoreSettingActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        Toast.makeText(MainActivity.this, mList.get(i).getTitle() + i, Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
    }
}
