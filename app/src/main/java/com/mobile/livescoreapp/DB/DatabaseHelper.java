package com.mobile.livescoreapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

/**
 * Created by JohnDoe on 5/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "livescore_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "HOME";
    private static final String COL_3 = "AWAY";
    private static final String COL_4 = "STATUS";

    public DatabaseHelper(Context context) {
        super(context,TABLE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+TABLE_NAME+ "(ID TEXT PRIMARY KEY,"
                +COL_2 +" TEXT,"
                +COL_3 +" TEXT,"
                +COL_4 +" TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXIST "+ TABLE_NAME);
        onCreate(db);
    }
    public boolean addData(String id,String home,String away,String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,home);
        contentValues.put(COL_3,away);
        contentValues.put(COL_4,status);

        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1){
            return false;
        }else {
            return true;
        }

    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;
        Cursor data = db.rawQuery(query,null);
        return data;
    }
    public void deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "ID = "+id+"";
        db.delete(TABLE_NAME,where,null);
    }
}
