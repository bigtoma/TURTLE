package com.team9.admin.turtle001;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.team9.admin.turtle001.Fragment_2_content.Mingxiang;
import com.team9.admin.turtle001.Mussic_player.Main_Activity;

public class welcome2 extends AppCompatActivity {



        private ImageView mShowPicture;
        private TextView mShowText;

        private Animation mFadeIn;
        private Animation mFadeInScale;
        private Animation mFadeOut;

        private Drawable mPicture_1;

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.welcome2);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            mShowPicture = (ImageView) findViewById(R.id.guide_picture);
            mShowText = (TextView) findViewById(R.id.guide_content);
            findViewById(R.id.guide_picture).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                            Intent detailIntent = new Intent(welcome2.this, MainActivity.class);
                            startActivity(detailIntent); }
            });
            init();
            setListener();
        }

        private void init() {
            initPicture();
            initAnim();
            mShowPicture.setImageDrawable(mPicture_1);
            mShowText.setText(getString(R.string.guide_one));
            mShowPicture.startAnimation(mFadeIn);
        }

        private void initAnim() {
            mFadeIn = AnimationUtils.loadAnimation(this,
                    R.anim.guide_welcome_fade_in);
            mFadeInScale = AnimationUtils.loadAnimation(this,
                    R.anim.guide_welcome_fade_in_scale);
            mFadeOut = AnimationUtils.loadAnimation(this,
                    R.anim.guide_welcome_fade_out);
        }

        private void initPicture() {
            mPicture_1 = getResources().getDrawable(R.drawable.guide_pic1);
        }

        private void setListener() {
            mFadeIn.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    mShowPicture.startAnimation(mFadeInScale);
                }
            });
            mFadeInScale.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    Intent intent  = new Intent(welcome2.this,MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.guide_welcome_fade_in,R.anim.hold);
                }
            });
        }

        public void onStop(){
            super.onStop();
            finish();
        }

}
