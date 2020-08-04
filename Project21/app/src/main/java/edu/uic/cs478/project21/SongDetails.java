package edu.uic.cs478.project21;

/**
 * Created by Heeba on 2/17/16.
 */
public class SongDetails {
    String titleName;
    String artist;
    String videoCode="";

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitleName() {

        return titleName;
    }

    public SongDetails(String titleName,String artist,String videoCode) {
        this.titleName = titleName;
        this.artist=artist;
        this.videoCode=videoCode;
    }

    public void videoCode(String videoCode) {
        this.videoCode = videoCode;
    }

    public String getVideoCode() {

        return videoCode;
    }

    public String getArtist() {
        return artist;
    }
}
