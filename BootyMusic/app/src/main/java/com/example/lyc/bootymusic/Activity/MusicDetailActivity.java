package com.example.lyc.bootymusic.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.lyc.bootymusic.R;
import com.example.lyc.bootymusic.bean.MusicBean;
import com.example.lyc.bootymusic.bean.RxBusBean.MusicPlayingProgressBean;
import com.example.lyc.bootymusic.bean.RxBusBean.PlayLocalMusicBean;
import com.example.lyc.bootymusic.fragment.LocalMusicFragment;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MusicDetailActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.music_name)
    TextView mMusicName;
    @BindView(R.id.seek_bar)
    SeekBar mSeekBar;
    @BindView(R.id.recycle_state)
    ImageView mRecycleState;
    @BindView(R.id.previous_music)
    ImageView mPreviousMusic;
    @BindView(R.id.play_or_pause_button)
    ImageView mPlayOrPauseButton;
    @BindView(R.id.next_music)
    ImageView mNextMusic;
    @BindView(R.id.function_button_layout)
    LinearLayout mFunctionButtonLayout;
    @BindView(R.id.activity_music_detail)
    RelativeLayout mActivityMusicDetail;
    @BindView(R.id.artist_name)
    TextView mArtistName;

    private int progress = 0;
    //    private int duration_seconds = 0;
    private MusicBean mMusicBean;
    private boolean isPlaying = false;
    private ArrayList<MusicBean> musics = new ArrayList<>();
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this);
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_music_detail);
        ButterKnife.bind(this);
        progress = getIntent().getIntExtra("progress", 0);
        musics = (ArrayList<MusicBean>) getIntent().getSerializableExtra("musicbean");
        position=getIntent().getIntExtra("position",0);
        mMusicBean=musics.get(position);
        isPlaying=getIntent().getBooleanExtra("isPlaying",false);
        if (isPlaying){
            mPlayOrPauseButton.setSelected(true);
        }else {
            mPlayOrPauseButton.setSelected(false);
        }
        Log.d("MusicDetailActivity", "-------------------- progress==----------  " + progress);
        Log.d("MusicDetailActivity", "-------------------- duration_seconds==----------  " + mMusicBean.getDuration_millinseconds());
        mSeekBar.setMax((int) mMusicBean.getDuration_millinseconds());
        mSeekBar.setProgress(progress);
        mSeekBar.setOnSeekBarChangeListener(this);
        mMusicName.setText(mMusicBean.getTitle());
        setTitle(mMusicBean.getTitle());
        mArtistName.setText(mMusicBean.getArtist().getName());
    }

    @Override
    public void setListener() {

    }

    @OnClick({R.id.recycle_state, R.id.previous_music, R.id.play_or_pause_button, R.id.next_music})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recycle_state:
                break;
            case R.id.previous_music:
                if (position==0){
                    position=musics.size()-1;
                }else {
                    position = (--position) % musics.size();
                }
                MusicBean previousmusic = musics.get(position);
//                Picasso.with(MusicDetailActivity.this).load("imageUrl").fit().placeholder(R.mipmap.music_image_default).into(mMusic);
                setTitle(mMusicBean.getTitle());
                mArtistName.setText(previousmusic.getArtist().getName());
                isPlaying = true;
                mPlayOrPauseButton.setSelected(true);
                PlayLocalMusicBean previousbean = new PlayLocalMusicBean(PlayLocalMusicBean.PREVIOUS);
                RxBus.get().post(previousbean);
                break;
            case R.id.play_or_pause_button:
                if (isPlaying) {
                    isPlaying = false;
                    mPlayOrPauseButton.setSelected(false);
                    PlayLocalMusicBean bean = new PlayLocalMusicBean(PlayLocalMusicBean.PAUSE);
                    RxBus.get().post(bean);
                } else {
                    isPlaying = true;
                    mPlayOrPauseButton.setSelected(true);
                    PlayLocalMusicBean bean = new PlayLocalMusicBean(PlayLocalMusicBean.PLAY);
                    RxBus.get().post(bean);
                }
                break;
            case R.id.next_music:
                position=(++position)%musics.size();
                MusicBean nextmusic = musics.get(position);
//                Picasso.with(MusicDetailActivity.this).load("imageUrl").fit().placeholder(R.mipmap.music_image_default).into(mMusic);
                setTitle(mMusicBean.getTitle());
                mArtistName.setText(nextmusic.getArtist().getName());
                isPlaying = true;
                mPlayOrPauseButton.setSelected(true);
                PlayLocalMusicBean nextbean = new PlayLocalMusicBean(PlayLocalMusicBean.NEXT);
                RxBus.get().post(nextbean);
                break;
        }
    }

    @Subscribe
    public void onRxBusEvent(MusicPlayingProgressBean bean) {
        Log.d("MusicDetailActivity", "-------------------- onRxBusEvent(MusicPlayingProgressBean bean) ----------");
        if (!bean.isForService()) {
            int progress = bean.getProgress();
            mSeekBar.setProgress(progress);
        }
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void onBackPressed() {
        Intent resultIntent=new Intent(MusicDetailActivity.this, LocalMusicFragment.class);
        resultIntent.putExtra("progress",progress);
        resultIntent.putExtra("position",position);
        resultIntent.putExtra("isPlaying",isPlaying);
        setResult(RESULT_OK,resultIntent);
        RxBus.get().unregister(this);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //----------------------------------------------seek bar listener----------------------------------
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log("progress is  " + progress);
        if (fromUser) {
            RxBus.get().post(new MusicPlayingProgressBean(progress, true));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
//----------------------------------------------seek bar listener---------------------end----------
}
