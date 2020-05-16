package com.example.android.musicqiqiplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class LocalMusicView extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    MediaPlayer lastMP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music_view);

        // create list of local songs
        final ArrayList<Song> songList = new ArrayList<Song>();
        songList.add(new Song("Flower", R.raw.flower));
        songList.add(new Song("静止", R.raw.jing_zhi));
        songList.add(new Song("Norway Forest", R.raw.norway_forest));
        songList.add(new Song("泡沫", R.raw.pao_mo));
        songList.add(new Song("意外", R.raw.yi_wai));
        songList.add(new Song("Melting", R.raw.melting));


        SongAdapter itemAdapter = new SongAdapter(this, songList);
        ListView listView = findViewById(R.id.local_music_list_view);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(lastMP!= null && lastMP.isPlaying() == true)
                    lastMP.stop();
                if(mediaPlayer!= null && mediaPlayer.isPlaying() == true)
                    mediaPlayer.stop();
                Song currentSong = songList.get(position);
                mediaPlayer = MediaPlayer.create(LocalMusicView.this, currentSong.getSongResId());
                mediaPlayer.start();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mediaPlayer != null){
            lastMP = mediaPlayer;
        }
/*
        if (mediaPlayer != null) {
            resumedActivity = true;
            pausedMilliSec = mediaPlayer.getCurrentPosition();
        } */
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //never comes here?!
    }
}
