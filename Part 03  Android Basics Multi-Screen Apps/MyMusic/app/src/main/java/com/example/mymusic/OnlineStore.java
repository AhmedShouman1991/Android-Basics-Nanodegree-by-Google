package com.example.mymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class OnlineStore extends AppCompatActivity {

    ArrayList<Track> trackArrayList;
    ListView onlineList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_store);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        trackArrayList = new ArrayList<>();
        trackArrayList.add(new Track(getString(R.string.local_s1_name), getString(R.string.local_a1_name), R.drawable.solodance));
        trackArrayList.add(new Track(getString(R.string.local_s2_name), getString(R.string.local_a2_name), R.drawable.hrarth2));
        onlineList = findViewById(R.id.online_list);
        TracksAdapter adapter = new TracksAdapter(this, trackArrayList);
        onlineList.setAdapter(adapter);
        onlineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OnlineStore.this, BuySong.class);
                Track track = trackArrayList.get(position);
                intent.putExtra("Online_SONG_NAME",track.getTitle());
                intent.putExtra("Online_ARTIST", track.getArtist());
                intent.putExtra("Online_img", track.getImgId());
                startActivity(intent);
            }
        });
        onlineList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OnlineStore.this, MusicInfo.class);
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
