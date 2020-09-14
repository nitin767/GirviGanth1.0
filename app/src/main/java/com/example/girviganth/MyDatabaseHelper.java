package com.example.girviganth;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "GirviGanth.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "customer_detail";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CUSTOMER = "customer_name";
    public static final String COLUMN_FATHER = "father_name";
    public static final String COLUMN_VILLAGE = "village_name";
    public static final String COLUMN_PHONE = "phone_no";
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +TABLE_NAME+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, customer_name TEXT, father_name TEXT, village_name TEXT, phone_no INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);

    }

    // Add Customer

    void addCustomer (String customer, String father, String village, int phone){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER, customer);
        cv.put(COLUMN_FATHER, father);
        cv.put(COLUMN_VILLAGE, village);
        cv.put(COLUMN_PHONE, phone);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }

    }
}
