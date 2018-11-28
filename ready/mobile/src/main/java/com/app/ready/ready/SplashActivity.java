package com.app.ready.ready;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animOpen = new AlphaAnimation(0.0f,1.0f);
                animOpen.setDuration(1000);
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        final Animation animClose = new AlphaAnimation(1.0f, 0.0f);
                        animClose.setDuration(1000);
                        ImageView imageView = (ImageView) findViewById(R.id.imgReadySplash);
                        imageView.startAnimation(animClose);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } ,3000);
            }
        }, 2000);
    }
}
