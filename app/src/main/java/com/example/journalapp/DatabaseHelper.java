package com.example.journalapp;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.text.SimpleDateFormat;

public class DatabaseHelper extends SQLiteOpenHelper{

    private String DB_PATH;
    private String DB_NAME;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");





    public DatabaseHelper(Context context, String name) {
        super(context, name+".db", null, 1);
        DB_NAME = name + ".db";
    }

    public String getCurrentTime(){
        String time;
        time = simpleDateFormat.format(new java.util.Date());
        return time;
    }

    public Cursor doQuery(String sql, String[] params){

        try {
            Cursor cursor = getReadableDatabase().rawQuery(sql, params);
            return cursor;
        } catch (SQLException mSQLException) {
            System.err.println("-- doQuery --\n"+sql);
            mSQLException.printStackTrace(System.err);
            return null;
        }
    }
    public Cursor doQuery(String sql){

        try {
            Cursor cursor = getReadableDatabase().rawQuery(sql,null);
            return cursor;
        } catch (SQLException mSQLException) {
            System.err.println("-- doQuery --\n"+sql);
            mSQLException.printStackTrace(System.err);
            return null;
        }
    }

    public void doUpdate(String sql, String[] params){

        try {
                getReadableDatabase().execSQL(sql, params);
        } catch (SQLException mSQLException) {
            System.err.println("-- doUpdate --\n"+sql);
            mSQLException.printStackTrace(System.err);
        }
    }
    public void doUpdate(String sql){

        try {
            getReadableDatabase().execSQL(sql);
        } catch (SQLException mSQLException) {
            System.err.println("-- doUpdate --\n"+sql);
            mSQLException.printStackTrace(System.err);
        }
    }

    public long getSize(){
        final SQLiteDatabase db = getReadableDatabase();

        /*Get the information inside the database*/
        final String path = db.getPath();
        final File file = new File(path);
        final long fileLength = file.length();

        return fileLength;


    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE entries(id int PRIMARY KEY," +
                "date text,entry_name text, thoughts text)";

        String currentTime = getCurrentTime();
        String sampleData = "INSERT INTO entries(date,entry_name,thoughts)" +
                " VALUES(currentTime,'Hulk','Strongest Avenger')";
        db.execSQL(createTable);
        db.execSQL(sampleData);
    }

    SQLiteDatabase getDB(){
        return getWritableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);

    }
}
