package com.example.mymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BuySong extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_song);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String songName = getIntent().getStringExtra("Online_SONG_NAME");
        TextView songText = findViewById(R.id.getSongName);
        songText.setText(songName);


        String artistName = getIntent().getStringExtra("Online_ARTIST");
        TextView artistText = findViewById(R.id.getArtistName);
        artistText.setText(artistName);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int id = bundle.getInt("Online_img");
            ImageView image = findViewById(R.id.getAlbumImg);
            image.setImageResource(id);
        }
        Button button = findViewById(R.id.buySong);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BuySong.this, "downloading song", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
