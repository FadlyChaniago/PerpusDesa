package com.example.perpusdesa.model;

public class PepusModel {
    private String id;
    private String url;
    private String title;

    public PepusModel(String title, String image, String id) {
        this.title = title;
        this.url = image;
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return url;
    }

    public void setImage(String image) {
        this.url = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PepusModel{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
