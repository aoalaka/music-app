package com.example.android.qplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SongsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songs_list);

        final ArrayList<Song> songs = new ArrayList<Song>();

        songs.add(new Song(getString(R.string.song_title_1), R.drawable.ic_play_arrow_white_24dp, R.raw.quran_105, 0));
        songs.add(new Song(getString(R.string.song_title_2), R.drawable.ic_play_arrow_white_24dp, R.raw.quran_106, 1));
        songs.add(new Song(getString(R.string.song_title_3), R.drawable.ic_play_arrow_white_24dp, R.raw.quran_107, 2));
        songs.add(new Song(getString(R.string.song_title_4), R.drawable.ic_play_arrow_white_24dp, R.raw.quran_108, 3));
        songs.add(new Song(getString(R.string.song_title_5), R.drawable.ic_play_arrow_white_24dp, R.raw.quran_109, 4));
        songs.add(new Song(getString(R.string.song_title_6), R.drawable.ic_play_arrow_white_24dp, R.raw.quran_110, 5));
        songs.add(new Song(getString(R.string.song_title_7), R.drawable.ic_play_arrow_white_24dp, R.raw.quran_111, 6));
        songs.add(new Song(getString(R.string.song_title_8), R.drawable.ic_play_arrow_white_24dp, R.raw.quran_112, 7));
        songs.add(new Song(getString(R.string.song_title_9), R.drawable.ic_play_arrow_white_24dp, R.raw.quran_113, 8));
        songs.add(new Song(getString(R.string.song_title_10), R.drawable.ic_play_arrow_white_24dp, R.raw.quran_114, 9));

/*        Button HomeBtn = new Button(this);
        HomeBtn.setId(R.id.back_to_home_from_songs_list);

        Button backToHomeBtn = (Button) findViewById(R.id.back_to_home_from_songs_list);
        backToHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //The code in this method will be executed when the albums View is clicked on.
                Intent backToSongsListIntent = new Intent(SongsActivity.this, MainActivity.class);
                startActivity(backToSongsListIntent);


            }
        });*/


        SongAdapter songAdapter = new SongAdapter(this, songs);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(songAdapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                // Get the {@link Song} object at the given position the user clicked on
                Song song = songs.get(position);
                int songId = song.getTextViewId();
                String songTitle = song.getSongTitle();

                Intent toNowPlayingActivityIntent = new Intent(SongsActivity.this, NowPlayingActivity.class);
                toNowPlayingActivityIntent.putExtra("position", songId);
                toNowPlayingActivityIntent.putExtra("title", songTitle);
                startActivity(toNowPlayingActivityIntent);
            }
        });
    }


}

