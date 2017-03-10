package com.example.lyc.bootymusic.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import com.example.lyc.bootymusic.bean.MusicBean;
import com.example.lyc.bootymusic.bean.RxBusBean.DurationBean;
import com.example.lyc.bootymusic.bean.RxBusBean.MusicPlayingProgressBean;
import com.example.lyc.bootymusic.bean.RxBusBean.PlayLocalMusicBean;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


//If you want your media to play in the background even when your application is not onscreen—that is,
// you want it to continue playing while the user is interacting with other applications—then you must
// start a Service and control the MediaPlayer instance from there.
public class PlayMusicService extends Service implements MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {
    public PlayMusicService() {
    }

    // If you perform an operation while in the wrong state, the system may throw an exception or cause other undesirable behaviors.
    //When you call stop(), however, notice that you cannot call start() again until you prepare the MediaPlayer again.
    private static MediaPlayer mediaPlayer;       //媒体播放器对象
    private String path;                        //音乐文件路径
    private int position = -1;
    private boolean isPause;                    //暂停状态
    private WifiManager.WifiLock wifiLock;
    private ArrayList<MusicBean> musicList = new ArrayList<>();
    private int playPositon;//当前歌曲播放进度对应的秒数
    private Timer mTimer;
    private TimerTask mTimerTask;

//    private void startSchedule(long delay, long period) {
//        if (mTimer == null) {
//            mTimer = new Timer();
//        }
//        mTimerTask = new TimerTask() {
//            @Override
//            public void run() {
//                Log.d("PlayMusicService", "----------------mRunnable-----------is running-----------");
//                if (mediaPlayer != null) {
//                    playPositon = mediaPlayer.getCurrentPosition();
//                    Log.d("PlayMusicService", "----------------mRunnable------playPosition-is------" + playPositon);
//                    RxBus.get().post(new MusicPlayingProgressBean(playPositon, false));
//                }
//            }
//        };
//        mTimer.schedule(mTimerTask, delay, period);
//    }
//
//    private void cancelSchedule() {
//        mTimerTask.cancel();
//    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
                if (mediaPlayer != null) {
                    RxBus.get().post(new MusicPlayingProgressBean(playPositon, false));
                    mHandler.postDelayed(mRunnable, 500);
                }
            }
        }
    };

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {

            if (mediaPlayer != null) {
                playPositon = mediaPlayer.getCurrentPosition();

                mHandler.sendEmptyMessage(1);
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        RxBus.get().register(this);
        mediaPlayer = new MediaPlayer();       //媒体播放器对象
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnPreparedListener(new PreparedListener());//注册一个监听器
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);//使cpu保持唤醒状态，知道pause或者stop，mediaplayer会自动释放cpu

        //If you are streaming media over the network and you are using Wi-Fi, you probably want to hold a WifiLock as well, which you must acquire
        // and release manually. So, when you start preparing the MediaPlayer with the remote URL, you should create and acquire the Wi-Fi lock.
        // When you pause or stop your media, or when you no longer need the network, you should release the lock:
        wifiLock = ((WifiManager) getSystemService(Context.WIFI_SERVICE))
                .createWifiLock(WifiManager.WIFI_MODE_FULL, "mylock");

    }


    @Subscribe
    public void onRxEvent(PlayLocalMusicBean object) {

        switch (object.getType()) {
            case PlayLocalMusicBean.PLAY:
                resume();
                break;
            case PlayLocalMusicBean.PAUSE:
                pause();
                break;
            case PlayLocalMusicBean.NEXT:
                position = (++position) % musicList.size();
                path = musicList.get(position).getPath();
                isPause = false;
                playPositon = 0;
                play();
                break;
            case PlayLocalMusicBean.PREVIOUS:
                if (position == 0) {
                    position = musicList.size() - 1;
                } else {
                    position = (--position) % musicList.size();
                }
                path = musicList.get(position).getPath();
                isPause = false;
                playPositon = 0;
                play();
                break;
        }
    }

    @Subscribe
    public void onRxBusEvent(MusicPlayingProgressBean bean) {

        if (bean.isForService()) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.seekTo(bean.getProgress());
            }
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mediaPlayer.isPlaying()) {
            stop();
        }
        musicList = (ArrayList<MusicBean>) intent.getSerializableExtra("musicList");
        position = intent.getIntExtra("position", 0);
        path = musicList.get(position).getPath();
        isPause = false;
        playPositon = 0;
        play();
        if (!wifiLock.isHeld()) {
            wifiLock.acquire();
        }
//        int msg = intent.getIntExtra("MSG", 0);
//        if(msg == AppConstant.PlayerMsg.PLAY_MSG) {
//            play(0);
//        } else if(msg == AppConstant.PlayerMsg.PAUSE_MSG) {
//            pause();
//        } else if(msg == AppConstant.PlayerMsg.STOP_MSG) {
//            stop();
//        }
        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * 播放音乐
     */
    private void play() {
        try {
            mediaPlayer.reset();//把各项参数恢复到初始状态
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepareAsync();  //进行缓冲
            if (!wifiLock.isHeld()) {
                wifiLock.acquire();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 恢复播放
     */
    private void resume() {
        if (isPause) {
            isPause = false;
            mediaPlayer.start();
//            startSchedule(500,500);
            mHandler.postDelayed(mRunnable, 500);
        }
    }

    /**
     * 暂停音乐
     */
    private void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            playPositon = mediaPlayer.getCurrentPosition();//获取毫秒数
            isPause = true;
        }
        if (wifiLock.isHeld()) {
            wifiLock.release();
        }

//        cancelSchedule();
        mHandler.removeCallbacks(mRunnable);
    }

    /**
     * 停止音乐
     */
    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            try {
                mediaPlayer.prepare(); // 在调用stop后如果需要再次通过start进行播放,需要之前调用prepare函数
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (wifiLock.isHeld()) {
            wifiLock.release();
        }
//        cancelSchedule();
        mHandler.removeCallbacks(mRunnable);
    }


    @Override
    public void onDestroy() {

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            if (wifiLock.isHeld()) {
                wifiLock.release();
            }
            mediaPlayer = null;
        }
//        cancelSchedule();
//        mTimer.cancel();
        mHandler.removeCallbacks(mRunnable);
        mHandler = null;
        RxBus.get().unregister(this);
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        mediaPlayer.reset();
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

        PlayLocalMusicBean bean = new PlayLocalMusicBean(PlayLocalMusicBean.COMPLETED);
        RxBus.get().post(bean);
//        mTimerTask.cancel();
        mHandler.removeCallbacks(mRunnable);
    }


    /**
     * 实现一个OnPrepareLister接口,当音乐准备好的时候开始播放
     */
    private final class PreparedListener implements MediaPlayer.OnPreparedListener {
        private int progress;

        public PreparedListener(int positon) {
            this.progress = positon;
        }

        public void setPositon(int positon) {
            this.progress = positon;
        }

        public PreparedListener() {

        }

        @Override
        public void onPrepared(MediaPlayer mp) {
            mediaPlayer.start();    //开始播放
            if (progress > 0) {    //如果音乐不是从头播放
                mediaPlayer.seekTo(progress);
            }
            Log.d("PlayMusicService", "----------------mediaPlayer.getDuration()-----------is -----------" + mediaPlayer.getDuration());
            if (musicList.get(position).getDuration_millinseconds() == 0&&mediaPlayer.getDuration()>0) {
                musicList.get(position).setDuration_millinseconds(mediaPlayer.getDuration());
                RxBus.get().post(new DurationBean(mediaPlayer.getDuration(),position));
            }
//            startSchedule(500,500);
            mHandler.postDelayed(mRunnable, 500);
        }
    }

}
