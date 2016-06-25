package com.example.administrator.day0622_chabaike.ui.fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.day0622_chabaike.adapter.InfoListAdapter;
import com.example.administrator.day0622_chabaike.beans.Info;
import com.example.administrator.httplib.SQLiteDataBaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/25 0025.
 */
public class CollectFragment extends Fragment {
    private InfoListAdapter adapter;
    private List<Info> infos=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        initData();




        return super.onCreateView(inflater, container, bundle);
    }

    private void initData() {
        Context context=getActivity().getApplication();
        SQLiteDataBaseHelper sqLiteDataBaseHelper=new SQLiteDataBaseHelper(context,"tea");

        Cursor cursor = sqLiteDataBaseHelper.selectCursor(
                "student",)


        while(cursor.moveToNext()) {
            Info info=new Info();
            int index = cursor.getColumnIndex("id");
            int id = cursor.getInt(index);
            index = cursor.getColumnIndex("title");
            String title = cursor.getString(index);
            index = cursor.getColumnIndex("description");
            String description = cursor.getString(index);
        /*index = cursor.getColumnIndex("keywords");
        String keywords = cursor.getString(index);
        index = cursor.getColumnIndex("infoclass");
        int infoclass = cursor.getInt(index);
        index = cursor.getColumnIndex("count");
        int count = cursor.getInt(index);
        index = cursor.getColumnIndex("fcount");
        int fcount = cursor.getInt(index);*/
            index = cursor.getColumnIndex("img");
            String img=cursor.getString(index);
            index = cursor.getColumnIndex("time");
            String time = cursor.getString(index);
            index = cursor.getColumnIndex("rcount");
            int rcount = cursor.getInt(index);
            info.setId(id);
            info.setImg(img);
            info.setDescription(description);
            info.setRcount(rcount);
            info.setTime(time);
            list.add(info);
        }


    }
}