package com.example.commentify.model;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Comment {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username, content;

    //contructor leeg laten,
    public Comment () {
    }

    @Ignore
    public Comment(String user, String content) {
        this.username = user;
        this.content = content;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }





}
