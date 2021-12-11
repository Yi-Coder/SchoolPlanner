package com.example.schoolplanner.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.ArrayAdapter;

import androidx.annotation.RequiresApi;

import com.example.schoolplanner.Model.Subject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SubjectManager";
    private static final String TABLE_Subject = "Subject";
    private static final String KEY_NAME = "name";
    private static final String KEY_TEACHER = "teacher";
    private static final String KEY_IMGID = "imgId";
    private static final String KEY_TOOLS = "tools";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        context.deleteDatabase(DATABASE_NAME);
        //3rd argument to be passed is CursorFactory instance
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Subject);;

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_Subject + "("
                + KEY_NAME + " STRING PRIMARY KEY,"
                + KEY_TEACHER + " TEXT,"
                + KEY_IMGID + " INTEGER,"
                + KEY_TOOLS + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Subject);

        // Create tables again
        onCreate(db);
    }


    public void addSubject(Subject subject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, subject.getName());
        values.put(KEY_IMGID, subject.getImgId());
        values.put(KEY_TEACHER, subject.getTeacher());
        values.put(KEY_TOOLS, String.join(",", subject.getTools()));
        // Inserting Row
        db.insert(TABLE_Subject, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    public Subject findByTitle(String input){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Subject WHERE TRIM(name) = '"+input.trim()+"'", null);
        if(c.moveToFirst()){
            String title = c.getString(0);
            String teacher = c.getString(1);
            Integer imgId  = Integer.parseInt(c.getString(2));
            List<String> tools = Arrays.asList(c.getString(3).split(","));
            return new Subject(title,teacher,imgId,tools);
        }
        c.close();
        return  null;
    }


    public boolean deleteByName(String input){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_Subject, KEY_NAME + "=?", new String[]{input}) > 0;

    }

    public List<Subject> getAllSubjects() {
        List<Subject> contactList = new ArrayList<Subject>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Subject;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Subject subject = new Subject();
                subject.setName(cursor.getString(0));
                subject.setTeacher(cursor.getString(1));
                subject.setImgId(Integer.parseInt(cursor.getString(2)));
                List<String> list = new ArrayList<>();
                Collections.addAll(list, cursor.getString(3).split(","));
                subject.setTools(list);
                // Adding contact to list
                contactList.add(subject);
            } while (cursor.moveToNext());
        }
        // return contact list
        return contactList;
    }

}
