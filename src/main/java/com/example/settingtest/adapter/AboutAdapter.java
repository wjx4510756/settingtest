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
import com.example.settingtest.bean.AboutItem;

import java.util.List;

/**
 * Created by 11070562 on 2017/10/11.
 */
public class AboutAdapter extends BaseAdapter {


    private Context mContext;
    private LayoutInflater mInflater;
    private List<AboutItem> mList;

    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_DIVIDER = 2;


    public AboutAdapter(Context context, List<AboutItem> list) {

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = list;
    }

    @Override
    public int getItemViewType(int position) {

        int type = 0;

        switch (mList.get(position).getType()) {
            case TYPE_NORMAL:
                type = TYPE_NORMAL;
                break;
            case TYPE_DIVIDER:
                type = TYPE_DIVIDER;
                break;
        }
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
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

        switch (getItemViewType(i)) {
            case TYPE_NORMAL:
                view = setViewHolderNormal(i, view, viewGroup);
                break;
            case TYPE_DIVIDER:
                view = mInflater.inflate(R.layout.divider, viewGroup, false);
                break;
        }
        return view;
    }

    @NonNull
    private View setViewHolderNormal(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_about, viewGroup, false);
            holder = new ViewHolder();
            holder.content = view.findViewById(R.id.id_content);
            holder.textView = view.findViewById(R.id.id_title);
            holder.divider = view.findViewById(R.id.id_divider);
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();

        AboutItem item = mList.get(i);
        holder.content.setText(item.getContent());
        holder.textView.setText(item.getTitle());

        //防止复用view分割线显示不正确
        if (!item.isShowDivider())
            holder.divider.setVisibility(View.GONE);
        else
            holder.divider.setVisibility(View.VISIBLE);


        return view;
    }


    class ViewHolder {
        TextView textView;
        TextView content;
        ImageView divider;
    }

}