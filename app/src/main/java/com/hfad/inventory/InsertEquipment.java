package com.hfad.inventory;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertEquipment extends AppCompatActivity {

    Spinner cabs_select;
    Spinner type_select;
    Spinner status_select;
    EditText insert;
    EditText serial;
    Cursor equipmentCursor;
    String item_type;
    String item_cabs;
    String item_state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_signin);

        cabs_select = (Spinner) findViewById(R.id.spinner_search2);
        type_select = (Spinner) findViewById(R.id.spinner_search3);
        status_select = (Spinner) findViewById(R.id.spinner_search);

        String[] queryCols = new String[]{"_id", InventoryDatabaseHelper.COLUMN_NAME_STATE};
        SQLiteDatabase database = new InventoryDatabaseHelper(this).getReadableDatabase();
        Cursor cursor = database.query(
                InventoryDatabaseHelper.TABLE_3,
                queryCols,
                null,
                null,
                null,
                null,
                null
        );
        String[] adapterCols = new String[]{InventoryDatabaseHelper.COLUMN_NAME_STATE};
        int[] adapterRowViews = new int[]{android.R.id.text1};
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(
                this, android.R.layout.simple_spinner_item, cursor, adapterCols, adapterRowViews, 0);
        cursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status_select.setAdapter(cursorAdapter);

        String[] queryCols2 = new String[]{"_id", InventoryDatabaseHelper.COLUMN_NAME_CABS};
        SQLiteDatabase database2 = new InventoryDatabaseHelper(this).getReadableDatabase();
        Cursor cursor2 = database2.query(
                InventoryDatabaseHelper.TABLE_2,
                queryCols2,
                null,
                null,
                null,
                null,
                null
        );
        String[] adapterCols2 = new String[]{InventoryDatabaseHelper.COLUMN_NAME_CABS};
        int[] adapterRowViews2 = new int[]{android.R.id.text1};
        SimpleCursorAdapter cursorAdapter2 = new SimpleCursorAdapter(
                this, android.R.layout.simple_spinner_item, cursor2, adapterCols2, adapterRowViews2, 0);
        cursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cabs_select.setAdapter(cursorAdapter2);

        String[] queryCols3 = new String[]{"_id", InventoryDatabaseHelper.COLUMN_NAME_TYPE};
        SQLiteDatabase database3 = new InventoryDatabaseHelper(this).getReadableDatabase();
        Cursor cursor3 = database3.query(
                InventoryDatabaseHelper.TABLE_4,
                queryCols3,
                null,
                null,
                null,
                null,
                null
        );
        String[] adapterCols3 = new String[]{InventoryDatabaseHelper.COLUMN_NAME_TYPE};
        int[] adapterRowViews3 = new int[]{android.R.id.text1};
        SimpleCursorAdapter cursorAdapter3 = new SimpleCursorAdapter(
                this, android.R.layout.simple_spinner_item, cursor3, adapterCols3, adapterRowViews3, 0);
        cursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_select.setAdapter(cursorAdapter3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClick(View view){
        cabs_select = (Spinner) findViewById(R.id.spinner_search2);
        type_select = (Spinner) findViewById(R.id.spinner_search3);
        status_select = (Spinner) findViewById(R.id.spinner_search);
        long cabs_name = cabs_select.getSelectedItemId();
        long type_name = type_select.getSelectedItemId();
        long status_name = status_select.getSelectedItemId();
        insert = findViewById(R.id.inv_insert);
        serial = findViewById(R.id.serial_insert);
        String insert_num = insert.getText().toString();
        String serial_num = serial.getText().toString();
        InventoryDatabaseHelper databaseHelper = new InventoryDatabaseHelper(this);
        databaseHelper.insertEquipment(cabs_name,type_name, insert_num, serial_num,status_name);

    }
}

