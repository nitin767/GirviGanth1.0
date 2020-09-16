package com.example.girviganth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public static final String ITEM_TABLE_NAME = "item_detail";
    public static final String COLUMN_ITEM_ID = "item_id";
    public static final String COLUMN_ITEM = "item_name";
    public static final String COLUMN_METAL = "metal_name";
    public static final String COLUMN_ACTUAL_WEIGHT = "actual_weight";
    public static final String COLUMN_WASTAGE_WEIGHT = "wastage_weight";
    public static final String COLUMN_NET_WEIGHT = "net_weight";
    public static final String COLUMN_PURITY = "purity";
    public static final String COLUMN_METAL_RATE = "metal_rate";
    public static final String COLUMN_TODAY_VALUE = "today_value";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +TABLE_NAME+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, customer_name TEXT, father_name TEXT, village_name TEXT, phone_no INTEGER)");
        db.execSQL("CREATE TABLE " +ITEM_TABLE_NAME+ "(item_id INTEGER PRIMARY KEY AUTOINCREMENT, _id INTEGER, item_name TEXT, metal_name TEXT, actual_weight FLOAT, wastage_weight FLOAT, net_weight FLOAT, purity FLOAT, metal_rate FLOAT, today_value FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +ITEM_TABLE_NAME);
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

    // Read All Customer
    Cursor readAllData (){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;


    }

    // Update Customer
    void updateData (String row_id, String customer, String father, String village, Integer phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER, customer);
        cv.put(COLUMN_FATHER, father);
        cv.put(COLUMN_VILLAGE, village);
        cv.put(COLUMN_PHONE, phone);
        try {
            long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
            if (result == -1) {
                Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    // Delete One Customer
    void deleteOneRow(String row_id){
            SQLiteDatabase db = this.getWritableDatabase();
            long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
            if (result == -1) {
                Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
            }
        }

     // Add Item

    void addItem (int customer_id, String item, String metal, float actual, float wastage, float purity, float rate){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        float net;
        float value;
        cv.put(COLUMN_ID, customer_id);
        cv.put(COLUMN_ITEM, item);
        cv.put(COLUMN_METAL, metal);
        cv.put(COLUMN_ACTUAL_WEIGHT, actual);
        cv.put(COLUMN_WASTAGE_WEIGHT, wastage);
        net = actual - wastage;
        cv.put(COLUMN_NET_WEIGHT, net);
        cv.put(COLUMN_PURITY, purity);
        cv.put(COLUMN_METAL_RATE, rate);
        value = (net*purity*(rate/10));
        cv.put(COLUMN_TODAY_VALUE, value);

        long result = db.insert(ITEM_TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }

    }

    // Read All Items
    Cursor readAllItem (){
        String query = "SELECT * FROM " + ITEM_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;


    }

    // Update Items
    void updateItem (String row_id, String item, String metal, float actual, float wastage, float purity, float rate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        float net;
        float value;
        cv.put(COLUMN_ITEM, item);
        cv.put(COLUMN_METAL, metal);
        cv.put(COLUMN_ACTUAL_WEIGHT, actual);
        cv.put(COLUMN_WASTAGE_WEIGHT, wastage);
        net = actual - wastage;
        cv.put(COLUMN_NET_WEIGHT, net);
        cv.put(COLUMN_PURITY, purity);
        cv.put(COLUMN_METAL_RATE, rate);
        value = (net*purity*(rate/10));
        cv.put(COLUMN_TODAY_VALUE, value);
        try {
            long result = db.update(ITEM_TABLE_NAME, cv, "item_id=?", new String[]{row_id});
            if (result == -1) {
                Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    // Delete One Item
    void deleteOneItem(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(ITEM_TABLE_NAME, "item_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
