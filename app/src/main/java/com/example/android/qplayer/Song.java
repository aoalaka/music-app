package com.example.android.qplayer;

public class Song {

    private String mSongTitle;
    private String mArtistName;
    private String mSongLength;

    public Song(String songTitle, String artistName, String songLength) {
        mSongTitle = songTitle.replace(" ", "-");
        mArtistName = artistName;
        mSongLength = songLength;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public String getSongTitle() {
        return mSongTitle;
    }

    public String getSongLength() {
        return mSongLength;
    }
}
