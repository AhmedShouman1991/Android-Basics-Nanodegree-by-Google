package com.example.alexandriatourguidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ViewFlipper flipper;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flipper = findViewById(R.id.flipper);
        button = findViewById(R.id.btn);
        flipperImg();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent);
                finish();
            }
        });


//        String[] imgArray = {"Stanly Bridge in Alexandria", "ancient monuments amongst modern Alexandria", "Alexandria University", "Montazah Palace"};
//
//        Map<String, Integer> mainView = new HashMap<>();
//        mainView.put("Stanly Bridge in Alexandria", R.drawable.img1);
//        mainView.put("ancient monuments amongst modern Alexandria", R.drawable.img3);
//        mainView.put("Alexandria University", R.drawable.img2);
//        mainView.put("Montazah Palace", R.drawable.img4);


    }

    private void flipperImg() {

        flipper.setAutoStart(true);
        flipper.setFlipInterval(2000);
        flipper.setInAnimation(this, android.R.anim.fade_in);

    }
}
