package com.example.player;

public class music {
    private String path;
    private String name;
    private String album;
    private String artist;
    private int musicId;
    private int duration;
    public music(){}
    public music(String path,String name,String album,String artist,int musicId,int duration){
        this.album=album;
        this.artist=artist;
        this.duration=duration;
        this.musicId=musicId;
        this.name=name;
        this.path=path;
    }

    public String getPath() {
        return path;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getMusicId() {
        return musicId;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
