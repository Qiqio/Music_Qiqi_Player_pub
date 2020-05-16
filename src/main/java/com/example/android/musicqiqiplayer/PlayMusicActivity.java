package com.example.android.musicqiqiplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class PlayMusicActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer = null;

    ImageButton play_pause_button;
    ImageButton previous_button;
    ImageButton next_button;
    TextView song_title_textView;

    SeekBar seekBar;

    SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser){ //if not from user, this is fired by code, so do nothing
                mediaPlayer.seekTo(progress);
                seekBar.setProgress(progress);
            }
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };


    View.OnClickListener play_pause_OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mediaPlayer!= null) {
                if (mediaPlayer.isPlaying() == true) {
                    mediaPlayer.pause();
                    play_pause_button.setImageResource(android.R.drawable.ic_media_play);
                } else {
                    mediaPlayer.start();
                    play_pause_button.setImageResource(android.R.drawable.ic_media_pause);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        play_pause_button = findViewById(R.id.play_play_pause_button);
        previous_button = findViewById(R.id.play_backward_button);
        next_button  = findViewById(R.id.play_forward_button);
        song_title_textView = findViewById(R.id.songTitle);
        seekBar = findViewById(R.id.seekBar);

        Intent intent = getIntent();
        int songResId = intent.getIntExtra("songResId",0);

        if(mediaPlayer!= null && mediaPlayer.isPlaying() == true)
            mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(this, songResId);
        mediaPlayer.start();

        //set title
        //ToDo: set to retrieved song title
        //set onClickListener
        play_pause_button.setOnClickListener(play_pause_OnClickListener);

        //set SeekbarListener
        seekBar.setMax(mediaPlayer.getDuration()); //to get related to the progress
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //if(mediaPlayer!= null)
          //  mediaPlayer.release();
    }
}
