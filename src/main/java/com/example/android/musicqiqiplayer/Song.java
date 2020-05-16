package com.example.android.musicqiqiplayer;

public class Song {
    private String songTitle;
    private int songResId;
    private int songDuration_s;

    public Song(String songTitle, int songResId){
        this.songTitle = songTitle;
        this.songResId = songResId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public int getSongDuration_s() {
        return songDuration_s;
    }

    public int getSongResId() {
        return songResId;
    }
}
