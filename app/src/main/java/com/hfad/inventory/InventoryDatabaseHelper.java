package com.hfad.inventory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InventoryDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "inventoryzation.db";
    private static final int DB_VERSION = 1;

    public InventoryDatabaseHelper(Context context){
        super(context, DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("Create table equipment(_id integer primary key autoincrement,"
                +"name text,"
                +"inventory integer unique,"
                +"serial text unique);");
        db.execSQL("Create table cabs(_id integer primary key autoincrement,"
                +"name text);");
        db.execSQL("Create table cabs_equipment(equipment_id integer not null,"
                +"cabs_id integer not null,"
                +"foreign key (equipment_id) references equipment(id),"
                +"foreign key (cabs_id) references cabs(id));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
    }