package com.example.android.qplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NowPlayingActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the song from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

       final int songIdFromSongsActivity = getIntent().getExtras().getInt("position");
       String songTitleFromSongsActivity = getIntent().getExtras().getString("title");

       TextView titleBar = (TextView) findViewById(R.id.song_title_now_playing);
       titleBar.setText(songTitleFromSongsActivity + " is now playing");


        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
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

        ImageView playPauseArrow = (ImageView) findViewById(R.id.play_pause);

        playPauseArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v) {
                // Release the media player if                   it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

               /* // Get the {@link Song} object at the given position the user clicked on
                Song song = songs.get(position);*/

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.
                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the song clicked
                    final Song songClicked = songs.get(songIdFromSongsActivity);
                    mMediaPlayer = MediaPlayer.create(NowPlayingActivity.this, songClicked.getAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    //mMediaPlayer.setOnCompletionListener(mCompletionListener);
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            String songTitle = songClicked.getSongTitle().toUpperCase();
                            Toast.makeText(NowPlayingActivity.this, "The song " + songTitle + " is finished", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

}

