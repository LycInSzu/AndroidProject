package com.example.lyc.bootymusic.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lyc.bootymusic.Activity.MusicDetailActivity;
import com.example.lyc.bootymusic.Adapter.MyAdapter;
import com.example.lyc.bootymusic.R;
import com.example.lyc.bootymusic.bean.MusicBean;
import com.example.lyc.bootymusic.bean.RxBusBean.MusicPlayingProgressBean;
import com.example.lyc.bootymusic.bean.RxBusBean.PlayLocalMusicBean;
import com.example.lyc.bootymusic.service.PlayMusicService;
import com.example.lyc.bootymusic.utils.MediaUtil;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LocalMusicFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.music_list)
    ListView musicList;
    @BindView(R.id.music_image)
    ImageView musicImage;
    @BindView(R.id.music_name)
    TextView musicName;
    @BindView(R.id.artist_name)
    TextView artistName;
    @BindView(R.id.play)
    ImageView play;
    @BindView(R.id.next)
    ImageView next;
    @BindView(R.id.bottom_layout)
    LinearLayout bottomLayout;

    private String mParam1;
    private String mParam2;

    private ArrayList<MusicBean> musics = new ArrayList<>();
    private MusicAdapter adapter;
    private boolean isPlaying = false;
    private int position=-1;
    private int progress=0;

//    private OnFragmentInteractionListener mListener;

    public LocalMusicFragment() {

    }


    public static LocalMusicFragment newInstance(String param1, String param2) {
        LocalMusicFragment fragment = new LocalMusicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        RxBus.get().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.app_bar_main, container, false);
        ButterKnife.bind(this, view);
        adapter = new MusicAdapter(getActivity());
        musicList.setAdapter(adapter);
        musics = MediaUtil.getMusicBeans(getActivity());
        adapter.setMyList(musics);

        musicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log("item clicked is " + i);
                position=i;
                MusicBean bean = musics.get(i);
                Picasso.with(getActivity()).load("imageUrl").fit().placeholder(R.mipmap.music_image_default).into(musicImage);
                musicName.setText(bean.getTitle());
                artistName.setText(bean.getArtist().getName());
                if (bottomLayout.getVisibility() != View.VISIBLE) {
                    bottomLayout.setVisibility(View.VISIBLE);
                }
                isPlaying = true;
                progress=0;
                play.setSelected(true);
                Intent intent = new Intent();
                intent.putExtra("musicList",musics);
                intent.putExtra("position", i);
                intent.setClass(getActivity(), PlayMusicService.class);
                getActivity().startService(intent);       //启动服务

            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    @OnClick({R.id.play, R.id.next, R.id.bottom_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play:
                if (isPlaying) {
                    isPlaying = false;
                    play.setSelected(false);
                    PlayLocalMusicBean bean = new PlayLocalMusicBean(PlayLocalMusicBean.PAUSE);
                    RxBus.get().post(bean);
                } else {
                    isPlaying = true;
                    play.setSelected(true);
                    PlayLocalMusicBean bean = new PlayLocalMusicBean(PlayLocalMusicBean.PLAY);
                    RxBus.get().post(bean);
                }
                break;
            case R.id.next:
                position=(++position)%musics.size();
                MusicBean bean = musics.get(position);
                Picasso.with(getActivity()).load("imageUrl").fit().placeholder(R.mipmap.music_image_default).into(musicImage);
                musicName.setText(bean.getTitle());
                artistName.setText(bean.getArtist().getName());
                isPlaying = true;
                play.setSelected(true);
                PlayLocalMusicBean bean1 = new PlayLocalMusicBean(PlayLocalMusicBean.NEXT);
                RxBus.get().post(bean1);
                break;
            case R.id.bottom_layout:
                Intent detailIntent=new Intent(getActivity(), MusicDetailActivity.class);
                detailIntent.putExtra("progress",progress);
                detailIntent.putExtra("position",position);
//                detailIntent.putExtra("totalTime",Integer.valueOf(musics.get(position).getDuration_millinseconds()+""));//音乐的时长，秒数
                detailIntent.putExtra("musicbean",musics);
                detailIntent.putExtra("isPlaying",isPlaying);
                startActivityForResult(detailIntent,1);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode== Activity.RESULT_OK){
                    isPlaying=data.getBooleanExtra("isPlaying",false);
                    if (isPlaying){
                        play.setSelected(true);
                    }else {
                        play.setSelected(false);
                    }
                    if (position==data.getIntExtra("position",0)){
                        return;
                    }
                    position=data.getIntExtra("position",0);
                    MusicBean bean = musics.get(position);
                    Picasso.with(getActivity()).load(bean.getImage()).fit().placeholder(R.mipmap.music_image_default).into(musicImage);
                    musicName.setText(bean.getTitle());
                    artistName.setText(bean.getArtist().getName());
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    class MusicAdapter extends MyAdapter<MusicBean> {

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
            Picasso.with(mContext).load("imageUrl").fit().placeholder(R.mipmap.music_image_default).into(holder.musicImage);
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Subscribe
    public void onRxEventBus(PlayLocalMusicBean obj){
        if (obj.getType()== PlayLocalMusicBean.COMPLETED){
            isPlaying=false;
            play.setSelected(false);
        }
    }
    @Subscribe
    public void onRxBusEvent(MusicPlayingProgressBean bean){
        if (!bean.isForService())
         progress=bean.getProgress();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
}
