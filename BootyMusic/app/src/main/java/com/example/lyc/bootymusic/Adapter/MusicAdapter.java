package com.example.lyc.bootymusic.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lyc.bootymusic.R;
import com.example.lyc.bootymusic.bean.MusicBean;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：abc on 2017/1/13 13:05
 * 邮箱：liyuchong@kocla.com
 */

public class MusicAdapter extends MyAdapter<MusicBean> {

    public MusicAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.music_item_layout, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        MusicBean bean = myList.get(i);
        holder.positon.setText((i + 1) + "");
        Picasso.with(mContext).load(bean.getImage()).fit().placeholder(R.mipmap.music_image_default).into(holder.musicImage);
        holder.musicName.setText(bean.getTitle());
        holder.musicName.setSelected(true);
        holder.artistName.setText(bean.getArtist().getName());
        holder.duration.setText(bean.getDuration() + "");
        return view;
    }

    class ViewHolder {
        @BindView(R.id.positon)
        TextView positon;
        @BindView(R.id.music_image)
        ImageView musicImage;
        @BindView(R.id.music_name)
        TextView musicName;
        @BindView(R.id.artist_name)
        TextView artistName;
        @BindView(R.id.duration)
        TextView duration;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
