package com.example.yattask;

public class movieitem {
    private int ImageResource;
    private String title;
    private String Key_id;
    private String favStatus;
    public  movieitem(){

    }

    public movieitem(int imageResource, String title, String key_id, String favStatus) {
        ImageResource = imageResource;
        this.title = title;
        Key_id = key_id;
        this.favStatus = favStatus;
    }

    public int getImageResource() {
        return ImageResource;
    }

    public void setImageResource(int imageResource) {
        ImageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey_id() {
        return Key_id;
    }

    public void setKey_id(String key_id) {
        Key_id = key_id;
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }
}
