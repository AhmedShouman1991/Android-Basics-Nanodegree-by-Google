package com.example.mymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MusicPlayer extends AppCompatActivity {
    boolean isPlaying = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String songName = getIntent().getStringExtra("Player_SONG_NAME");
        TextView songText = findViewById(R.id.song);
        songText.setText(songName);


        String artistName = getIntent().getStringExtra("Player_ARTIST");
        TextView artistText = findViewById(R.id.artist);
        artistText.setText(artistName);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int id = bundle.getInt("Player_img");
            ImageView image = findViewById(R.id.profile_image);
            image.setImageResource(id);
        }

        final ImageView playAndPause = findViewById(R.id.play);
        playAndPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    isPlaying = false;
                    playAndPause.setImageResource(R.drawable.play);
                    Toast.makeText(v.getContext(), "Paused", Toast.LENGTH_SHORT).show();
                } else {
                    isPlaying = true;
                    playAndPause.setImageResource(R.drawable.pause);
                    Toast.makeText(v.getContext(), "Playing", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageView rewind = findViewById(R.id.rewind);
        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Rewind", Toast.LENGTH_SHORT).show();

            }
        });

        ImageView forward = findViewById(R.id.forward);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Forward", Toast.LENGTH_SHORT).show();

            }
        });

        ImageView settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Settings", Toast.LENGTH_SHORT).show();

            }
        });

        ImageView replay = findViewById(R.id.replay);
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Replay", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
