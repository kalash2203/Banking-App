package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9812345678,'Karan',6794.00,'karan.singh@gmail.com','XXXXXXXXXXXX9457','ABC09876543')");
        db.execSQL("insert into user_table values(9832145679,'Akshay',101.67,'akshaykumar54@gmail.com','XXXXXXXXXXXX2378','BCA98765432')");
        db.execSQL("insert into user_table values(8912345678,'Golu',847.56,'goluulog7@gmail.com','XXXXXXXXXXXX9845','CAB87654321')");
        db.execSQL("insert into user_table values(9045637893,'Sanjeet',10000.01,'sanjeet5@gmail.com','XXXXXXXXXXXX5693','ABC76543210')");
        db.execSQL("insert into user_table values(9412789435,'Rahul',2491.48,'rahul89@gmail.com','XXXXXXXXXXXX7469','BCA65432109')");
        db.execSQL("insert into user_table values(8967435523,'Harshal',765.16,'harshal92@gmail.com','XXXXXXXXXXXX8721','CAB54321098')");
        db.execSQL("insert into user_table values(2234546298,'Anil',9345.00,'anilkapoor45@gmail.com','XXXXXXXXXXXX3749','ABC43210987')");
        db.execSQL("insert into user_table values(7823465871,'Vancover',879.22,'van5cover@gmail.com','XXXXXXXXXXXX7835','BCA32109876')");
        db.execSQL("insert into user_table values(8712376403,'Tokyo',7451.46,'tokyo4555@gmail.com','XXXXXXXXXXXX9634','CAB21098765')");
        db.execSQL("insert into user_table values(9467092687,'Delhi',205.90,'delhi89@gmail.com','XXXXXXXXXXXX8931','ABC10987654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
