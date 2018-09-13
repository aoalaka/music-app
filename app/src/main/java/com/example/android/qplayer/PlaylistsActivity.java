package com.example.android.qplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PlaylistsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songs_list);

        ArrayList<String> playlist = new ArrayList<String>();

        playlist.add("Recently added");
        playlist.add("My recordings");

        ArrayAdapter<String> playListAdapter = new ArrayAdapter<String>(this, R.layout.list_item_1, playlist);

        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(playListAdapter);
    }
}
