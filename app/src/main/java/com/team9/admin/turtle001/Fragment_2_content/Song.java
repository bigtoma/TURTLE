package com.team9.admin.turtle001.Fragment_2_content;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.team9.admin.turtle001.R;
//音乐的类
public class Song {
    public String name;
    private int imageId;
    private String songId;
    public Song(String name ,String songId ,int imageId) {
        this.name = name;
        this.songId = songId;
        this.imageId = imageId;
    }


    public String getName() {
        return name;
    }

    public String getSongId() {
        return songId;
    }

    public int getImageId() {
        return imageId;
    }
}
