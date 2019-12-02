package com.example.android.miwok;

import android.app.Activity;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;


public class WordAdapter extends ArrayAdapter<Words> {

    private int colorResourceID;
    private MediaPlayer m;
    public WordAdapter(@NonNull Activity context, ArrayList<Words> words, int colorResourceID) {
        super(context,0, words);
        this.colorResourceID = colorResourceID;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;

        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.item_list_layout,parent , false);
        }

        final Words currentWord = getItem(position);

//        listItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mMediaPlayer = MediaPlayer.create(getContext(), currentWord.getSoundResourceID());
//                mMediaPlayer.start();
//            }
//        });

        TextView miwokTextView = listItem.findViewById(R.id.miwok_word);
        miwokTextView.setText(currentWord.getMiwokWord());

        TextView englishTextView = listItem.findViewById(R.id.english_word);
        englishTextView.setText(currentWord.getEnglishWord());

        RelativeLayout layout = listItem.findViewById(R.id.translation_layout);
        int color = ContextCompat.getColor(getContext(), colorResourceID);
        layout.setBackgroundColor(color);

        ImageView imageView = listItem.findViewById(R.id.img);
        if (currentWord.getImageResourceId() == 0) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(currentWord.getImageResourceId());
        }
        return listItem;
    }
}
