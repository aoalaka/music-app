package com.example.android.qplayer;

import java.util.ArrayList;

public class Song {

    private String mSongTitle;
    private String mArtistName;
    private String mSongLength;
    private String mSource;
    public int mTotalSongs;

    public Song(String songTitle, String artistName, String songLength, String src) {
        mSongTitle = songTitle.replace(" ", "-");
        mSongLength = songLength;
        mSource = src;

        if (!(artistName == null)) {
            mArtistName = artistName;
        } else {
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

    public String getSource() {
        return mSource;
    }

    public void totalSongs(ArrayList<Song> args) {
        int size = args.size();
        int count = 0;
        while (count < size) {
            String firstSongArtist = args.get(count).getArtistName();
            for (int i = 1; i < size; i++) {
                if (firstSongArtist == args.get(i).getArtistName()) {
                    count = count + 1;
                }
            }
        }
    }

    public int getTotalSongs(){
        return mTotalSongs;
    }
}
