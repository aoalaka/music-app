package com.example.android.qplayer;

public class Song {

    private String mSongTitle;
    private String mArtistName;

    private int mAudioResourceId;
    private int mTextViewId;
    /**
     * Constant value that represents no image was provided for this word
     */
    private static final int NO_IMAGE_PROVIDED = -1;

    private int mImageResourceId = NO_IMAGE_PROVIDED;


    public Song(String songTitle, String artistName) {
        mSongTitle = songTitle.replace(" ", "-");


        if (!(artistName == null)) {
            mArtistName = artistName;
        } else {
            mArtistName = "Unknown artist";
        }
    }

    public Song(String SongTitle, String artistName, int ImageResourceId, int AudioResourceId) {
        mSongTitle = SongTitle.replace(" ", "-");
        if (!(artistName == null)) {
            mArtistName = artistName;
        } else {
            mArtistName = "Unknown artist";
        }
        mImageResourceId = ImageResourceId;
        mAudioResourceId = AudioResourceId;
    }

    public Song(String SongTitle, int ImageResourceId, int AudioResourceId, int textViewId) {
        mSongTitle = SongTitle.replace(" ", "-");
        mImageResourceId = ImageResourceId;
        mAudioResourceId = AudioResourceId;
        mTextViewId =textViewId;
    }


    public String getArtistName() {
        return mArtistName;
    }

    public String getSongTitle() {
        return mSongTitle;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public int getTextViewId() {
        return mTextViewId;
    }

    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

}
