package com.example.lyc.bootymusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by LYC on 2017/1/8.
 */

public abstract class  MyAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected LayoutInflater inflater;
    public ArrayList<T> getMyList() {
        return myList;
    }

    public void setMyList(ArrayList<T> list) {
        if (myList==null){
            myList=new ArrayList<>();
        }else {
            this.myList.clear();
        }
        this.myList.addAll(list);
        notifyDataSetChanged();
    }

    protected ArrayList<T> myList;

    public MyAdapter(Context context) {
        mContext=context;
        inflater= LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return myList==null?0: myList.size();
    }

    @Override
    public Object getItem(int i) {
        return myList==null?null:myList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
