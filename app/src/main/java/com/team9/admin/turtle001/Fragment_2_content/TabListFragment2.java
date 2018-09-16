package com.team9.admin.turtle001.Fragment_2_content;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.team9.admin.turtle001.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//音乐界面
public class TabListFragment2 extends Fragment {


    public static final String EXTRA_CONTENT = "content";

    public static TabListFragment2 newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);
        TabListFragment2 tabContentFragment = new TabListFragment2();
        tabContentFragment.setArguments(arguments);
        return tabContentFragment;
    }


    private List<Song> songList = new ArrayList<>();
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private int lastpositon = 999;
    public ListView listView;
    public View contentView2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView2 = inflater.inflate(R.layout.song_list, null);
        SongAdapter adapter = new SongAdapter(getContext(), R.layout.song_item, songList);
        initSongs();

        ListView listView = contentView2.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Song song = songList.get(position);
                if (position != lastpositon) {
                    mediaPlayer.reset();
                    try {
                        mediaPlayer.setDataSource(song.getSongId());
                        mediaPlayer.prepare();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    lastpositon = position;
                    mediaPlayer.start();
                    Toast.makeText(getActivity(), "开始播放\n"+song.name, Toast.LENGTH_SHORT).show();

                }
                else if(mediaPlayer.isPlaying()){
                    Toast.makeText(getActivity(), "停止播放\n"+song.name, Toast.LENGTH_SHORT).show();
                    mediaPlayer.pause();
                }

                else{
                    Toast.makeText(getActivity(), "开始播放\n"+song.name, Toast.LENGTH_SHORT).show();
                    mediaPlayer.start();
                }

            }
        });
        return contentView2;
    }
    private void initSongs() {


            Song song1 = new Song("浙江大学校歌",
                    "http://songlist-turtle.oss-cn-hangzhou.aliyuncs.com/%E6%B5%99%E6%B1%9F%E5%A4%A7%E5%AD%A6%E6%A0%A1%E6%AD%8C.mp3"
                    ,R.drawable.song1);
            songList.add(song1);
            Song song2 = new Song("風の住む街",
                    "http://songlist-turtle.oss-cn-hangzhou.aliyuncs.com/%E7%A3%AF%E6%9D%91%E7%94%B1%E7%BA%AA%E5%AD%90%20-%20%E9%A2%A8%E3%81%AE%E4%BD%8F%E3%82%80%E8%A1%97.mp3"
                    ,R.drawable.song2);
            songList.add(song2);
            Song song3 = new Song("Sys Bjerre - Sku' Ha' Gået Hjem",
                    "http://songlist-turtle.oss-cn-hangzhou.aliyuncs.com/Sys%20Bjerre%20-%20Sku%27%20Ha%27%20G%C3%A5et%20Hjem.mp3"
                    , R.drawable.song3);
            songList.add(song3);
            Song song4 = new Song("String Quartet Tribute to Edward Sharpe and the Magnetic Zeros",
                    "http://songlist-turtle.oss-cn-hangzhou.aliyuncs.com/Vitamin%20String%20Quartet%20-%20Home%20-%20String%20Quartet%20Tribute%20to%20Edward%20Sharpe%20and%20the%20Magnetic%20Zeros.mp3"
                , R.drawable.song4);
            songList.add(song4);
            Song song5 = new Song("Katie Sky - Monsters",
                    "http://songlist-turtle.oss-cn-hangzhou.aliyuncs.com/Katie%20Sky%20-%20Monsters.mp3"
                , R.drawable.song5);
            songList.add(song5);
            Song song6 = new Song("Doctor Vox - Frontier",
                    "http://songlist-turtle.oss-cn-hangzhou.aliyuncs.com/Doctor%20Vox%20-%20Frontier.mp3"
                , R.drawable.song6);
            songList.add(song6);
            Song song7 = new Song(" Secrets AMFB Onerepublic",
                    "http://songlist-turtle.oss-cn-hangzhou.aliyuncs.com/Bryson%20Andres%20-%20Secrets%20AMFB%20Onerepublic.mp3"
                , R.drawable.song7);
            songList.add(song7);

    }

}
