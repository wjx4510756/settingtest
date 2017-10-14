package com.example.settingtest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.settingtest.R;
import com.example.settingtest.bean.AppBean;
import com.example.settingtest.utils.AppUtils;

import java.util.List;

/**
 * Created by 11070562 on 2017/10/11.
 */

public class AppAdapter extends BaseAdapter {


    private Context mContext;
    private LayoutInflater mInflater;
    private List<AppBean> mList;

    public AppAdapter(Context context, List<AppBean> list) {

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = list;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        view = setViewHolderNormal(i, view, viewGroup);

        return view;
    }

    @NonNull
    private View setViewHolderNormal(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_app, viewGroup, false);
            holder = new ViewHolder();
            holder.content = view.findViewById(R.id.id_size);
            holder.textView = view.findViewById(R.id.id_app);
            holder.icon = view.findViewById(R.id.id_imageView);
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();

        AppBean item = mList.get(i);
        holder.icon.setImageDrawable(item.getIcon());
        holder.content.setText(AppUtils.getSize(item.getAppSize()));
        holder.textView.setText(item.getAppName());


//        //防止复用view分割线显示不正确
//        if (!item.isShowDivider())
//            holder.divider.setVisibility(View.GONE);
//        else
//            holder.divider.setVisibility(View.VISIBLE);


        return view;
    }


    class ViewHolder {
        ImageView icon;
        TextView textView;
        TextView content;
    }




}
