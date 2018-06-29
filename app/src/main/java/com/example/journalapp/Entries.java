package com.example.journalapp;

public class Entries {
    String entry_id;
    String entry_name;
    String entry_thougts;
    /*String entry_date;*/

    public Entries(){

    }

    public Entries(String entry_id, String entry_name, String entry_thougts) {
        this.entry_id = entry_id;
        this.entry_name = entry_name;
        this.entry_thougts = entry_thougts;
    }

    public String getEntry_id() {
        return entry_id;
    }

    public String getEntry_name() {
        return entry_name;
    }

    public String getEntry_thougts() {
        return entry_thougts;
    }



}
