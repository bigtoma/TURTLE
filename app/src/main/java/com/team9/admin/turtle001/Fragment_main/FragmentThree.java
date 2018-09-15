package com.team9.admin.turtle001.Fragment_main;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import com.team9.admin.turtle001.Fragment_3_content.MainActivity_ai;
import com.team9.admin.turtle001.R;
import com.team9.admin.turtle001.ScreenSizeUtils;
import com.team9.admin.turtle001.diary.DiaryView;
import com.team9.admin.turtle001.diary.SelectAct;
import com.team9.admin.turtle001.history.ChartActivity;

import static android.content.Context.MODE_PRIVATE;


public class FragmentThree extends Fragment {
    private ImageView mHBack;
    private ImageView mHHead;

    private ItemView mNickName;
    private ItemView mSex;
    private ItemView mSignName;
    private ItemView mRebort;
    private ItemView mHistory;

    private ItemView mMe;
    private ItemView mMore;
    private ItemView mUpdate;
    private ItemView mAbout;
    private ItemView mDiary;

    private String textsex;
    private String textname;
    private String textsign;

    protected SharedPreferences.Editor editor;

    public View view2;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view2 = inflater.inflate(R.layout.activity_mycenter, container, false);
        ImageView headerView = view2.findViewById(R.id.h_back);
        PullScrollView pullScrollView = view2.findViewById(R.id.pullscrollview);
        pullScrollView.setmHeaderView(headerView);

        initView();
        setData();
        onClick();
        return view2;
    }



    private void initView() {

        mHBack = view2.findViewById(R.id.h_back);
        mHHead = view2.findViewById(R.id.h_head);
        mNickName = view2.findViewById(R.id.nickName);
        mSex = view2.findViewById(R.id.sex);
        mSignName = view2.findViewById(R.id.signName);
        mHistory = view2.findViewById(R.id.history);
        mRebort = view2.findViewById(R.id.rebort);
        mMe = view2.findViewById(R.id.me);
        mMore = view2.findViewById(R.id.more);
        mUpdate = view2.findViewById(R.id.update);
        mAbout = view2.findViewById(R.id.about);
        mDiary = view2.findViewById(R.id.diary_cell);

        editor = getActivity().getSharedPreferences("userdata",MODE_PRIVATE).edit();
    }


    private void setData () {
            //设置背景磨砂效果
          Glide.with(this).load(R.drawable.ic_user)
                    .bitmapTransform(new BlurTransformation(getContext(), 20), new CenterCrop(getContext()))
                    .into(mHBack);
            //设置圆形图像
            Glide.with(this).load(R.drawable.ic_user)
                    .bitmapTransform(new CropCircleTransformation(getContext()))
                    .into(mHHead);
        }

        private void onClick(){

            mHHead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast = Toast.makeText(getContext(),
                            "暂时不能更改", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });

            //设置用户名整个item的点击事件
            mNickName.setItemClickListener(new ItemView.itemClickListener() {
                @Override
                public void itemClick(String text) {
                    nameDialog();
                }
            });
            mSex.setItemClickListener(new ItemView.itemClickListener() {
                @Override
                public void itemClick(String text) {
                    singleDialog();
                }
            });
            mSignName.setItemClickListener(new ItemView.itemClickListener() {
                @Override
                public void itemClick(String text) {
                    signDialog();
                }
            });
            mRebort.setItemClickListener(new ItemView.itemClickListener() {
                public void itemClick(String text) {
                    Intent detailIntent = new Intent(getActivity(), MainActivity_ai.class);
                    startActivity(detailIntent);
                }
            });
            mHistory.setItemClickListener(new ItemView.itemClickListener() {
                @Override
                public void itemClick(String text) {
                    Intent intenthis = new Intent(getActivity(),ChartActivity.class);
                    startActivity(intenthis);
                }
            });
            mDiary.setItemClickListener(new ItemView.itemClickListener() {
                @Override
                public void itemClick(String text) {
                    Intent intenthis = new Intent(getActivity(),DiaryView.class);
                    startActivity(intenthis);
                }
            });
            mMe.setItemClickListener(new ItemView.itemClickListener() {
                @Override
                public void itemClick(String text) {
                    String path = "http://www.xinli001.com/ceshi?source=pc-home";
                    Intent intent = new Intent(getActivity(), WebActivity.class);
                    intent.putExtra("url", path);
                    startActivity(intent);

                }
            });
            mMore.setItemClickListener(new ItemView.itemClickListener() {
                @Override
                public void itemClick(String text) {
                    String path = "http://www.xlzx.zju.edu.cn/";
                    Intent intent = new Intent(getActivity(), WebActivity.class);
                    intent.putExtra("url", path);
                    startActivity(intent);
                }
            });
            mUpdate.setItemClickListener(new ItemView.itemClickListener() {
                @Override
                public void itemClick(String text) {
                    Toast toast = Toast.makeText(getContext(),
                            "当前已是最新版本", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                }
            });

            mAbout.setItemClickListener(new ItemView.itemClickListener() {
                @Override
                public void itemClick(String text) {
                    Intent intent = new Intent(getActivity(), AboutActivity.class);
                    startActivity(intent);
                }
            });

            SharedPreferences user = getActivity().getSharedPreferences("userdata",MODE_PRIVATE);
            mNickName.setRightDesc(user.getString("username","你的名字"));
            mSex.setRightDesc(user.getString("usersex","你的性别"));
            mSignName.setRightDesc(user.getString("usersign","修改"));
        }



    private void singleDialog() {
        final String items[] = {"男", "女","保密"};
        textsex ="男";
        editor.putString("usersex",textsex).apply();
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setIcon(R.drawable.ic_sex)//设置标题的图片
                .setTitle("性别")//设置对话框的标题
                //第一个参数:设置单选的资源;第二个参数:设置默认选中哪一项;
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textsex = items[which];
                        editor.putString("usersex",textsex).apply();

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int which) {
                        Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Message sex = new Message();
                                sex.what = 1;
                                handler.sendMessage(sex);
                            }
                        }).start();
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    private void nameDialog(){
        View view = getLayoutInflater().inflate(R.layout.half_dialog_view, null);
        final EditText editText = (EditText) view.findViewById(R.id.dialog_edit);
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_nick_name)//设置标题的图片
                .setTitle("用户名")//设置对话框的标题
                .setView(view)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                        String content = editText.getText().toString();
                        textname = content;
                        editor.putString("username",textname).apply();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Message name = new Message();
                                name.what = 2;
                                handler.sendMessage(name);
                            }
                        }).start();

                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    private void signDialog(){
        View view = getLayoutInflater().inflate(R.layout.dialog_sign, null);
        final EditText editText = (EditText) view.findViewById(R.id.dialog_edit);
        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setIcon(R.drawable.ic_sign_name)//设置标题的图片
                .setTitle("个性签名")//设置对话框的标题
                .setView(view)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String content = editText.getText().toString();
                        Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                        textsign = content;
                        editor.putString("usersign",textsign).apply();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Message sign = new Message();
                                sign.what = 3;
                                handler.sendMessage(sign);
                            }
                        }).start();

                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }





    private Handler handler = new Handler(){


        public void handleMessage(Message msg){
            Log.d("test", "sex");

            switch (msg.what){
                case 1:
                    mSex.setRightDesc(textsex);

                    break;
                case 2:
                    mNickName.setRightDesc(textname);
                    break;

                case 3:
                    mSignName.setRightDesc(textsign);
            }
        }
    };



}
