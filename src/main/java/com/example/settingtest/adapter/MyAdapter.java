package com.example.settingtest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.settingtest.MyApplication;
import com.example.settingtest.R;
import com.example.settingtest.bean.Item;

import java.util.List;

/**
 * Created by 11070562 on 2017/10/8.
 */

public class MyAdapter extends BaseAdapter {


    private Context mContext;
    private LayoutInflater mInflater;
    private List<Item> mList;

    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_SPECIAL = 2;
    public static final int TYPE_DIVIDER = 3;


    public MyAdapter(Context context, List<Item> list) {

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
            case TYPE_SPECIAL:
                type = TYPE_SPECIAL;
                break;
            case TYPE_DIVIDER:
                type = TYPE_DIVIDER;
                break;
        }
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
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
            case TYPE_SPECIAL:
                view = setViewHolderSpecial(i, view, viewGroup);
                break;
            case TYPE_DIVIDER:
                view = mInflater.inflate(R.layout.divider, viewGroup, false);
                break;
        }
        return view;
    }

    @NonNull
    private View setViewHolderNormal(int i, View view, ViewGroup viewGroup) {
        ViewHolderNormal holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item, viewGroup, false);
            holder = new ViewHolderNormal();
            holder.imageView = view.findViewById(R.id.id_imageView);
            holder.textView = view.findViewById(R.id.id_title);
            holder.more = view.findViewById(R.id.id_more);
            holder.divider = view.findViewById(R.id.id_divider);
            view.setTag(holder);
        } else
            holder = (ViewHolderNormal) view.getTag();

        Item item = mList.get(i);
        holder.imageView.setImageResource(item.getImage());
        holder.textView.setText(item.getTitle());
        holder.more.setImageResource(item.getMore());


        //防止复用view分割线显示不正确
        if (!item.isShowDivider())
            holder.divider.setVisibility(View.GONE);
        else
            holder.divider.setVisibility(View.VISIBLE);

//        switch (i){
//            case 5:
//            case 12:
//            case 19:
//            case 27:
//                holder.divider.setVisibility(View.GONE);
//        }

        return view;
    }

    @NonNull
    private View setViewHolderSpecial(int i, View view, ViewGroup viewGroup) {
        ViewHolderSpecial holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_one, viewGroup, false);
            holder = new ViewHolderSpecial();
            holder.imageView = view.findViewById(R.id.id_imageView);
            holder.textView = view.findViewById(R.id.id_title);
            holder.mSwitch = view.findViewById(R.id.id_switch);
            view.setTag(holder);
        } else
            holder = (ViewHolderSpecial) view.getTag();

        Item item = mList.get(i);
        holder.imageView.setImageResource(item.getImage());
        holder.textView.setText(item.getTitle());

        holder.mSwitch.setChecked(MyApplication.getPreferences().getBoolean("offline", false));
        return view;
    }


    class ViewHolderNormal {
        ImageView imageView;
        TextView textView;
        ImageView more;
        ImageView divider;
    }


    class ViewHolderSpecial {
        ImageView imageView;
        TextView textView;
        Switch mSwitch;
    }

}
