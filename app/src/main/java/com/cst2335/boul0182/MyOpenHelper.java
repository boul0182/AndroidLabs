package com.cst2335.boul0182;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

    public static final String filename = "ConversationDatabase";
    public static final int version = 1;
    public static final String TABLE_NAME = "MessagesTable";
    public static final String COL_ID = "_id";
    public static final String COL_MESSAGE = "Message";
    public static final String COL_SEND_RECEIVE = "SendOrReceive";

    public MyOpenHelper(Context context) {
        super(context, filename, null, version);
    }

    // should be the creation statement
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table MessagesTable ( _id INTEGER PRIMARY KEY AUTOINCREMENT, Message TEXT, SendOrReceive INTEGER );
        String result = String.format(" %s %s", "FirstString" , "10" );

        db.execSQL( String.format( "Create table %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s  INTEGER );"
                , TABLE_NAME, COL_ID, COL_MESSAGE, COL_SEND_RECEIVE ) );
    }

    // delete current table, create a new one
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "Drop table if exists " + TABLE_NAME ); //deletes the current data
        //create a new table:

        this.onCreate(db); //calls function on line 25
    }
}
