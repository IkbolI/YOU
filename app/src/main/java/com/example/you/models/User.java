package com.example.you.models;

public class User {

    private String email, imageUrl, name, radioGroup;

    public User (){}

    public User(String email, String imageUrl, String name, String radioGroup) {
        this.email = email;
        this.imageUrl = imageUrl;
        this.name = name;
        this.radioGroup = radioGroup;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRadioGroup() {
        return radioGroup;
    }

    public void setRadioGroup(String radioGroup) {
        this.radioGroup = radioGroup;
    }
}
