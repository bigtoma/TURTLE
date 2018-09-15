package com.team9.admin.turtle001.Fragment_2_content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.team9.admin.turtle001.R;
import java.util.List;


public class
SongAdapter extends ArrayAdapter<Song> {

    private int resourceId;

    public SongAdapter(Context context, int textViewResourceId,
                        List<Song> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Song song = getItem(position); // 获取当前项的Fruit实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.songImage = (ImageView) view.findViewById (R.id.song_image);
            viewHolder.songName = (TextView) view.findViewById (R.id.song_name);
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        viewHolder.songImage.setImageResource(song.getImageId());
        viewHolder.songName.setText(song.getName());
        return view;
    }

    class ViewHolder {

        ImageView songImage;

        TextView songName;

    }
}
