package com.hfad.inventory;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class InventoryDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "inventory.db";
    private static final int DB_VERSION = 1;

    static final String TABLE_1 = "Equipment";

    public static final String COLUMN_ID_EQUIPMENT = "_id";
    public static final String COLUMN_NAME_EQUIPMENT = "name_equipment";
    public static final String COLUMN_INV_EQUIPMENT = "Inventory";
    public static final String COLUMN_SR_EQUIPMENT = "Serial";
    public  static  final  String COLUMN_STATE_EQUIPMENT = "State";

    static final String TABLE_2 = "Cabs";

    public static final String COLUMN_ID_CABS = "_id";
    public static final String COLUMN_NAME_CABS = "name_cabs";

    static final String TABLE_3 = "Cabs_Equipment";

    public static final String COLUMN_ID_CAB = "cabs_id";
    public static final String COLUMN_ID_EQUIP = "equipment_id";

    static final String TABLE_4 = "State";

    public static final String COLUMN_ID_STATE = "_id";
    public static final String COLUMN_NAME_STATE = "name_state";

    static final String TABLE_5 = "State_Equipment";

    public static final String COLUMN_ID_ST = "state_id";
    public static final String COLUMN_ID_EQ = "equipment_id";

    public InventoryDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Equipment("+COLUMN_ID_EQUIPMENT+" integer primary key autoincrement,"
                +COLUMN_NAME_EQUIPMENT+" text,"
                +COLUMN_INV_EQUIPMENT+" integer unique,"
                +COLUMN_SR_EQUIPMENT+" text unique,"
                +COLUMN_STATE_EQUIPMENT+" text);");
        db.execSQL("Create table Cabs("+COLUMN_ID_CABS+" integer primary key autoincrement,"
                +COLUMN_NAME_CABS+" text);");
        db.execSQL("Create table Cabs_Equipment("+COLUMN_ID_EQUIP+" integer not null,"
                +COLUMN_ID_CAB+" integer not null,"
                +"foreign key ("+COLUMN_ID_EQUIP+") references Equipment("+COLUMN_ID_EQUIPMENT+"),"
                +"foreign key ("+COLUMN_ID_CAB+") references Cabs("+COLUMN_ID_CABS+"));");
        db.execSQL("Create table State("+COLUMN_ID_STATE+" integer primary key autoincrement,"
                +COLUMN_NAME_STATE+" text);");
        db.execSQL("Create table State_Equipment("+COLUMN_ID_EQ+" integer not null,"
                +COLUMN_ID_ST+" integer not null,"
                +"foreign key ("+COLUMN_ID_EQ+") references Equipment("+COLUMN_ID_EQUIPMENT+"),"
                +"foreign key ("+COLUMN_ID_ST+") references State("+COLUMN_ID_STATE+"));");
        db.execSQL("INSERT INTO "+TABLE_1+" ("+COLUMN_NAME_EQUIPMENT+","+COLUMN_INV_EQUIPMENT+","+COLUMN_SR_EQUIPMENT+") VALUES ('Системный блок',187385625,'8g7fds5g6sg55ud');");
        db.execSQL("INSERT INTO "+TABLE_2+" ("+COLUMN_NAME_CABS+") VALUES ('206');");
        db.execSQL("INSERT INTO "+TABLE_3+" ("+COLUMN_ID_CAB+","+COLUMN_ID_EQUIP+") VALUES (1,1);");
        db.execSQL("INSERT INTO "+TABLE_1+" ("+COLUMN_NAME_EQUIPMENT+","+COLUMN_INV_EQUIPMENT+","+COLUMN_SR_EQUIPMENT+") VALUES ('Системный блок',34597645876,'87dfg57d6g765');");
        db.execSQL("INSERT INTO "+TABLE_2+" ("+COLUMN_NAME_CABS+") VALUES ('209');");
        db.execSQL("INSERT INTO "+TABLE_3+" ("+COLUMN_ID_CAB+","+COLUMN_ID_EQUIP+") VALUES (2,2);");
        db.execSQL("INSERT INTO "+TABLE_4+" ("+COLUMN_NAME_STATE+") VALUES ('Работает');");
        db.execSQL("INSERT INTO "+TABLE_5+" ("+COLUMN_ID_ST+","+COLUMN_ID_EQ+") VALUES (1,1);");
        db.execSQL("INSERT INTO "+TABLE_4+" ("+COLUMN_NAME_STATE+") VALUES ('Не найден');");
        db.execSQL("INSERT INTO "+TABLE_5+" ("+COLUMN_ID_ST+","+COLUMN_ID_EQ+") VALUES (2,2);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        if(newVersion>0){
            db.execSQL("Create table Equipment("+COLUMN_ID_EQUIPMENT+" integer primary key autoincrement,"
                    +COLUMN_NAME_EQUIPMENT+" text,"
                    +COLUMN_INV_EQUIPMENT+" integer unique,"
                    +COLUMN_SR_EQUIPMENT+" text unique,"
                    +COLUMN_STATE_EQUIPMENT+" text);");
            db.execSQL("Create table Cabs("+COLUMN_ID_CABS+" integer primary key autoincrement,"
                    +COLUMN_NAME_CABS+" text);");
            db.execSQL("Create table Cabs_Equipment("+COLUMN_ID_EQUIP+" integer not null,"
                    +COLUMN_ID_CAB+" integer not null,"
                    +"foreign key ("+COLUMN_ID_EQUIP+") references Equipment("+COLUMN_ID_EQUIPMENT+"),"
                    +"foreign key ("+COLUMN_ID_CAB+") references Cabs("+COLUMN_ID_CABS+"));");
            db.execSQL("Create table State("+COLUMN_ID_STATE+" integer primary key autoincrement,"
                    +COLUMN_NAME_STATE+" text);");
            db.execSQL("Create table State_Equipment("+COLUMN_ID_EQ+" integer not null,"
                    +COLUMN_ID_ST+" integer not null,"
                    +"foreign key ("+COLUMN_ID_EQ+") references Equipment("+COLUMN_ID_EQUIPMENT+"),"
                    +"foreign key ("+COLUMN_ID_ST+") references State("+COLUMN_ID_STATE+"));");
            db.execSQL("INSERT INTO "+TABLE_1+" ("+COLUMN_NAME_EQUIPMENT+","+COLUMN_INV_EQUIPMENT+","+COLUMN_SR_EQUIPMENT+") VALUES ('Системный блок',187385625,'8g7fds5g6sg55ud');");
            db.execSQL("INSERT INTO "+TABLE_2+" ("+COLUMN_NAME_CABS+") VALUES ('206');");
            db.execSQL("INSERT INTO "+TABLE_3+" ("+COLUMN_ID_CAB+","+COLUMN_ID_EQUIP+") VALUES (1,1);");
            db.execSQL("INSERT INTO "+TABLE_1+" ("+COLUMN_NAME_EQUIPMENT+","+COLUMN_INV_EQUIPMENT+","+COLUMN_SR_EQUIPMENT+") VALUES ('Системный блок',34597645876,'87dfg57d6g765');");
            db.execSQL("INSERT INTO "+TABLE_2+" ("+COLUMN_NAME_CABS+") VALUES ('209');");
            db.execSQL("INSERT INTO "+TABLE_3+" ("+COLUMN_ID_CAB+","+COLUMN_ID_EQUIP+") VALUES (2,2);");
            db.execSQL("INSERT INTO "+TABLE_4+" ("+COLUMN_NAME_STATE+") VALUES ('Работает');");
            db.execSQL("INSERT INTO "+TABLE_5+" ("+COLUMN_ID_ST+","+COLUMN_ID_EQ+") VALUES (1,1);");
            db.execSQL("INSERT INTO "+TABLE_4+" ("+COLUMN_NAME_STATE+") VALUES ('Не найден');");
            db.execSQL("INSERT INTO "+TABLE_5+" ("+COLUMN_ID_ST+","+COLUMN_ID_EQ+") VALUES (2,2);");
        }
        onCreate(db);
    }
}
