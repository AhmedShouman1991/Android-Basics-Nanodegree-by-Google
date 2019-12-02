package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {




//    private MediaPlayer mMediaPlayer;
//    private AudioManager audioManager;
//    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
//        @Override
//        public void onAudioFocusChange(int i) {
//
//            if (i == AudioManager.AUDIOFOCUS_GAIN) {
//                mMediaPlayer.start();
//
//            } else if (i == AudioManager.AUDIOFOCUS_LOSS) {
//                releaseMediaPlayer();
//
//            } else if (i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
//               mMediaPlayer.pause();
//
//               mMediaPlayer.seekTo(0);
//
//            }
//        }
//    };
//
//
//    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener(){
//
//        @Override
//        public void onCompletion(MediaPlayer mediaPlayer) {
//            releaseMediaPlayer();
//        }
//    };
//
//
//
////    private void audio() {
////        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
////
////
////        int result = audioManager.requestAudioFocus();
////        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
////
////        }
////
////    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        releaseMediaPlayer();
//    }
//
//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catogry);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new NumbersFragment())
                .commit();
    }
//
//        final ArrayList<Words> wordsArrayList = new ArrayList<>();
//        wordsArrayList.add(new Words("one", "lutti", R.drawable.number_one, R.raw.number_one));
//        wordsArrayList.add(new Words("two", "otiiko", R.drawable.number_two, R.raw.number_two));
//        wordsArrayList.add(new Words("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
//        wordsArrayList.add(new Words("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
//        wordsArrayList.add(new Words("five", "massokka", R.drawable.number_five, R.raw.number_five));
//        wordsArrayList.add(new Words("six", "temmokka", R.drawable.number_six, R.raw.number_six));
//        wordsArrayList.add(new Words("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
//        wordsArrayList.add(new Words("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
//        wordsArrayList.add(new Words("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
//        wordsArrayList.add(new Words("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));
//
//
//        //int colorResourceID = getResources().getColor(R.color.category_numbers);
//        WordAdapter itemsAdapter =
//                new WordAdapter(this,wordsArrayList, R.color.category_numbers);
//
//
//        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
//        // There should be a {@link ListView} with the view ID called list, which is declared in the
//        // words_list.xmlt file.
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
//                Words words = wordsArrayList.get(i);
//                releaseMediaPlayer();
//
//                audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
//
//                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//
//                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, words.getSoundResourceID());
//                    mMediaPlayer.start();
//
//                    mMediaPlayer.setOnCompletionListener(onCompletionListener);
//                }
//            }
//        });
//    }

//    private void releaseMediaPlayer() {
//        // If the media player is not null, then it may be currently playing a sound.
//        if (mMediaPlayer != null) {
//            // Regardless of the current state of the media player, release its resources
//            // because we no longer need it.
//            mMediaPlayer.release();
//
//            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
//
//            // Set the media player back to null. For our code, we've decided that
//            // setting the media player to null is an easy way to tell that the media player
//            // is not configured to play an audio file at the moment.
//            mMediaPlayer = null;
//        }
//    }
}
