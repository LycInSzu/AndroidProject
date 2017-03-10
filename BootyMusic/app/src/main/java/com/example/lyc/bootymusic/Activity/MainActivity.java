package com.example.lyc.bootymusic.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lyc.bootymusic.R;
import com.example.lyc.bootymusic.fragment.InternetMusicFragment;
import com.example.lyc.bootymusic.fragment.LocalMusicFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;


    private FragmentManager mFragmentManager;
    private LocalMusicFragment localMusicFragment;
    private InternetMusicFragment internetMusicFragment;
//    @BindView(R.id.fab)
//    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    /**
     * [初始化参数]
     *
     * @param params
     */
    @Override
    public void initParams(Bundle params) {

    }

    /**
     * [初始化控件]
     */
    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.local_music);
        mFragmentManager = getSupportFragmentManager();
        localMusicFragment=LocalMusicFragment.newInstance("","");
        mFragmentManager.beginTransaction().add(R.id.fragment_content, localMusicFragment).commit();
    }




    /**
     * [设置监听]
     */
    @Override
    public void setListener() {

    }

    /**
     * [业务操作]
     *
     * @param mContext
     */
    @Override
    public void doBusiness(Context mContext) {

    }

    /**
     * View点击
     *
     * @param v
     **/
    @Override
    public void widgetClick(View v) {
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else{
            moveTaskToBack(false);//false表示只有当该activity是根activity时有效
        } /*else {
            super.onBackPressed();
        }*/
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.local_music) {//本地音乐
            if (localMusicFragment==null){
                localMusicFragment=LocalMusicFragment.newInstance("","");
                FragmentTransaction transaction=mFragmentManager.beginTransaction();
                if (internetMusicFragment!=null){
                    transaction.hide(internetMusicFragment);
                }
                transaction.add(R.id.fragment_content, localMusicFragment).commit();
            }else {
                FragmentTransaction transaction=mFragmentManager.beginTransaction();
                if (internetMusicFragment!=null){
                    transaction.hide(internetMusicFragment);
                }
                transaction.show(localMusicFragment).commit();
            }

        } else if (id == R.id.internet_music) {//网络音乐
            if (internetMusicFragment==null){
                internetMusicFragment=InternetMusicFragment.newInstance("","");
                FragmentTransaction transaction=mFragmentManager.beginTransaction();
                if (localMusicFragment!=null){
                    transaction.hide(localMusicFragment);
                }
                transaction.add(R.id.fragment_content, internetMusicFragment).commit();
            }else {
                FragmentTransaction transaction=mFragmentManager.beginTransaction();
                if (localMusicFragment!=null){
                    transaction.hide(localMusicFragment);
                }
                transaction.show(internetMusicFragment).commit();
            }
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
