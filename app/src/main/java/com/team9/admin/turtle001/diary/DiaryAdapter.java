package com.team9.admin.turtle001.diary;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.team9.admin.turtle001.R;

public class DiaryAdapter extends BaseAdapter {

    private Context context;
    private Cursor cursor;
    private LinearLayout layout;

    public DiaryAdapter(Context context,Cursor cursor){
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public int getCount(){
        return cursor.getCount();
    }
    @Override
    public Object getItem(int position) {
        return cursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        layout = (LinearLayout)inflater.inflate(R.layout.cell,null);
        TextView timetv = layout.findViewById(R.id.list_time);
        TextView titletv = layout.findViewById(R.id.list_title);
        cursor.moveToPosition(position);
        String time = cursor.getString(cursor.getColumnIndex("time"));
        String title = cursor.getString(cursor.getColumnIndex("title"));
        int color = cursor.getInt(cursor.getColumnIndex("color"));
        timetv.setText(time);
        titletv.setText(title);
        layout.setBackgroundColor(color);
        return layout;
    }
}
