package com.example.you.models;

public class CommentClass {

    private String username;
    private String comment;

    public CommentClass(String username, String comment) {
        this.username = username;
        this.comment = comment;
    }

    public  CommentClass () {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
