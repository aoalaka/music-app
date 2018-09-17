package com.example.android.qplayer;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
        final String songTitleFromSongsActivity = getIntent().getExtras().getString("title");

        TextView titleBar = (TextView) findViewById(R.id.song_title_now_playing);
        titleBar.setText(songTitleFromSongsActivity + " is ready");


        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
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

        final Song songClicked = songs.get(songIdFromSongsActivity);
        mMediaPlayer = MediaPlayer.create(NowPlayingActivity.this, songClicked.getAudioResourceId());
        // Start the audio file
        mMediaPlayer.start();

        Button backToSongsListBtn = (Button) findViewById(R.id.back_to_songs_list);
        backToSongsListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //The code in this method will be executed when the albums View is clicked on.
                Intent backToSongsListIntent = new Intent(NowPlayingActivity.this, SongsActivity.class);
                startActivity(backToSongsListIntent);
            }
        });

        ImageView playPauseIcon = (ImageView) findViewById(R.id.play_pause);
        playPauseIcon.setImageResource(R.drawable.icons8_pause_50);


        final int prevSongId;
        final int nextSongId;


        if (songs.get(songIdFromSongsActivity).getTextViewId() > 0) {
            prevSongId = songIdFromSongsActivity - 1;
        } else {
            prevSongId = songIdFromSongsActivity;
        }
        if (songs.get(songIdFromSongsActivity).getTextViewId() < songs.size() - 1) {
            nextSongId = songIdFromSongsActivity + 1;
        } else {
            nextSongId = songIdFromSongsActivity;
        }

        //Selects the play-pause image
        ImageView playPause = (ImageView) findViewById(R.id.play_pause);

        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView titleBar = (TextView) findViewById(R.id.song_title_now_playing);
                titleBar.setText(songTitleFromSongsActivity + " is now playing");
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
                    /*final Song songClicked = songs.get(songIdFromSongsActivity);
                    mMediaPlayer = MediaPlayer.create(NowPlayingActivity.this, songClicked.getAudioResourceId());*/
                    /*// Start the audio file
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);*/

                    //Not working yet
                    if (mMediaPlayer != null && mMediaPlayer.isPlaying()){
                        mMediaPlayer.pause();
//                        mMediaPlayer.seekTo(0);
                        ImageView playPauseIconX = (ImageView) findViewById(R.id.play_pause);
                        playPauseIconX.setImageResource(R.drawable.icons8_play_50);
                    } else if (mMediaPlayer != null && !(mMediaPlayer.isPlaying())){
                        mMediaPlayer.start();
                        ImageView playPauseIcon = (ImageView) findViewById(R.id.play_pause);
                        playPauseIcon.setImageResource(R.drawable.icons8_pause_50);
                    }
                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    //mMediaPlayer.setOnCompletionListener(mCompletionListener);
                    if(mMediaPlayer!=null){
                        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                String songTitle = songClicked.getSongTitle().toUpperCase();
                                Toast.makeText(NowPlayingActivity.this, songTitle + " has finished playing", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });

        //Select the previous_audio icon
        ImageView prevIcon = (ImageView) findViewById(R.id.previous);


        prevIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(songs.get(songIdFromSongsActivity).getTextViewId() > 0)){
                    Toast.makeText(NowPlayingActivity.this, "You clicked the first file on the list", Toast.LENGTH_SHORT).show();
                }
                    TextView titleBar = (TextView) findViewById(R.id.song_title_now_playing);
                String currentSongId = songs.get(prevSongId).getSongTitle();
                titleBar.setText(currentSongId + " is now playing");
                // Release the media player if                   it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();


                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.
                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the song clicked
                    final Song songClicked = songs.get(prevSongId);
                    mMediaPlayer = MediaPlayer.create(NowPlayingActivity.this, songClicked.getAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    //mMediaPlayer.setOnCompletionListener(mCompletionListener);
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            String songTitle = songClicked.getSongTitle().toUpperCase();
                            Toast.makeText(NowPlayingActivity.this, songTitle + " has finished playing", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        //Select the next_audio icon
        ImageView nextIcon = (ImageView) findViewById(R.id.next);


        nextIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(songs.get(songIdFromSongsActivity).getTextViewId() < songs.size() - 1)){
                    Toast.makeText(NowPlayingActivity.this, "You clicked the last file on the list", Toast.LENGTH_SHORT).show();
                }

                    TextView titleBar = (TextView) findViewById(R.id.song_title_now_playing);
                String currentSongId = songs.get(nextSongId).getSongTitle();
                titleBar.setText(currentSongId + " is now playing");
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
                    final Song songClicked = songs.get(nextSongId);
                    mMediaPlayer = MediaPlayer.create(NowPlayingActivity.this, songClicked.getAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    //mMediaPlayer.setOnCompletionListener(mCompletionListener);
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            String songTitle = songClicked.getSongTitle().toUpperCase();
                            Toast.makeText(NowPlayingActivity.this, songTitle + " has finished playing", Toast.LENGTH_SHORT).show();
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

