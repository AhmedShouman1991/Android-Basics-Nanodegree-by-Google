package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

//    private MediaPlayer MediaPlayer;
//    private AudioManager audioManager;
//    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
//        @Override
//        public void onAudioFocusChange(int i) {
//            if (i == AudioManager.AUDIOFOCUS_GAIN) {
//                MediaPlayer.start();
//            }
//            else if (i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
//                MediaPlayer.pause();
//                MediaPlayer.seekTo(0);
//            }
//            else if (i == AudioManager.AUDIOFOCUS_LOSS) {
//                releaseMediaPlayer();
//            }
//        }
//    };
//    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
//        @Override
//        public void onCompletion(android.media.MediaPlayer mediaPlayer) {
//            releaseMediaPlayer();
//        }
//    };
//
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        releaseMediaPlayer();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catogry);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ColorFragment())
                .commit();

//        final ArrayList<Words> WordsArrayList = new ArrayList<>();
//        WordsArrayList.add(new Words("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
//        WordsArrayList.add(new Words("green", "chokokki", R.drawable.color_green, R.raw.color_green));
//        WordsArrayList.add(new Words("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
//        WordsArrayList.add(new Words("black", "kululli", R.drawable.color_black, R.raw.color_black));
//        WordsArrayList.add(new Words("white", "kelelli", R.drawable.color_white, R.raw.color_white));
//        WordsArrayList.add(new Words("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
//        WordsArrayList.add(new Words("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
//        WordsArrayList.add(new Words("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
//
//
//        //int colorResourceID = getResources().getColor(R.color.category_colors);
//        WordAdapter itemsAdapter =
//                new WordAdapter(this,WordsArrayList, R.color.category_colors);
//
//
//        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
//        // There should be a {@link ListView} with the view ID called list, which is declared in the
//        // words_listayout file.
//        ListView listView = (ListView) findViewById(R.id.list);
//
//        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
//        // {@link ListView} will display list items for each word in the list of words.
//        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
//        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
//        listView.setAdapter(itemsAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//              Words words = WordsArrayList.get(i);
//              releaseMediaPlayer();
//
//              audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//              int result = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
//
//              if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//                  MediaPlayer = MediaPlayer.create(ColorsActivity.this, words.getSoundResourceID());
//                  MediaPlayer.start();
//                  MediaPlayer.setOnCompletionListener(onCompletionListener);
//              }
//            }
//        });
//
//    }
//    private void releaseMediaPlayer() {
//        // If the media player is not null, then it may be currently playing a sound.
//        if (MediaPlayer != null) {
//            // Regardless of the current state of the media player, release its resources
//            // because we no longer need it.
//            MediaPlayer.release();
//
//            // Set the media player back to null. For our code, we've decided that
//            // setting the media player to null is an easy way to tell that the media player
//            // is not configured to play an audio file at the moment.
//            MediaPlayer = null;
//            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
//        }
//    }
    }
}
