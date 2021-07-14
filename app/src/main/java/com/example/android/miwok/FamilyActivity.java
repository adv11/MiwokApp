package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;

    // creating the global variable for releasing resources
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {

            releaseMediaPlayerResource();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<Word>();

        /* Adding custom lists */
//        Word w = new Word("one", "lutti");
//        words.add(w);

        words.add(new Word("father", "apa", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("mother", "ata", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("elder brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("elder sister", "tete", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("younger sister", "kollete", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("grand mother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("grand father", "apa", R.drawable.family_grandfather, R.raw.family_grandfather));


        WordAdapter Adapter = new WordAdapter(this, words, R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.list);
        assert listView != null;
        listView.setAdapter(Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

                Word word = words.get(i);

                releaseMediaPlayerResource(); // for releasing the resource if the new song is played before the completion of the previous song.

                mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getmAudioResourceId());
                mMediaPlayer.start();

                mMediaPlayer.setOnCompletionListener(mCompletionListener);  // after the completion of the current song it will release the resources
            }
        });
    }

    // If the user played any song and simultaneously minimize our app then the activity(song) should stopped and we have to release the resources for better memory purpose.
    @Override
    protected void onStop() {

        super.onStop();
        releaseMediaPlayerResource();
    }

    // method for releasing the resources
    private void releaseMediaPlayerResource() {

        if (mMediaPlayer != null) {

            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

}