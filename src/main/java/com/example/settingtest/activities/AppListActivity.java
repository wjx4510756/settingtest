package com.example.settingtest.activities;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.settingtest.R;
import com.example.settingtest.adapter.AppAdapter;
import com.example.settingtest.bean.AppBean;
import com.example.settingtest.bean.AppItem;
import com.example.settingtest.bean.AppSizeBean;
import com.example.settingtest.utils.AppUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 11070562 on 2017/10/8.
 */

public class AppListActivity extends AppCompatActivity {

    private TextView mTitle;
    private ImageView back;
    private ProgressBar progressBar;

    private ListView mListView;
    private List<AppItem> mList;

    private List<AppBean> mAppList;
    private List<AppBean> allApps;
    private AppAdapter appAdapter;

    private Intent fromIntent;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                AppSizeBean appSize = (AppSizeBean) msg.obj;
                int i = msg.arg1;

                mAppList.get(i).setAppSize(appSize.getAppSize());
                mAppList.get(i).setCodeSize(appSize.getCodeSize());
                mAppList.get(i).setDataSize(appSize.getDataSize());
                mAppList.get(i).setCatchSize(appSize.getCatchSize());

                mList.get(i).setContent(String.valueOf(appSize.getAppSize()));

                appAdapter.notifyDataSetChanged();
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_manager_layout);

        fromIntent = getIntent();

        initData();
        initView();

        new GetInstalledApps().execute();
    }

    private void initView() {

        mListView = (ListView) findViewById(R.id.id_listView);

        progressBar = (ProgressBar) findViewById(R.id.progress);

        mTitle = (TextView) findViewById(R.id.id_title);
        back = (ImageView) findViewById(R.id.id_back);

        mTitle.setText(getIntent().getStringExtra("title"));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        appAdapter = new AppAdapter(this, mList);
        mListView.setAdapter(appAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                AppBean appBean = mAppList.get(i);

                Intent intent = new Intent(AppListActivity.this, AppManageActivity.class);
                intent.putExtra("app_name", appBean.getAppName());
                intent.putExtra("app_version", appBean.getAppVersion());
                intent.putExtra("app_icon", ((BitmapDrawable) appBean.getIcon()).getBitmap());
                intent.putExtra("app_catch_size", appBean.getCatchSize());
                intent.putExtra("app_size", appBean.getAppSize());
                intent.putExtra("app_data_size", appBean.getDataSize());
                intent.putExtra("app_code_size", appBean.getCodeSize());

                startActivity(intent);
            }
        });

    }

    private void initData() {

        mList = new ArrayList<>();
        mAppList = new ArrayList<>();
        allApps = new ArrayList<>();

    }


    class GetInstalledApps extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            publishProgress();

            PackageManager packageManager = getPackageManager();
            List<PackageInfo> list = packageManager.getInstalledPackages(PackageManager.GET_META_DATA);


            // Installed & System Apps
            for (PackageInfo packageInfo : list) {
                if (!(packageManager.getApplicationLabel(packageInfo.applicationInfo).equals("") || packageInfo.packageName.equals(""))) {
                    if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {

                        // Non System Apps
                        AppBean appBean = new AppBean();
                        appBean.setAppPackage(packageInfo.packageName);
                        appBean.setAppVersion("" + packageInfo.versionName);
                        appBean.setAppName(packageManager.getApplicationLabel(packageInfo.applicationInfo).toString());
                        appBean.setIcon(packageManager.getApplicationIcon(packageInfo.applicationInfo));
                        appBean.setSystem(false);


//                        Log.d("TAG1", appBean.getAppSize()+"");

                        mAppList.add(appBean);
                        allApps.add(appBean);

                    } else {
                        // System Apps
                        AppBean appBean = new AppBean();
                        appBean.setAppPackage(packageInfo.packageName);
                        appBean.setAppVersion("" + packageInfo.versionName);
                        appBean.setAppName(packageManager.getApplicationLabel(packageInfo.applicationInfo).toString());
                        appBean.setIcon(packageManager.getApplicationIcon(packageInfo.applicationInfo));
                        appBean.setSystem(true);
                        allApps.add(appBean);
                    }
                }


            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            int from = fromIntent.getIntExtra("from", -1);

            if (from == 1) {

                for (AppBean bean : mAppList) {
                    AppItem item = new AppItem();
                    item.setTitle(bean.getAppName());
                    item.setIcon(bean.getIcon());
                    item.setContent("");
                    mList.add(item);
                }
            } else {
                for (AppBean bean : allApps) {
                    AppItem item = new AppItem();
                    item.setTitle(bean.getAppName());
                    item.setIcon(bean.getIcon());
                    item.setContent("");
                    mList.add(item);
                }
            }


            Collections.sort(mList, new Comparator<AppItem>() {
                @Override
                public int compare(AppItem appItem, AppItem t1) {
                    return appItem.getTitle().compareTo(t1.getTitle());
                }
            });

            Collections.sort(mAppList, new Comparator<AppBean>() {
                @Override
                public int compare(AppBean appBean, AppBean t1) {
                    return appBean.getAppName().compareTo(t1.getAppName());
                }
            });

            appAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);


            List<String> appPackages = new ArrayList<>();

            for (int i = 0; i < mAppList.size(); i++) {
                AppBean bean = mAppList.get(i);
                String appPackage = bean.getAppPackage();
                appPackages.add(appPackage);
            }
            AppUtils.getPkgSize(AppListActivity.this, handler, appPackages);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }
    }
}
