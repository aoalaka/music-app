package com.example.android.qplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SongsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songs_list);

        final ArrayList<Song> songs = new ArrayList<Song>();

        songs.add(new Song("Qur'an 105", R.drawable.ic_play_arrow_white_24dp, R.raw.quran_105, 1));
        songs.add(new Song("Qur'an 106", R.drawable.ic_play_arrow_white_24dp, R.raw.quran_106, 2));
        songs.add(new Song("Qur'an 107", R.drawable.ic_play_arrow_white_24dp, R.raw.quran_107, 3));
        songs.add(new Song("Qur'an 108", R.drawable.ic_play_arrow_white_24dp, R.raw.quran_108, 4));
        songs.add(new Song("Qur'an 109", R.drawable.ic_play_arrow_white_24dp, R.raw.quran_109, 5));
        songs.add(new Song("Qur'an 110", R.drawable.ic_play_arrow_white_24dp, R.raw.quran_110, 6));
        songs.add(new Song("Qur'an 111", R.drawable.ic_play_arrow_white_24dp, R.raw.quran_111, 7));
        songs.add(new Song("Qur'an 112", R.drawable.ic_play_arrow_white_24dp, R.raw.quran_112, 8));
        songs.add(new Song("Qur'an 113", R.drawable.ic_play_arrow_white_24dp, R.raw.quran_113, 9));
        songs.add(new Song("Qur'an 114", R.drawable.ic_play_arrow_white_24dp, R.raw.quran_114, 10));


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

                Intent nowPlayingIntent = new Intent(SongsActivity.this, NowPlayingActivity.class);
                nowPlayingIntent.putExtra("position", songId);
                nowPlayingIntent.putExtra("title", songTitle);
                startActivity(nowPlayingIntent);
            }
        });
    }


}

