package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
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

        words.add(new Word("where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word("what is your name?", "tinna oyaasina", R.raw.phrase_what_is_your_name));
        words.add(new Word("my name is", "oyyasit", R.raw.phrase_my_name_is));
        words.add(new Word("how are your feeling", "michaksas", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("i'm feeling good", "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word("are your coming", "aanas'aa", R.raw.phrase_are_you_coming));
        words.add(new Word("yes, i am coming", "ha'aanam", R.raw.phrase_yes_im_coming));
        words.add(new Word("i am coming", "aanam", R.raw.phrase_im_coming));


        WordAdapter Adapter = new WordAdapter(this, words, R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.list);
        assert listView != null;
        listView.setAdapter(Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

                Word word = words.get(i);   // getting the position of media files

                releaseMediaPlayerResource(); // for releasing the resource if the new song is played before the completion of the previous song.

                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getmAudioResourceId());
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