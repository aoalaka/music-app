package com.example.android.qplayer;

public class Song {

    private String mSongTitle;
    private String mArtistName;
    private String mSongLength;
    private String mSource;

    public Song(String songTitle, String artistName, String songLength, String src) {
        mSongTitle = songTitle.replace(" ", "-");
        mSongLength = songLength;
        mSource = src;

        if (!(artistName == null)) {
            mArtistName =  artistName;
        }
        else {
            mArtistName = "Unknown artist";
        }
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
