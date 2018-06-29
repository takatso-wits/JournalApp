package com.example.journalapp;

public class Users {

    String user_id;
    String entryName;
    String thoughts;

    public Users(){

    }

    public Users(String user_id, String entryName, String thoughts) {
        this.user_id = user_id;
        this.entryName = entryName;
        this.thoughts = thoughts;

    }

    public String getUser_id() {
        return user_id;
    }

    public String getEntryName() {
        return entryName;
    }

    public String getThoughts() {
        return thoughts;
    }
}
