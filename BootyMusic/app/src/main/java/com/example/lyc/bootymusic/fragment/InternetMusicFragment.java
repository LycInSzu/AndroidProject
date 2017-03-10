package com.example.lyc.bootymusic.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lyc.bootymusic.Activity.MusicDetailActivity;
import com.example.lyc.bootymusic.Activity.MusicsOfAlbumActivity;
import com.example.lyc.bootymusic.Adapter.MusicAdapter;
import com.example.lyc.bootymusic.Adapter.MyAdapter;
import com.example.lyc.bootymusic.R;
import com.example.lyc.bootymusic.bean.AlbumBean;
import com.example.lyc.bootymusic.bean.ArtistBean;
import com.example.lyc.bootymusic.bean.MusicBean;
import com.example.lyc.bootymusic.bean.RxBusBean.DurationBean;
import com.example.lyc.bootymusic.bean.RxBusBean.MusicPlayingProgressBean;
import com.example.lyc.bootymusic.bean.RxBusBean.PlayLocalMusicBean;
import com.example.lyc.bootymusic.service.PlayMusicService;
import com.example.lyc.bootymusic.utils.LogUtils;
import com.example.lyc.bootymusic.utils.ToastUtil;
import com.example.lyc.bootymusic.utils.internet.RequestParams;
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


public class InternetMusicFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.keyword)
    EditText keyword;
    @BindView(R.id.search)
    Button search;
    @BindView(R.id.music_list)
    ListView musicListView;
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
//    @BindView(R.id.search_result)
//    ListView searchResult;


    private String mParam1;
    private String mParam2;
    private int pageSize = 2;
    private MusicAdapter mMusicAdapter;
    private AlbumAdapter mAlbumAdapter;
    private boolean isPlaying = false;
    private int position = -1;
    private int progress = 0;
    private int type = 1;//搜索类型（1：单曲  10：专辑  100：歌单   1000：歌手）

    private ArrayList<MusicBean> musicList = new ArrayList<>();//单曲的list
    private ArrayList<AlbumBean> albumList = new ArrayList<>();//专辑的list
//    private OnFragmentInteractionListener mListener;

    public InternetMusicFragment() {
        // Required empty public constructor
    }


    public static InternetMusicFragment newInstance(String param1, String param2) {
        InternetMusicFragment fragment = new InternetMusicFragment();
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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_internet_music, container, false);
        ButterKnife.bind(this, view);

        mMusicAdapter = new MusicAdapter(getActivity());
        mAlbumAdapter=new AlbumAdapter(getActivity());

        musicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log("item clicked is " + i);
                switch (type) {
                    case 1:
                        position = i;
                        MusicBean bean = musicList.get(i);
                        Picasso.with(getActivity()).load(bean.getImage()).fit().placeholder(R.mipmap.music_image_default).into(musicImage);
                        musicName.setText(bean.getTitle());
                        artistName.setText(bean.getArtist().getName());
                        if (bottomLayout.getVisibility() != View.VISIBLE) {
                            bottomLayout.setVisibility(View.VISIBLE);
                        }
                        isPlaying = true;
                        play.setSelected(true);
                        Intent intent = new Intent();
                        intent.putExtra("musicList", musicList);
                        intent.putExtra("position", i);
                        intent.setClass(getActivity(), PlayMusicService.class);
                        getActivity().startService(intent);       //启动服务
                        break;
                    case 10://专辑
                        LogUtils.e("查看专辑内容");
                        Intent albumIntent = new Intent();
                        albumIntent.putExtra("albumID", albumList.get(i).getId());
                        albumIntent.putExtra("albumName",albumList.get(i).getName());
                        albumIntent.setClass(getActivity(), MusicsOfAlbumActivity.class);
                        startActivity(albumIntent);
                        break;
                    case 100://歌手
                        break;
                    case 1000://歌单
                        break;
                }
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

    @OnClick(R.id.search)
    public void onClick() {
        String content = keyword.getText().toString().trim();
        if (!TextUtils.isEmpty(content)) {
            searchMusic(content);
            keyword.setText("");
        } else {
            ToastUtil.showToast(getActivity(), "关键词不能为空");
        }
    }


    private void searchMusic(String keyword) {
        LogUtils.e("-----------------------------------searchMusic---------------------------------------");
        RequestParams params = new RequestParams();
        params.put("type", type);
        params.put("sub", false);
        params.put("offset", 0);
        params.put("s", keyword);
        params.put("limit", pageSize);
        if (type == 1) {
            musicListView.setAdapter(mMusicAdapter);
//            http://s.music.163.com/search/get/?type=1&s=周杰伦&limit=5
            RetrofitHttpUtil.getSingleMusicInterface().commonConnect("search/get/", params).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String res = response.body();
                    try {
                        JSONObject object = new JSONObject(res);
                        if (object.optInt("code") == 200) {
                            JSONObject result=object.getJSONObject("result");
                           JSONArray array=result.getJSONArray("songs");

                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject music=array.getJSONObject(i);
                                    MusicBean bean = new MusicBean();
                                    bean.setId(music.optString("id"));
                                    bean.setTitle(music.optString("name"));
                                    if (music.getJSONArray("artists").length() > 0) {
                                        ArtistBean artist=new ArtistBean();
                                        artist.setName(music.getJSONArray("artists").getJSONObject(0).optString("name"));
                                        bean.setArtist(artist);
                                    } else {
                                        ArtistBean artist=new ArtistBean();
                                        artist.setName("未知");
                                        bean.setArtist(artist);
                                    }
                                    bean.setPath(music.optString("audio"));
                                    bean.setImage(music.getJSONObject("album").optString("picUrl"));
                                    bean.setDuration("");
                                    musicList.add(bean);
                                }
                                mMusicAdapter.setMyList(musicList);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });

        } else {
            /*http://music.163.com/api/search/get/*/
            RetrofitHttpUtil.getCommonRetrofitInterface().commonConnect("api/search/get/", params).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String res = response.body();
                    try {
                        JSONObject object = new JSONObject(res);
                        int code = object.getInt("code");
                        LogUtils.e(code + "");
                        if (code == 200) {
                            switch (type) {
//                                case 1:
//                                    LogUtils.e("case 1:");
//                                    musicList.clear();
//                                    JSONObject result = object.getJSONObject("result");
//                                    JSONArray array = result.getJSONArray("songs");
//                                    for (int i = 0; i < array.length(); i++) {
//                                        WangYiMusicBean mWangYiMusicBean = new Gson().fromJson(array.getString(i), WangYiMusicBean.class);
//                                        MusicBean bean = new MusicBean();
//                                        bean.setId(mWangYiMusicBean.getId() + "");
//                                        bean.setTitle(mWangYiMusicBean.getName());
//                                        if (mWangYiMusicBean.getArtists().size() > 0) {
//                                            bean.setArtist(mWangYiMusicBean.getArtists().get(0));
//                                        } else {
//                                            ArtistBean artistBean = new ArtistBean();
//                                            artistBean.setName("未知");
//                                            bean.setArtist(artistBean);
//                                        }
//                                        String url1 = MakeMD5.makeMD5(mWangYiMusicBean.getId() + "");
//                                        bean.setPath("http://m1.music.126.net/" + url1 + "/" + mWangYiMusicBean.getId() + ".mp3");
//                                        LogUtils.e("音乐文件的path是：  " + bean.getPath());
//                                        bean.setImage(mWangYiMusicBean.getAlbum().getArtist().getImg1v1Url());
//                                        bean.setDuration_millinseconds(mWangYiMusicBean.getDuration());
//                                        bean.setDuration(MediaUtil.formatTime(mWangYiMusicBean.getDuration()));
//                                        musicList.add(bean);
//                                    }
//                                    mMusicAdapter.setMyList(musicList);
//
//                                    break;
                                case 10://专辑
                                    albumList.clear();

                                    LogUtils.e("case 10:");
                                    JSONObject result = object.getJSONObject("result");
                                    JSONArray array = result.getJSONArray("albums");
                                    for (int i = 0; i < array.length(); i++) {
                                        AlbumBean album=new Gson().fromJson(array.getString(i),AlbumBean.class);
                                        albumList.add(album);
                                    }
                                    musicListView.setAdapter(mAlbumAdapter);
                                    mAlbumAdapter.setMyList(albumList);
                                    break;
                                case 100://歌手
                                    LogUtils.e("case 1000:");
                                    break;
                                case 1000://歌单
                                    LogUtils.e("case 100:");
                                    break;
                            }

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

    }

//    private void downloadMusic(String musicID) {
//        String url1= MakeMD5.makeMD5(musicID);
//        RequestParams params = new RequestParams();//    http://m1.music.126.net/[encrypted_song_id]/[song_dfsId].mp3
//        params.put("type", type);
//        params.put("sub", false);
//        params.put("offset", 0);
//        params.put("s", keyword);
//        params.put("limit", pageSize);
//        RetrofitHttpUtil.getImageRetrofitInterface().commonConnect(url1+"/"+musicID+".mp3", params).enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                String res = response.body();
//                LogUtils.e(res);
//                try {
//                    JSONObject object=new JSONObject(res);
//                    JSONObject result=new JSONObject("result");
//                    int code=result.optInt("code");
//                    if (code == 200) {
//                        switch (type){
//                            case 1:
//                                musicList.clear();
//                                JSONArray array=result.getJSONArray("songs");
//                                for (int i = 0; i < array.length(); i++) {
//                                    WangYiMusicBean mWangYiMusicBean=new Gson().fromJson(array.getString(i),WangYiMusicBean.class);
//                                    MusicBean bean = new MusicBean();
//                                    bean.setId(mWangYiMusicBean.getId()+"");
//                                    bean.setTitle(mWangYiMusicBean.getName());
//                                    if (mWangYiMusicBean.getArtists().size() > 0) {
//                                        bean.setArtist(mWangYiMusicBean.getArtists().get(0));
//                                    } else {
//                                        ArtistBean artistBean=new ArtistBean();
//                                        artistBean.setName("未知");
//                                        bean.setArtist(artistBean);
//                                    }
////                                    bean.setPath(mWangYiMusicBean.getAudio());
//                                    bean.setImage(mWangYiMusicBean.getAlbum().getArtist().getImg1v1Url());
//                                    bean.setDuration_millinseconds(mWangYiMusicBean.getDuration());
//                                    bean.setDuration(MediaUtil.formatTime(mWangYiMusicBean.getDuration()));
//                                    musicList.add(bean);
//                                }
//                                mMusicAdapter.setMyList(musicList);
//
//                                break;
//                            case 10://专辑
//
//                                break;
//                            case 100://歌手
//
//                                break;
//                            case 1000://歌单
//
//                                break;
//                        }
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });
//    }

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
                position = (++position) % musicList.size();
                MusicBean bean = musicList.get(position);
                Picasso.with(getActivity()).load("imageUrl").fit().placeholder(R.mipmap.music_image_default).into(musicImage);
                musicName.setText(bean.getTitle());
                artistName.setText(bean.getArtist().getName());
                isPlaying = true;
                play.setSelected(true);
                PlayLocalMusicBean bean1 = new PlayLocalMusicBean(PlayLocalMusicBean.NEXT);
                RxBus.get().post(bean1);
                break;
            case R.id.bottom_layout:
                if (musicList.get(position).getDuration_millinseconds() > 0) {
                    Intent detailIntent = new Intent(getActivity(), MusicDetailActivity.class);
                    detailIntent.putExtra("progress", progress);
                    detailIntent.putExtra("position", position);
//                detailIntent.putExtra("totalTime",Integer.valueOf(musics.get(position).getDuration_millinseconds()+""));//音乐的时长，秒数
                    detailIntent.putExtra("musicbean", musicList);
                    detailIntent.putExtra("isPlaying", isPlaying);
                    startActivityForResult(detailIntent, 1);
                }
                break;
        }
    }



    class AlbumAdapter extends MyAdapter<AlbumBean> {

        public AlbumAdapter(Context context) {
            super(context);
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if (view == null) {
                view = inflater.inflate(R.layout.album_item_layout, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            AlbumBean bean = myList.get(i);
            holder.positon.setText((i + 1) + "");
            Picasso.with(mContext).load(bean.getPicUrl()).fit().placeholder(R.mipmap.album).into(holder.musicImage);
            holder.musicName.setText(bean.getName());
            holder.musicName.setSelected(true);
            holder.artistName.setText(bean.getArtist().getName());
            holder.duration.setText(bean.getSize() + "");
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
            @BindView(R.id.music_num)
            TextView duration;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    @Subscribe
    public void onRxEventBus(PlayLocalMusicBean obj) {
        if (obj.getType() == PlayLocalMusicBean.COMPLETED) {
            isPlaying = false;
            play.setSelected(false);
        }
    }

    @Subscribe
    public void onRxBusEvent(MusicPlayingProgressBean bean) {
        if (!bean.isForService())
            progress = bean.getProgress();
    }

    @Subscribe
    public void onRxEventBus(DurationBean obj) {
        musicList.get(obj.getPosition()).setDuration_millinseconds(obj.getDuration());
        mMusicAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    isPlaying = data.getBooleanExtra("isPlaying", false);
                    if (isPlaying) {
                        play.setSelected(true);
                    } else {
                        play.setSelected(false);
                    }
                    if (position == data.getIntExtra("position", 0)) {
                        return;
                    }
                    position = data.getIntExtra("position", 0);
                    MusicBean bean = musicList.get(position);
                    Picasso.with(getActivity()).load(bean.getImage()).fit().placeholder(R.mipmap.music_image_default).into(musicImage);
                    musicName.setText(bean.getTitle());
                    artistName.setText(bean.getArtist().getName());
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
//        menu.add("单曲").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//        menu.add("专辑").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//        menu.add("歌手").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        type=0;//搜索类型（0：单曲  1：专辑  2：歌单   3：歌手）
        LogUtils.e("------------------------   index is" + item.getItemId() + " && menu text is " + item.getTitle());
        switch (item.getItemId()) {
            case R.id.action_albums:
                type = 10;
                keyword.setHint("搜索专辑");
                break;
            case R.id.action_artists:
                type = 100;
                keyword.setHint("搜索歌单");
                break;
            case R.id.action_music_orders:
                type = 1000;
                keyword.setHint("搜索歌手");
                break;
            case R.id.action_single_music:
                type = 1;
                keyword.setHint("搜索单曲");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
