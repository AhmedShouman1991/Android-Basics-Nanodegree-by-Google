package com.example.mymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MusicInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String songName = getIntent().getStringExtra("SONG_NAME");
        TextView songText = findViewById(R.id.getSongName);
        songText.setText(songName);


        String artistName = getIntent().getStringExtra("ARTIST");
        TextView artistText = findViewById(R.id.getArtistName);
        artistText.setText(artistName);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int id = bundle.getInt("img");
            ImageView image = findViewById(R.id.getImage);
            image.setImageResource(id);
        }
    }
}
