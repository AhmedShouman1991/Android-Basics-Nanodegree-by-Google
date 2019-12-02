package com.example.mymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button localImg = findViewById(R.id.goto_local);
        Intent intent = new Intent(MainActivity.this, LocalMusic.class);
        onClickListener(localImg, intent);

        Button onlineSearch = findViewById(R.id.goto_online_store);
        Intent intent2 = new Intent(MainActivity.this, OnlineStore.class);
        onClickListener(onlineSearch, intent2);
    }

    public void onClickListener(Button button, final Intent intent) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
