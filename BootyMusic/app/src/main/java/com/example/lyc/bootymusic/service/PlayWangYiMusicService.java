package com.example.lyc.bootymusic.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import com.example.lyc.bootymusic.bean.MusicBean;
import com.example.lyc.bootymusic.bean.RxBusBean.PlayLocalMusicBean;
import com.example.lyc.bootymusic.bean.RxBusBean.PlayWangYiMusicBean;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;

import java.util.ArrayList;

//If you want your media to play in the background even when your application is not onscreen—that is,
// you want it to continue playing while the user is interacting with other applications—then you must
// start a Service and control the MediaPlayer instance from there.
public class PlayWangYiMusicService extends Service implements MediaPlayer.OnErrorListener,MediaPlayer.OnCompletionListener {
    public PlayWangYiMusicService() {
    }
    // If you perform an operation while in the wrong state, the system may throw an exception or cause other undesirable behaviors.
    //When you call stop(), however, notice that you cannot call start() again until you prepare the MediaPlayer again.
    private static MediaPlayer mediaPlayer;       //媒体播放器对象
    private String path;                        //音乐文件路径
    private int position=-1;
    private boolean isPause;                    //暂停状态
    private WifiManager.WifiLock wifiLock;
    private ArrayList<MusicBean> musicList = new ArrayList<>();
    private long playPositon;//当前歌曲的播放进度
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
    public void onRxEvent(PlayWangYiMusicBean object) {
        Log.d("PlayMusicService", "--------------------onRxEvent ----------");
        switch (object.getType()) {
            case PlayWangYiMusicBean.PLAY:
                play();
                break;
            case PlayWangYiMusicBean.PAUSE:
                pause();
                break;
            case PlayWangYiMusicBean.NEXT:
                position=(++position)%musicList.size();
                path=musicList.get(position).getPath();
                isPause=false;
                play();
                break;
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
        musicList= (ArrayList<MusicBean>) intent.getSerializableExtra("musicList");
        position = intent.getIntExtra("position",0);
        path=musicList.get(position).getPath();
        isPause=false;
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

            if (isPause) {
                isPause=false;
                mediaPlayer.start();
            } else {
                mediaPlayer.reset();//把各项参数恢复到初始状态
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepareAsync();  //进行缓冲
            }
            if (!wifiLock.isHeld()) {
                wifiLock.acquire();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停音乐
     */
    private void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            playPositon=mediaPlayer.getCurrentPosition();
            isPause = true;
        }
        if (wifiLock.isHeld()) {
            wifiLock.release();
        }
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
    }


    @Override
    public void onDestroy() {
        Log.d("PlayMusicService", "--------------------onDestroy ----------");
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            if (wifiLock.isHeld()) {
                wifiLock.release();
            }
            mediaPlayer = null;
        }
        RxBus.get().unregister(this);
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        mediaPlayer.reset();
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.d("PlayMusicService", "--------------------onCompletion ----------");
        PlayWangYiMusicBean bean = new PlayWangYiMusicBean(PlayWangYiMusicBean.COMPLETED);
        RxBus.get().post(bean);
    }


    /**
     * 实现一个OnPrepareLister接口,当音乐准备好的时候开始播放
     */
    private final class PreparedListener implements MediaPlayer.OnPreparedListener {
        private int positon;

        public PreparedListener(int positon) {
            this.positon = positon;
        }

        public void setPositon(int positon) {
            this.positon = positon;
        }

        public PreparedListener() {
        }

        @Override
        public void onPrepared(MediaPlayer mp) {
            mediaPlayer.start();    //开始播放
            if (positon > 0) {    //如果音乐不是从头播放
                mediaPlayer.seekTo(positon);
            }
        }
    }

}