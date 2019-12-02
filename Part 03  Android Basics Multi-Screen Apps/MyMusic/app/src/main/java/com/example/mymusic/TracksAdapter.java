package com.example.mymusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TracksAdapter extends ArrayAdapter {

    ArrayList<Track> tracks;

    public TracksAdapter(@NonNull Context context, ArrayList<Track> tracks) {
        super(context, 0);
        this.tracks = tracks;
    }

    @Override
    public int getCount() {
        return tracks.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null)
            v = LayoutInflater.from(getContext()).inflate(R.layout.item, null);
        TextView title = v.findViewById(R.id.song_name);
        TextView artist = v.findViewById(R.id.by);
        ImageView img = v.findViewById(R.id.img);
        title.setText(tracks.get(position).getTitle());
        artist.setText(tracks.get(position).getArtist());
        img.setImageResource(tracks.get(position).getImgId());

        return v;
    }
}