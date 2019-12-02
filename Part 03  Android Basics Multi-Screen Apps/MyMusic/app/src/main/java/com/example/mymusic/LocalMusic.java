package com.example.mymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LocalMusic extends AppCompatActivity {
    ArrayList<Track> trackArrayList;
    ListView songsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        trackArrayList = new ArrayList<>();
        trackArrayList.add(new Track(getString(R.string.local_s1_name), getString(R.string.local_a1_name), R.drawable.solodance));
        trackArrayList.add(new Track(getString(R.string.local_s2_name), getString(R.string.local_a2_name), R.drawable.hrarth2));
        songsList = findViewById(R.id.songs_list);
        TracksAdapter adapter = new TracksAdapter(this, trackArrayList);
        songsList.setAdapter(adapter);
        songsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("click", "clicked");
                Intent intent = new Intent(LocalMusic.this, MusicPlayer.class);
                Track track = trackArrayList.get(position);
                intent.putExtra("Player_SONG_NAME",track.getTitle());
                intent.putExtra("Player_ARTIST", track.getArtist());
                intent.putExtra("Player_img", track.getImgId());
                startActivity(intent);
            }
        });
        songsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("Long_click", "clicked");
                Intent intent = new Intent(LocalMusic.this, MusicInfo.class);
                Track track = trackArrayList.get(position);
                intent.putExtra("SONG_NAME",track.getTitle());
                intent.putExtra("ARTIST", track.getArtist());
                intent.putExtra("img", track.getImgId());
                startActivity(intent);
                return true;
            }
        });
    }
}
