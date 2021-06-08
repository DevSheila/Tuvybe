package com.example.tuvybe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.spalshImage)
    ImageView mSplashImage;
    @BindView(R.id.appTitle)TextView mAppTitle;
    @BindView(R.id.appAbout)TextView mAppAbout;

    @BindView(R.id.splashButton)
    Button mSplashButton;

    //animation variables
    Animation topAnim,bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        //define animation context
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //hooks
        mSplashImage.setAnimation(topAnim);
        mAppTitle.setAnimation(topAnim);
        mAppAbout.setAnimation(bottomAnim);
        mSplashButton.setAnimation(bottomAnim);


    }
}