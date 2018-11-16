package com.example.marvellous.mealorderingapprestaurants;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    TextView tagline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tagline = findViewById(R.id.tagline);
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/Reality_Sunday.ttf");
        tagline.setTypeface(face);
        Button mSignin = findViewById(R.id.btn_signin);
        mSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signin = new Intent(LoginActivity.this,SignInActivity.class);
                startActivity(signin);
            }
        });
    }
}
