package com.example.marvellous.mealorderingapprestaurants;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.image_logo);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        logo.startAnimation(animation);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    toNextPage();
                }
            }
        };
        timer.start();
    }

    private void toNextPage() {
        final Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
        finish();
    }

}

