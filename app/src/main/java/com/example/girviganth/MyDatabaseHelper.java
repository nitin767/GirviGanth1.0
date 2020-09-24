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

    public static final String LOGIN_TABLE_NAME = "login_detail";
    public static final String COLUMN_USERNAME = "username_name";
    public static final String COLUMN_PASSWORD = "password_name";

    public static final String BRANCH_TABLE_NAME = "branch_detail";
    public static final String COLUMN_BRANCH_ID = "branch_id";
    public static final String COLUMN_BRANCH_NAME = "branch_name";

    public static final String METAL_TABLE_NAME = "metal_detail";
    public static final String COLUMN_METAL_ID = "metal_id";
    public static final String COLUMN_METAL_NAME = "metal_name";
    //public static final String COLUMN_METAL_DATE = "metal_date";
    public static final String COLUMN_METAL_RATE = "metal_rate";

    public static final String CUSTOMER_TABLE_NAME = "customer_detail";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CUSTOMER = "customer_name";
    public static final String COLUMN_FATHER = "father_name";
    public static final String COLUMN_VILLAGE = "village_name";
    public static final String COLUMN_PHONE = "phone_no";

    public static final String ITEM_TABLE_NAME = "item_detail";
    public static final String COLUMN_ITEM_ID = "item_id";
    public static final String COLUMN_ITEM = "item_name";
    //public static final String COLUMN_METAL_ID = "metal_id";
    public static final String COLUMN_ACTUAL_WEIGHT = "actual_weight";
    public static final String COLUMN_WASTAGE_WEIGHT = "wastage_weight";
    public static final String COLUMN_NET_WEIGHT = "net_weight";
    public static final String COLUMN_PURITY = "purity";
    //public static final String COLUMN_METAL_RATE = "metal_rate";
    public static final String COLUMN_TODAY_VALUE = "today_value";


    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +LOGIN_TABLE_NAME+ "(username_name TEXT PRIMARY KEY, password_name TEXT)");
        db.execSQL("CREATE TABLE " +BRANCH_TABLE_NAME+ "(branch_id INTEGER PRIMARY KEY AUTOINCREMENT, branch_name TEXT)");
        db.execSQL("CREATE TABLE " +METAL_TABLE_NAME+ "(metal_id INTEGER PRIMARY KEY AUTOINCREMENT, metal_name TEXT, metal_rate FLOAT)");
        db.execSQL("CREATE TABLE " +CUSTOMER_TABLE_NAME+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, branch_id INTEGER, customer_name TEXT, father_name TEXT, village_name TEXT, phone_no INTEGER)");
        db.execSQL("CREATE TABLE " +ITEM_TABLE_NAME+ "(item_id INTEGER PRIMARY KEY AUTOINCREMENT, _id INTEGER, item_name TEXT, metal_id INTEGER, actual_weight FLOAT, wastage_weight FLOAT, net_weight FLOAT, purity FLOAT, today_value FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " +LOGIN_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +BRANCH_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +METAL_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +CUSTOMER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +ITEM_TABLE_NAME);
        onCreate(db);

    }

    // Add Login
    long addLogin (String username, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERNAME, username);
        cv.put(COLUMN_PASSWORD, password);
        long result = db.insert(LOGIN_TABLE_NAME, null, cv);
       return  result;

    }

    // Read One Login
    public String readOneLogin(String userName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.query("login_detail", null, " username_name=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("password_name"));
        cursor.close();
        return password;
    }

//    Cursor readAllLogin (){
//        String query = "SELECT * FROM " + LOGIN_TABLE_NAME;
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = null;
//        if(db != null) {
//            cursor = db.rawQuery(query, null);
//        }
//        return cursor;
//    }

    // Update Password
    void updateLogin (String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PASSWORD, password);

        try {
            long result = db.update(LOGIN_TABLE_NAME, cv, "_id=?", new String[]{username});
            if (result == -1) {
                Toast.makeText(context, "Failed to Change Password", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Password Updated Successfully!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    // Delete One Login
    void deleteOneLogin(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(LOGIN_TABLE_NAME, "_id=?", new String[]{username});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    // Add Branch
    void addBranch (String branchname){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BRANCH_NAME, branchname);
        long result = db.insert(BRANCH_TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Branch Added Successfully", Toast.LENGTH_SHORT).show();
        }

    }

    // Read All Login
    Cursor readAllBranch (){
        String query = "SELECT * FROM " + BRANCH_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;


    }

    // Update Branch
    void updateBranch (String branch_id, String branchname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BRANCH_NAME, branchname);

        try {
            long result = db.update(BRANCH_TABLE_NAME, cv, "_id=?", new String[]{branch_id});
            if (result == -1) {
                Toast.makeText(context, "Failed to Update Branch", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Branch Updated Successfully!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    // Delete One Branch
    void deleteOneBranch(String branch_id, String branchname){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(BRANCH_TABLE_NAME, "_id=?", new String[]{branch_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    // Add Metal
    void addMetal (String metalname, Float metalrate){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_METAL_NAME, metalname);
        //cv.put(COLUMN_METAL_DATE, metaldate);
        cv.put(COLUMN_METAL_RATE, metalrate);
        long result = db.insert(METAL_TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Metal Added Successfully", Toast.LENGTH_SHORT).show();
        }

    }

    // Read All Metal
    Cursor readAllMetal (){
        String query = "SELECT * FROM " + METAL_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;


    }

    // Update Metal Rate
    void updateMetalRate (String metal_id, String metalrate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_METAL_RATE, metalrate);

        try {
            long result = db.update(METAL_TABLE_NAME, cv, "_id=?", new String[]{metal_id});
            if (result == -1) {
                Toast.makeText(context, "Failed to Update Rate", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Rate Updated Successfully!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    // Delete One Metal
    void deleteOneMetal(String metal_id, String metalname){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(BRANCH_TABLE_NAME, "_id=?", new String[]{metal_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    // Add Customer
    void addCustomer (String customer, String father, String village, int phone){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER, customer);
        cv.put(COLUMN_FATHER, father);
        cv.put(COLUMN_VILLAGE, village);
        cv.put(COLUMN_PHONE, phone);
        long result = db.insert(CUSTOMER_TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }

    }

    // Read All Customer
    Cursor readAllData (){
        String query = "SELECT * FROM " + CUSTOMER_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;


    }

    // Update Customer
    void updateData (String customer_id, String customer, String father, String village, Integer phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER, customer);
        cv.put(COLUMN_FATHER, father);
        cv.put(COLUMN_VILLAGE, village);
        cv.put(COLUMN_PHONE, phone);
        try {
            long result = db.update(CUSTOMER_TABLE_NAME, cv, "_id=?", new String[]{customer_id});
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
    void deleteOneRow(String customer_id){
            SQLiteDatabase db = this.getWritableDatabase();
            long result = db.delete(CUSTOMER_TABLE_NAME, "_id=?", new String[]{customer_id});
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
    Cursor readAllItem (int customer_id){
        String query = "SELECT * FROM " + ITEM_TABLE_NAME+" where _id="+customer_id;
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
