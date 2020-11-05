package com.nextgate.assesment.datatypes;

import java.util.Date;

public class Album {

    private String singer;
    private String album;
    private String year;
    private String company;

    public Album(String singer, String album, String year, String company) {
        this.singer = singer;
        this.album = album;
        this.year = year;
        this.company = company;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Album {" +
                "singer='" + singer + '\'' +
                ", album='" + album + '\'' +
                ", year='" + year + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
