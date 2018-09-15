package com.team9.admin.turtle001;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;;
import com.team9.admin.turtle001.Fragment_main.FragmentOne;
import com.team9.admin.turtle001.Fragment_main.FragmentTwo;
import com.team9.admin.turtle001.Fragment_main.FragmentThree;



public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar mBottomNavigationBar;
    private FragmentOne mFragmentOne;
    private FragmentThree mFragmentThree;
    private    FragmentTwo mFragmentTwo;
    private int data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppClose.getInstance().addActivity(this);
        /*设置底部导航栏风格*/
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setInActiveColor(R.color.gray);//unSelected icon color
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.mainpage,"首页").setActiveColorResource(R.color.colorText))
                .addItem(new BottomNavigationItem(R.drawable.channel, "频道").setActiveColorResource(R.color.colorText))
                .addItem(new BottomNavigationItem(R.drawable.mine,"我的").setActiveColorResource(R.color.colorText))
                .setFirstSelectedPosition(0)
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();

        Intent intent = getIntent();
         data = intent.getIntExtra("fragid",0);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (data){
            case 2:
                if (mFragmentTwo == null) {
                    mFragmentTwo = new FragmentTwo();
                }
                transaction.replace(R.id.ll_content, mFragmentTwo);
                mBottomNavigationBar.selectTab(1);
                break;
            default:
                if(mFragmentOne==null){
                    mFragmentOne = new FragmentOne();
                }
                transaction.replace(R.id.ll_content, mFragmentOne);
                break;
        }transaction.commit();
    }


    /**
     * 默认页
     */
    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mFragmentOne = new FragmentOne();
        transaction.replace(R.id.ll_content, mFragmentOne).commit();
    }


    public void onTabSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                transaction.replace(R.id.ll_content, mFragmentOne=new FragmentOne());
                break;
            case 1:
                if (mFragmentTwo == null) {
                    mFragmentTwo = new FragmentTwo();
                }
                transaction.replace(R.id.ll_content, mFragmentTwo);
                break;

            case 2:
                if (mFragmentThree == null) {
                    mFragmentThree = new FragmentThree();
                }
                transaction.replace(R.id.ll_content, mFragmentThree);

                break;
            default:
                if(mFragmentOne==null){
                    mFragmentOne = new FragmentOne();
                }
                transaction.replace(R.id.ll_content, mFragmentOne);
                break;
        }
        transaction.commit();


    }

    @Override
    public void onTabUnselected(int position) {
    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onBackPressed(){

        exitDialog();

    }

    private void exitDialog(){

        final Dialog dialog = new Dialog(this, R.style.NormalDialogStyle);
        View view = View.inflate(this, R.layout.dialog_bottom, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight() * 0.1f));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenSizeUtils.getInstance(this).getScreenWidth() * 0.9f);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);
        dialog.show();
        dialog.getWindow().findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppClose.getInstance().exit();
            }

        });

        dialog.getWindow().findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); //取消对话框
            }

        });

        }

}

