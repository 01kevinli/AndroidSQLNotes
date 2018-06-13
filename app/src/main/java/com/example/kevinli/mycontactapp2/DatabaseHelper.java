package com.example.kevinli.mycontactapp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.net.IDN;

import static android.os.Build.ID;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Contact2018.db";
    public static final String TABLE_NAME = "Contact2018_table";
    public static final String ID = "ID";
    public static final String COLUMN_NAME_CONTACT = "contact";
    public static final String COLUMN_ADDRESS_CONTACT = "address";
    public static final String COLUMN_PHONENUM_CONTACT = "phonenumber";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME_CONTACT + " TEXT," +
                    COLUMN_ADDRESS_CONTACT + " TEXT," +
                    COLUMN_PHONENUM_CONTACT + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("myContactApp", "DatabaseHelper: constructed DatabaseHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("myContactApp", "DatabaseHelper: creating database");
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    /*
    public void onOpen(SQLiteDatabase db) {
        Log.d("myContactApp", "DatabaseHelper: opening database");
        db.execSQL(SQL_CREATE_ENTRIES);
    }*/


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("myContactApp", "DatabaseHelper: upgrading database");
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    public boolean insertData(String name, String address, String pNum){
        Log.d("myContactApp", "DatabaseHelper: inserting data");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_CONTACT, name);
        Log.d("myContactApp", "DatabaseHelper: inserting name");


        contentValues.put(COLUMN_ADDRESS_CONTACT, address);
        Log.d("myContactApp", "DatabaseHelper: inserting address");
        contentValues.put(COLUMN_PHONENUM_CONTACT, pNum);
        Log.d("myContactApp", "DatabaseHelper: inserting num");

        long result = db.insert(TABLE_NAME, null, contentValues);
        Log.d("myContactApp", "DatabaseHelper: inserted into table");


        if(result == -1){
            Log.d("myContactApp", "DatabaseHelper: Contact insert -- FAILED");
            return false;
        }
        else{
            Log.d("myContactApp", "DatabaseHelper: Contact insert -- PASSED");
            return true;
        }
    }

    public Cursor getAllData(){
        Log.d("myContactApp", "DatabaseHelper: Pulling all records from the database");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

}
