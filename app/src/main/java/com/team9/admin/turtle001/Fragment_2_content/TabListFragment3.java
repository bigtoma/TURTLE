package com.team9.admin.turtle001.Fragment_2_content;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.team9.admin.turtle001.Game2048.GameActivity;
import com.team9.admin.turtle001.Mussic_player.Main_Activity;
import com.team9.admin.turtle001.R;

//游戏界面

public class TabListFragment3 extends Fragment  {


    public static final String EXTRA_CONTENT = "content";


    public static TabListFragment3 newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);
        TabListFragment3 tabContentFragment = new TabListFragment3();
        tabContentFragment.setArguments(arguments);
        return tabContentFragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = inflater.inflate(R.layout.gamecenter, null);
        contentView.findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(getActivity(), GameActivity.class);
                startActivity(detailIntent);
            }
        });
        return contentView;
    }
}
