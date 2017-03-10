package com.example.lyc.bootymusic.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lyc.bootymusic.Adapter.MusicAdapter;
import com.example.lyc.bootymusic.R;
import com.example.lyc.bootymusic.bean.ArtistBean;
import com.example.lyc.bootymusic.bean.MusicBean;
import com.example.lyc.bootymusic.bean.MusicBeanOfWangYiAlbum;
import com.example.lyc.bootymusic.bean.RxBusBean.MusicPlayingProgressBean;
import com.example.lyc.bootymusic.bean.RxBusBean.PlayLocalMusicBean;
import com.example.lyc.bootymusic.service.PlayMusicService;
import com.example.lyc.bootymusic.utils.LogUtils;
import com.example.lyc.bootymusic.utils.MediaUtil;
import com.example.lyc.bootymusic.utils.internet.RetrofitHttpUtil;
import com.google.gson.Gson;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicsOfAlbumActivity extends BaseActivity {


    @BindView(R.id.music_list)
    ListView mMusicList;
    @BindView(R.id.music_image)
    ImageView mMusicImage;
    @BindView(R.id.music_name)
    TextView mMusicName;
    @BindView(R.id.artist_name)
    TextView mArtistName;
    @BindView(R.id.play)
    ImageView mPlay;
    @BindView(R.id.next)
    ImageView mNext;
    @BindView(R.id.bottom_layout)
    LinearLayout mBottomLayout;
    private int albumID;
    private ArrayList<MusicBean> musicList = new ArrayList<>();//单曲的list
    private MusicAdapter mMusicAdapter;
    private boolean isPlaying = false;
    private int position = -1;
    private int progress = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this);

    }
    @Subscribe
    public void onRxEventBus(PlayLocalMusicBean obj) {
        if (obj.getType() == PlayLocalMusicBean.COMPLETED) {
            isPlaying = false;
            mPlay.setSelected(false);
        }
    }
    @Subscribe
    public void onRxBusEvent(MusicPlayingProgressBean bean) {
        if (!bean.isForService())
            progress = bean.getProgress();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_musics_of_album);
        ButterKnife.bind(this);
        setTitle(getIntent().getStringExtra("albumName"));
        albumID = getIntent().getIntExtra("albumID", -1);
        mMusicAdapter=new MusicAdapter(this);
        mMusicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                MusicBean bean = musicList.get(i);
                Picasso.with(MusicsOfAlbumActivity.this).load(bean.getImage()).fit().placeholder(R.mipmap.music_image_default).into(mMusicImage);
                mMusicName.setText(bean.getTitle());
                mArtistName.setText(bean.getArtist().getName());
                if (mBottomLayout.getVisibility() != View.VISIBLE) {
                    mBottomLayout.setVisibility(View.VISIBLE);
                }
                isPlaying = true;
                mPlay.setSelected(true);
                Intent intent = new Intent();
                intent.putExtra("musicList", musicList);
                intent.putExtra("position", i);
                intent.setClass(MusicsOfAlbumActivity.this, PlayMusicService.class);
                startService(intent);       //启动服务
            }
        });
    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {
        searchMusic(albumID);
    }

    @Override
    public void widgetClick(View v) {

    }

    private void searchMusic(int albumID) {
        LogUtils.e("-----------------------------------searchMusic---------------------------------------");
//        RequestParams params = new RequestParams();
//        params.put("album_id", albumID);

            /*http://music.163.com/api/search/get/*/
        RetrofitHttpUtil.getCommonRetrofitInterface().getMusicOfAlbum("api/album/" + albumID).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String res = response.body();
                try {
                    JSONObject object = new JSONObject(res);
                    int code = object.getInt("code");
                    LogUtils.e(code + "");
                    if (code == 200) {
                        musicList.clear();
                        JSONObject album = object.getJSONObject("album");
                        JSONArray songs=album.getJSONArray("songs");
                        for (int i = 0; i < songs.length(); i++) {
                            MusicBeanOfWangYiAlbum musicBean=new Gson().fromJson(songs.getString(i),MusicBeanOfWangYiAlbum.class);
                            MusicBean bean=new MusicBean();
                            bean.setId(musicBean.getId()+"");
                            ArtistBean artist=new ArtistBean();
                            artist.setName(musicBean.getArtists().get(0).getName());
                            bean.setArtist(artist);
                            bean.setImage(musicBean.getAlbum().getPicUrl());
                            bean.setDuration(MediaUtil.formatTime(musicBean.getDuration()));
                            bean.setDuration_millinseconds(musicBean.getDuration());
                            bean.setPath(musicBean.getMp3Url());
                            bean.setTitle(musicBean.getName());
                            musicList.add(bean);
                        }
                        mMusicList.setAdapter(mMusicAdapter);
                        mMusicAdapter.setMyList(musicList);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                LogUtils.e("--------------------------onFailure------------------------------------------------");
            }
        });


    }

    @OnClick({R.id.play, R.id.next, R.id.bottom_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play:
                if (isPlaying) {
                    isPlaying = false;
                    mPlay.setSelected(false);
                    PlayLocalMusicBean bean = new PlayLocalMusicBean(PlayLocalMusicBean.PAUSE);
                    RxBus.get().post(bean);
                } else {
                    isPlaying = true;
                    mPlay.setSelected(true);
                    PlayLocalMusicBean bean = new PlayLocalMusicBean(PlayLocalMusicBean.PLAY);
                    RxBus.get().post(bean);
                }
                break;
            case R.id.next:
                position = (++position) % musicList.size();
                MusicBean bean = musicList.get(position);
                Picasso.with(MusicsOfAlbumActivity.this).load("imageUrl").fit().placeholder(R.mipmap.music_image_default).into(mMusicImage);
                mMusicName.setText(bean.getTitle());
                mArtistName.setText(bean.getArtist().getName());
                isPlaying = true;
                mPlay.setSelected(true);
                PlayLocalMusicBean bean1 = new PlayLocalMusicBean(PlayLocalMusicBean.NEXT);
                RxBus.get().post(bean1);
                break;
            case R.id.bottom_layout:
                if (musicList.get(position).getDuration_millinseconds() > 0) {
                    Intent detailIntent = new Intent(MusicsOfAlbumActivity.this, MusicDetailActivity.class);
                    detailIntent.putExtra("progress", progress);
                    detailIntent.putExtra("position", position);
//                detailIntent.putExtra("totalTime",Integer.valueOf(musics.get(position).getDuration_millinseconds()+""));//音乐的时长，秒数
                    detailIntent.putExtra("musicbean", musicList);
                    detailIntent.putExtra("isPlaying", isPlaying);
                    startActivityForResult(detailIntent, 1);
                }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    isPlaying = data.getBooleanExtra("isPlaying", false);
                    if (isPlaying) {
                        mPlay.setSelected(true);
                    } else {
                        mPlay.setSelected(false);
                    }
                    if (position == data.getIntExtra("position", 0)) {
                        return;
                    }
                    position = data.getIntExtra("position", 0);
                    MusicBean bean = musicList.get(position);
                    Picasso.with(MusicsOfAlbumActivity.this).load(bean.getImage()).fit().placeholder(R.mipmap.music_image_default).into(mMusicImage);
                    mMusicName.setText(bean.getTitle());
                    mArtistName.setText(bean.getArtist().getName());
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
