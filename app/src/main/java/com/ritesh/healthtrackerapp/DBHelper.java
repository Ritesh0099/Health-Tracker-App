package com.ritesh.healthtrackerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "health_tracker.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_SYMPTOMS = "symptoms";
    private static final String TABLE_PROFILE = "profile";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create symptoms table
        db.execSQL("CREATE TABLE " + TABLE_SYMPTOMS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, symptom TEXT)");

        // Create profile table
        db.execSQL("CREATE TABLE " + TABLE_PROFILE + " (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, username TEXT, age INTEGER, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SYMPTOMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        onCreate(db);
    }

    // Add Symptom
    public boolean insertSymptom(String symptom) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("symptom", symptom);
        long result = db.insert(TABLE_SYMPTOMS, null, values);
        return result != -1;
    }

    // Get All Symptoms
    public Cursor getAllSymptoms() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_SYMPTOMS, null);
    }

    // Save or Update Profile
    public boolean saveProfile(String email, String username, int age, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROFILE, null, null); // Clear previous profile

        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("username", username);
        values.put("age", age);
        values.put("password", password);

        long result = db.insert(TABLE_PROFILE, null, values);
        return result != -1;
    }

    // Fetch Profile (only one row)
    public Cursor getProfile() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PROFILE + " LIMIT 1", null);
    }
}
