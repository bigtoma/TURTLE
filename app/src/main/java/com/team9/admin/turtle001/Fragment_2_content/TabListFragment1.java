package com.team9.admin.turtle001.Fragment_2_content;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.team9.admin.turtle001.Game2048.GameActivity;
import com.team9.admin.turtle001.Mussic_player.Main_Activity;
import com.team9.admin.turtle001.Mussic_player.Main_Activity2;
import com.team9.admin.turtle001.Mussic_player.Main_Activity3;
import com.team9.admin.turtle001.Mussic_player.Main_Activity4;
import com.team9.admin.turtle001.R;

import java.util.ArrayList;
import java.util.List;


public class TabListFragment1 extends Fragment implements AdapterView.OnItemClickListener {


    public static final String EXTRA_CONTENT = "content";

    public ListView mContentLv;



    private List<Mingxiang> MXlist = new ArrayList<Mingxiang>();

    public static TabListFragment1 newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);
        TabListFragment1 tabContentFragment = new TabListFragment1();
        tabContentFragment.setArguments(arguments);
        return tabContentFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_tab_list, null);
        mContentLv = (ListView) contentView.findViewById(R.id.lv_content);
        initMX();
        mContentLv.setOnItemClickListener(this);
        ViewCompat.setNestedScrollingEnabled(mContentLv, true);
        mContentLv.setAdapter(new ContentAdapter());

        return contentView;
    }

    private void initMX() {

        Mingxiang MX1 = new Mingxiang("7天冥想基础", "Main_Activity");
        MXlist.add(MX1);
        Mingxiang MX2 = new Mingxiang("乘车冥想", "Main_Activity2");
        MXlist.add(MX2);
        Mingxiang MX3 = new Mingxiang("冥想音乐", "Main_Activity3");
        MXlist.add(MX3);
        Mingxiang MX4 = new Mingxiang("睡前肌肉渐进放松", "Main_ACtivity4");
        MXlist.add(MX4);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }/*点击卡片事件*/

    private class ContentAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.item_simple_list_2, null);
            ImageView coverIv = (ImageView) contentView.findViewById(R.id.iv_cover);
            TextView MXtype = contentView.findViewById(R.id.type);
            TextView MXname = contentView.findViewById(R.id.tv_content);
            switch (position) {
                case 0:
                    MXtype.setText("冥想教学");
                    break;
                case 1:
                    MXtype.setText("情景冥想");
                    break;
                case 2:
                    MXtype.setText("冥想辅助");
                    break;
                case 3:
                    MXtype.setText("睡前冥想");
                    break;
                default:
                    break;
            }
            switch (position) {
                case 0:
                    MXname.setText("7天冥想基础");
                    break;
                case 1:
                    MXname.setText("乘车冥想");
                    break;
                case 2:
                    MXname.setText("冥想音乐");
                    break;
                case 3:
                    MXname.setText("睡前肌肉渐进放松");
                    break;
                default:
                    break;
            }


            coverIv.setImageResource(getResources().getIdentifier("ic_palette_0" + position % 4, "mipmap", getActivity().getPackageName()));
            contentView.findViewById(R.id.cv_content).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Mingxiang song = MXlist.get(position);
                    switch (position) {
                        case 0:
                            Intent detailIntent = new Intent(getActivity(), Main_Activity.class);
                            startActivity(detailIntent);
                            break;
                        case 1:
                            Intent detailIntent2 = new Intent(getActivity(),Main_Activity2.class);
                            startActivity(detailIntent2);
                            break;
                        case 2:
                            Intent detailIntent3 = new Intent(getActivity(), Main_Activity3.class);
                            startActivity(detailIntent3);
                            break;
                        case 3:
                            Intent detailIntent4 = new Intent(getActivity(), Main_Activity4.class);
                            startActivity(detailIntent4);
                            break;
                        default:
                            break;
                    }

                }
           /* public void onClick(View v) {
                    Intent detailIntent = new Intent(getActivity(),Main_Activity.class);
                    startActivity(detailIntent);
                }*/
            });
            return contentView;
        }

    }

}