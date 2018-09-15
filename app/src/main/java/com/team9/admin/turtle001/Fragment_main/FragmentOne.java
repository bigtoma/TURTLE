package com.team9.admin.turtle001.Fragment_main;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.taobao.library.VerticalBannerView;
import com.team9.admin.turtle001.ActivityCollector;
import com.team9.admin.turtle001.Fragment_3_content.MainActivity_ai;
import com.team9.admin.turtle001.Game2048.GameActivity;
import com.team9.admin.turtle001.Mussic_player.Main_Activity;
import com.team9.admin.turtle001.R;
import com.team9.admin.turtle001.article.Article;
import com.team9.admin.turtle001.article.Lowest;
import com.team9.admin.turtle001.diary.MainActivity_diary;
import com.team9.admin.turtle001.happy.Q0Activity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class FragmentOne extends Fragment   implements OnBannerListener  {

    private Banner banner;
    private VerticalBannerView upRoll;
    private List<Integer> list_path;
    private ArrayList<String> list_title;
    String[] url = new String[4];
    private View view2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view2 = inflater.inflate(R.layout.activity_homepage, container, false);
        List<Model> datas = new ArrayList<Model>();
        datas.add(new Model("大不自多，海纳江河，惟学无际，际于天地","http://www.zju.edu.cn/"));
        datas.add(new Model("五花马，千金裘，呼儿将出换美酒，与尔同销万古愁。","https://baike.baidu.com/item/%E6%9D%8E%E7%99%BD/1043?fr=aladdin"));
        datas.add(new Model("我是阿拉伯的酋长，你的爱情由我独享，今夜当你进入梦乡，我将溜进你的闺房","https://book.douban.com/subject/1008988/"));
        datas.add(new Model("面对愚昧，神们自己也缄口不言","https://book.douban.com/subject/26264967/"));
        datas.add(new Model("他的梦幻在阳台上筑起黑燕子的巢穴，在午睡的昏沉中留下亲吻和扇动翅膀的窸窣。","https://book.douban.com/subject/10594787/"));
        TBAdapter adapter = new TBAdapter(datas,getContext());
        upRoll = (VerticalBannerView) view2.findViewById(R.id.uoRoll);
        upRoll.setAdapter(adapter);
        upRoll.start();

        FloatingActionButton fab = (FloatingActionButton) view2.findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "添加日记", Toast.LENGTH_SHORT).show();
                Intent detailIntent = new Intent(getActivity(),MainActivity_diary.class);
                startActivity(detailIntent);
            }
        });

        Article article =new Article(getContext());
        article.addActicle();
        banner = (Banner) view2.findViewById(R.id.banner);
        initdata();
        bannerInitView();
        buttonOnclick();
        return view2;
    }


    private void bannerInitView() {

        banner.setViewPagerIsScroll(true);//是否轮播
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(com.youth.banner.Transformer.Accordion);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();
    }



    private void initdata(){

        //放图片地址的集合
        list_path=new ArrayList<>();
        //放标题的集合
        list_title = new ArrayList<>();

        Article article = new Article(getContext());
        Lowest lowest = new Lowest(getContext());


        Cursor cursor = lowest.searchDB();
        int i = 0;
        String tag,s;
        if (cursor.moveToFirst()){
            do{
                tag = cursor.getString(cursor.getColumnIndex("content"));
                url[i] = article.getIndex(tag);
                i++;
                s = "banner"+tag;
                int resid = getResources().getIdentifier(s,"drawable",getActivity().getPackageName());
                list_path.add(resid);
                String title = article.getTitle(tag);
                list_title.add(title);
            }while (cursor.moveToNext());
        }else {
            for (i = 0;i < 4 ;i++){
                tag = String.valueOf(i+1);
                url[i] = article.getIndex(tag);
                s = "banner"+tag;
                int resid = getResources().getIdentifier(s,"drawable",getActivity().getPackageName());
                list_path.add(resid);
                String title = article.getTitle(tag);
                list_title.add(title);
            }
        }
        banner.setImages(list_path);
        banner.setBannerTitles(list_title);
    }


    public void buttonOnclick(){
        ImageButton image1 = (ImageButton) view2.findViewById(R.id.imageButton1);
        ImageButton image2 = (ImageButton) view2.findViewById(R.id.imageButton2);
        ImageButton image3 = (ImageButton) view2.findViewById(R.id.imageButton3);

        Button totest =(Button) view2.findViewById(R.id.totest) ;

        ImageButton bottomimage1 = (ImageButton) view2.findViewById(R.id.downImage1);
        ImageButton bottomimage2 = (ImageButton) view2.findViewById(R.id.downImage2);
        ImageButton bottomimage3 = (ImageButton) view2.findViewById(R.id.downImage3);
        ImageButton bottomimage4 = (ImageButton) view2.findViewById(R.id.downImage4);
        ImageButton bottomimage5 = (ImageButton) view2.findViewById(R.id.downImage5);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(getActivity(), MainActivity_ai.class);
                startActivity(detailIntent);

            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(getActivity(), Mx_introduction.class);
                startActivity(detailIntent);

            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(getActivity(), GameActivity.class);
                startActivity(detailIntent);

            }
        });


        totest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(getActivity(), Q0Activity.class);
                startActivity(detailIntent);

            }
        });

        bottomimage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("url","https://mp.weixin.qq.com/s/H2eMip_2svxu-vMY4pDSsg");
                startActivity(intent);

            }
        });

        bottomimage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("url","https://mp.weixin.qq.com/s/79t2BXCLmN7kitsOvXM2DA");
                startActivity(intent);

            }
        });

        bottomimage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                intent.putExtra("url","https://mp.weixin.qq.com/s/Acf18DPmlO2lugohHbwrVg");
                startActivity(intent);

            }
        });

        bottomimage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "敬请期待", Toast.LENGTH_SHORT).show();

            }
        });
        bottomimage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "敬请期待", Toast.LENGTH_SHORT).show();

            }
        });

    }




    //轮播图的监听方法
    @Override
    public void OnBannerClick(int position) {
        Intent intent = new Intent(getActivity(), com.team9.admin.turtle001.Fragment_main.WebActivity.class);
        intent.putExtra("url",url[position]);
        startActivity(intent);

    }
    //    自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext()).load(path).into(imageView);
        }
    }



    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
        upRoll.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
        upRoll.stop();
    }




}






