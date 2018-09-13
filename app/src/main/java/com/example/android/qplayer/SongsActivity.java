package com.example.android.qplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class SongsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songs_list);

        ArrayList<Song> songs = new ArrayList<Song>();

        songs.add(new Song ("tafseeru suuratil faatiha", " Shaykh (Dr) Alaro", "30:55", "download"));
        songs.add(new Song ("tafseeru suuratil falaq", " Shaykh (Dr) Sekoni", "22:55", "bluetooth"));
        songs.add(new Song ("tafseeru suuratil Hujuraat", " Shaykh Sulaimon Amubieya", "50:53", "bluetooth"));
        songs.add(new Song ("tafseeru suuratil bayyinah", " Shaykh Isma'eel Busaery", "33:55", "download"));
        songs.add(new Song ("Islam & Knowledge", " Shaykh Rasheed Ath-thaqaafy", "50:55", "recordings"));
        songs.add(new Song ("Kindness to parents", "Dr Mufti Menk", "1:30:55", "WhatsApp audio"));
        songs.add(new Song ("سورة البقرة", "منشارى العفاسى", "1:10:15", "download"));
        songs.add(new Song ("سورة الكوثر", "الشيخ السديس", "01:01", "download"));
        songs.add(new Song ("The rights of spouses on one another", null, "54:55", "bluetooth"));
        songs.add(new Song ("Musim Youths and Challenges of Late Marriage", " Shaykh (Dr) Alaro", "1:30:55", "download" ));

        SongAdapter songAdapter = new SongAdapter(this, songs);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(songAdapter);
    }
}
