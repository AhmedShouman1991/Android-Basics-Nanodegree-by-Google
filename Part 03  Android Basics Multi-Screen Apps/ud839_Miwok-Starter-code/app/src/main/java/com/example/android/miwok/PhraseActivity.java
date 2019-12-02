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

public class PhraseActivity extends AppCompatActivity {

//    private MediaPlayer mMediaPlayer;
//    private AudioManager audioManager;
//    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
//        @Override
//        public void onAudioFocusChange(int i) {
//            if (i == AudioManager.AUDIOFOCUS_GAIN) {
//                mMediaPlayer.start();
//            }
//            else if (i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
//                mMediaPlayer.pause();
//                mMediaPlayer.seekTo(0);
//            }
//            else if (i == AudioManager.AUDIOFOCUS_LOSS) {
//                releaseMediaPlayer();
//            }
//        }
//    };
//    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
//        @Override
//        public void onCompletion(MediaPlayer mediaPlayer) {
//            releaseMediaPlayer();
//        }
//    };
//
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
                .replace(R.id.container, new PhraseFragment())
                .commit();
    }
        //        final ArrayList<Words> wordsArrayList = new ArrayList<>();
//        wordsArrayList.add(new Words("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
//        wordsArrayList.add(new Words("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
//        wordsArrayList.add(new Words("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
//        wordsArrayList.add(new Words("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
//        wordsArrayList.add(new Words("I’mMediaPlayer feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
//        wordsArrayList.add(new Words("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
//        wordsArrayList.add(new Words("Yes, I’mMediaPlayer coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
//        wordsArrayList.add(new Words("I’mMediaPlayer coming", "әәnәm", R.raw.phrase_im_coming));
//        wordsArrayList.add(new Words("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
//        wordsArrayList.add(new Words("Come here", "әnni'nem", R.raw.phrase_come_here));
//
//
//        //int colorResourceID = getResources().getColor(R.color.category_phrases);
//
//
//        WordAdapter itemsAdapter =
//                new WordAdapter(this,wordsArrayList, R.color.category_phrases);
//
//
//        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
//        // There should be a {@link ListView} with the view ID called list, which is declared in the
//        // activity_numbers.xml layout file.
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
//
//                releaseMediaPlayer();
//
//                audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
//
//                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//                    mMediaPlayer = MediaPlayer.create(PhraseActivity.this, words.getSoundResourceID());
//                    mMediaPlayer.start();
//                    mMediaPlayer.setOnCompletionListener(onCompletionListener);
//                }
//            }
//        });
//
//    }
//
//    private void releaseMediaPlayer() {
//        // If the media player is not null, then it may be currently playing a sound.
//        if (mMediaPlayer != null) {
//            // Regardless of the current state of the media player, release its resources
//            // because we no longer need it.
//            mMediaPlayer.release();
//
//            // Set the media player back to null. For our code, we've decided that
//            // setting the media player to null is an easy way to tell that the media player
//            // is not configured to play an audio file at the moment.
//            mMediaPlayer = null;
//            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
//        }
//    }

}
