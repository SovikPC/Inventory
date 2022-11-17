package com.hfad.inventory;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class InventoryDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "inventory.db";
    private static final int DB_VERSION = 1;

    static final String TABLE_1 = "Equipment";

    public static final String COLUMN_ID_EQUIPMENT = "_id";
    public static final String COLUMN_ID_CAB = "cabs_id";
    public static final String COLUMN_ID_TYP = "type_id";
    public static final String COLUMN_INV_EQUIPMENT = "Inventory";
    public static final String COLUMN_SR_EQUIPMENT = "Serial";
    public static final String COLUMN_ID_ST = "state_id";

    static final String TABLE_2 = "Cabs";

    public static final String COLUMN_ID_CABS = "_id";
    public static final String COLUMN_NAME_CABS = "name_cabs";


    static final String TABLE_3 = "State";

    public static final String COLUMN_ID_STATE = "_id";
    public static final String COLUMN_NAME_STATE = "name_state";

    static final String TABLE_4 = "Type";

    public static final String COLUMN_ID_TYPE = "_id";
    public static final String COLUMN_NAME_TYPE = "name_type";

    public InventoryDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table "+TABLE_2+" (" +
                ""+COLUMN_ID_CABS+" integer primary key autoincrement," +
                ""+COLUMN_NAME_CABS+" text)");
        db.execSQL("Create table "+TABLE_3+" (" +
                ""+COLUMN_ID_STATE+" integer primary key autoincrement," +
                ""+COLUMN_NAME_STATE+" text)");
        db.execSQL("Create table "+TABLE_4+" (" +
                ""+COLUMN_ID_TYPE+" integer primary key autoincrement," +
                ""+COLUMN_NAME_TYPE+" text)");
        db.execSQL("Create table "+TABLE_1+" (" +
                ""+COLUMN_ID_EQUIPMENT+" integer primary key autoincrement," +
                ""+COLUMN_ID_CAB+" integer," +
                ""+COLUMN_ID_TYP+" integer," +
                ""+COLUMN_INV_EQUIPMENT+" integer unique," +
                ""+COLUMN_SR_EQUIPMENT+" text unique," +
                ""+COLUMN_ID_ST+" integer," +
                "Foreign key("+COLUMN_ID_CAB+") references "+TABLE_2+"("+COLUMN_ID_CABS+")," +
                "Foreign key("+COLUMN_ID_TYP+") references "+TABLE_4+"("+COLUMN_ID_TYPE+")," +
                "Foreign key("+COLUMN_ID_ST+") references "+TABLE_3+"("+COLUMN_ID_STATE+"))");
        db.execSQL("INSERT INTO "+TABLE_2+" ("+COLUMN_NAME_CABS+") VALUES ('206');");
        db.execSQL("INSERT INTO "+TABLE_2+" ("+COLUMN_NAME_CABS+") VALUES ('209');");
        db.execSQL("INSERT INTO "+TABLE_4+" ("+COLUMN_NAME_TYPE+") VALUES ('Системный блок');");
        db.execSQL("INSERT INTO "+TABLE_4+" ("+COLUMN_NAME_TYPE+") VALUES ('Монитор');");
        db.execSQL("INSERT INTO "+TABLE_3+" ("+COLUMN_NAME_STATE+") VALUES ('В наличии');");
        db.execSQL("INSERT INTO "+TABLE_3+" ("+COLUMN_NAME_STATE+") VALUES ('Утерян');");
        db.execSQL("INSERT INTO "+TABLE_1+" ("+COLUMN_ID_CAB+", "+COLUMN_ID_TYP+", "+COLUMN_INV_EQUIPMENT+", "+COLUMN_SR_EQUIPMENT+", "+COLUMN_ID_ST+") VALUES (1,1,119043257,'76fdg4gf5hd4',1), (2,2,7565925564,'fdhf56d74h6f4',2);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        if(newVersion>0){
            db.execSQL("Create table "+TABLE_2+" (" +
                    ""+COLUMN_ID_CABS+" integer primary key autoincrement," +
                    ""+COLUMN_NAME_CABS+" text)");
            db.execSQL("Create table "+TABLE_3+" (" +
                    ""+COLUMN_ID_STATE+" integer primary key autoincrement," +
                    ""+COLUMN_NAME_STATE+" text)");
            db.execSQL("Create table "+TABLE_4+" (" +
                    ""+COLUMN_ID_TYPE+" integer primary key autoincrement," +
                    ""+COLUMN_NAME_TYPE+" text)");
            db.execSQL("Create table "+TABLE_1+" (" +
                    ""+COLUMN_ID_EQUIPMENT+" integer primary key autoincrement," +
                    ""+COLUMN_ID_CAB+" integer," +
                    ""+COLUMN_ID_TYP+" integer," +
                    ""+COLUMN_INV_EQUIPMENT+" integer unique," +
                    ""+COLUMN_SR_EQUIPMENT+" text unique," +
                    ""+COLUMN_ID_ST+" integer," +
                    "Foreign key("+COLUMN_ID_CAB+") references "+TABLE_2+"("+COLUMN_ID_CABS+")," +
                    "Foreign key("+COLUMN_ID_TYP+") references "+TABLE_4+"("+COLUMN_ID_TYPE+")," +
                    "Foreign key("+COLUMN_ID_ST+") references "+TABLE_3+"("+COLUMN_ID_STATE+"))");
        }
        onCreate(db);
    }
    public void insertEquipment(long cabs,long type, String invent, String serial, long status){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_ID_CAB,cabs);
        values.put(COLUMN_ID_TYP,type);
        values.put(COLUMN_INV_EQUIPMENT,invent);
        values.put(COLUMN_SR_EQUIPMENT,serial);
        values.put(COLUMN_ID_ST,status);

        db.insert(TABLE_1, null, values);
    }

    public void insertCabs(String cabs){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME_CABS,cabs);

        db.insert(TABLE_2, null, values);
    }

    public void insertType(String type){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME_TYPE,type);

        db.insert(TABLE_4, null, values);
    }

    public void updateEquipment(long id, long cabs, long status){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_ID_CAB,cabs);
        values.put(COLUMN_ID_ST,status);

        db.update(TABLE_1,values,COLUMN_ID_EQUIPMENT+ "=" + id, null);
    }
}
