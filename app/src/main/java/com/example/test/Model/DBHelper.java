package com.example.test.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "userdatabase.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String qr = "CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,username TEXT,email TEXT,password TEXT,dob TEXT)";
        db.execSQL(qr);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insertUser(TestModel testModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String qr = "SELECT * FROM user WHERE username = ?";
        Cursor cursor = db.rawQuery(qr, new String[]{ testModel.getUsername() });
        if (cursor.moveToFirst()){
            cursor.close();
            return false;
        }else {
            try{
                ContentValues cv = new ContentValues();
                cv.put("name",testModel.getName());
                cv.put("username",testModel.getUsername());
                cv.put("email",testModel.getEmail());
                cv.put("password",testModel.getPassword());
                cv.put("dob",testModel.getDob());
                long result = db.insert("user", null, cv);
                db.close();
                if(result == -1){
                    return false;
                }
            }finally {

                return true;
            }

        }




    }
    public boolean existingUser(String username, String password){
        String qr = "SELECT * FROM user WHERE username = ? AND password = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(qr,new String[]{username,password});
        if (cursor.moveToNext()){
            return true;
        }else {
            return false;
        }
    }

    public List<TestModel> getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<TestModel> userList = new ArrayList<>();
        try {
            String qr = "SELECT * FROM user;";
            Cursor cursor = db.rawQuery(qr,null);
            if (cursor.moveToFirst()){
                do {
                    TestModel testModel = new TestModel(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
                    userList.add(testModel);
                }while (cursor.moveToNext());
            }

        }finally {
            return userList;

        }



    }
    public boolean deleteUser(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String qr = "DELETE FROM user WHERE id = "+id;
        Cursor cursor = db.rawQuery(qr, null);
        if(cursor.moveToFirst()){
            return true;
        }else {
            return false;
        }
    }
}



