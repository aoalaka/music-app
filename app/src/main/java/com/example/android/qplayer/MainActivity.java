package com.example.android.qplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find the View that shows the artists category
        TextView artists = (TextView) findViewById(R.id.artists);
        //Set a click listener on that View
        artists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //The code in this method will be executed when the artists View is clicked on.
                Intent artistsIntent = new Intent(MainActivity.this, ArtistsActivity.class);
                startActivity(artistsIntent);
            }
        });

        //Find the View that shows the albums category
        TextView albums = (TextView) findViewById(R.id.albums);
        //Set a click listener on that View
        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //The code in this method will be executed when the albums View is clicked on.
                Intent albumsIntent = new Intent(MainActivity.this, AlbumsActivity.class);
                startActivity(albumsIntent);
            }
        });

        //Find the View that shows the songs category
        TextView songs = (TextView) findViewById(R.id.songs);
        //Set a click listener on that View
        songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //The code in this method will be executed when the songs View is clicked on.
                Intent songsIntent = new Intent(MainActivity.this, SongsActivity.class);
                startActivity(songsIntent);
            }
        });

        //Find the View that shows the albums category
        TextView playlists = (TextView) findViewById(R.id.playlists);
        //Set a click listener on that View
        playlists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //The code in this method will be executed when the albums View is clicked on.
                Intent playlistsIntent = new Intent(MainActivity.this, PlaylistsActivity.class);
                startActivity(playlistsIntent);
            }
        });

    }
}
