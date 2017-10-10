package com.example.settingtest.adapter;

import android.content.Context;
import android.media.AudioManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.example.settingtest.R;
import com.example.settingtest.bean.SoundItem;

import java.util.List;

import static android.media.AudioManager.STREAM_ALARM;
import static android.media.AudioManager.STREAM_MUSIC;
import static android.media.AudioManager.STREAM_RING;

/**
 * Created by 11070562 on 2017/10/8.
 */

public class SoundAdapter extends BaseAdapter {


    private AudioManager manager;

    private Context mContext;
    private LayoutInflater mInflate;
    private List<SoundItem> mList;

    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_SPECIAL = 2;
    public static final int TYPE_DIVIDER = 3;
    public static final int TYPE_MORE = 4;


    public SoundAdapter(Context context, List<SoundItem> list) {

        mContext = context;
        mInflate = LayoutInflater.from(mContext);
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
            case TYPE_MORE:
                type = TYPE_MORE;
                break;
        }
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    //不复用了
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        switch (getItemViewType(i)) {
            case TYPE_NORMAL:

                view = setViewHolderNormal(i, view, viewGroup);
                break;
            case TYPE_SPECIAL:
                view = mInflate.inflate(R.layout.item_sound2, viewGroup, false);
                ImageView imageView = view.findViewById(R.id.id_imageView);
                imageView.setImageResource(mList.get(i).getImage());

                SeekBar seekBar = view.findViewById(R.id.id_seekBar);
                ImageView divider1 = view.findViewById(R.id.id_divider);

                manager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
                int max = 0;
                int current = 0;

                switch (i) {
                    case 4:
                        //来电铃声音量
                        max = manager.getStreamMaxVolume(STREAM_RING);
                        current = manager.getStreamVolume(STREAM_RING);
                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                manager.setStreamVolume(STREAM_RING,i,0);
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }
                        });
                        break;
                    case 5:
                        //媒体音量
                        max = manager.getStreamMaxVolume(STREAM_MUSIC);
                        current = manager.getStreamVolume(STREAM_MUSIC);
                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                manager.setStreamVolume(STREAM_MUSIC,i,0);
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }
                        });
                        break;
                    case 6:
                        divider1.setVisibility(View.GONE);
                        //闹钟音量
                        max = manager.getStreamMaxVolume(STREAM_ALARM);
                        current = manager.getStreamVolume(STREAM_ALARM);
                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                manager.setStreamVolume(STREAM_ALARM,i,0);
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                if (seekBar.getProgress() == 0){

                                }
                            }
                        });
                        break;
                }
                seekBar.setMax(max);
                seekBar.setProgress(current);

                break;
            case TYPE_DIVIDER:
                view = mInflate.inflate(R.layout.divider, viewGroup, false);
                if (i == 9) {
                    TextView textView = view.findViewById(R.id.id_divider);
                    textView.setText(mContext.getResources().getString(R.string.tip));
                }
                break;
            case TYPE_MORE:
                view = mInflate.inflate(R.layout.item_sound3, viewGroup, false);
                TextView textView = view.findViewById(R.id.id_title);
                TextView textView1 = view.findViewById(R.id.id_content);
                textView.setText(mList.get(i).getTitle());
                textView1.setText(mList.get(i).getContent());

                ImageView divider2 = view.findViewById(R.id.id_divider);
                switch (i) {
                    case 8:
                        divider2.setVisibility(View.GONE);
                }


                break;
        }

        return view;
    }


    //包含Switch的Item
    private View setViewHolderNormal(int i, View view, ViewGroup viewGroup) {

        ViewHolderNormal holderNormal;
        if (view == null) {
            view = mInflate.inflate(R.layout.item_sound1, viewGroup, false);
            holderNormal = new ViewHolderNormal();
            holderNormal.textView = view.findViewById(R.id.id_title);
            holderNormal.mSwitch = view.findViewById(R.id.id_switch);
            holderNormal.divider = view.findViewById(R.id.id_divider);
            view.setTag(holderNormal);
        } else
            holderNormal = (ViewHolderNormal) view.getTag();

        final SoundItem item = mList.get(i);

        holderNormal.textView.setText(item.getTitle());


        holderNormal.mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                item.setSelect(b);
            }
        });

        //防止复选框复用问题
        if (item.isSelect()) {
            holderNormal.mSwitch.setChecked(true);
        } else
            holderNormal.mSwitch.setChecked(false);

        //防止复用View中分割线显示混乱问题
        if (item.isShowDivider())
            holderNormal.divider.setVisibility(View.VISIBLE);
        else
            holderNormal.divider.setVisibility(View.GONE);

        return view;
    }

    class ViewHolderNormal {
        TextView textView;
        Switch mSwitch;
        ImageView divider;
    }


}
