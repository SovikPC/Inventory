package com.hfad.inventory;

import static com.hfad.inventory.EquipmentActivity.EXTRA_EQUIP;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InfoEquipActivity extends AppCompatActivity {

    Spinner cabs_select;
    Spinner status_select;
    int id_equip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_equipment);

        cabs_select = (Spinner) findViewById(R.id.spinner_search2);
        status_select = (Spinner) findViewById(R.id.spinner_search);

        id_equip = (Integer)getIntent().getExtras().get(EXTRA_EQUIP);

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
        cursorAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cabs_select.setAdapter(cursorAdapter2);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClick(View view){
        cabs_select = (Spinner) findViewById(R.id.spinner_search2);
        status_select = (Spinner) findViewById(R.id.spinner_search);
        long cabs_name = cabs_select.getSelectedItemId();
        long status_name = status_select.getSelectedItemId();

        InventoryDatabaseHelper databaseHelper = new InventoryDatabaseHelper(this);
        databaseHelper.updateEquipment(id_equip,cabs_name, status_name);
        Toast toast = Toast.makeText(this, "Оборудование обновлено", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(InfoEquipActivity.this, EquipmentActivity.class);
        startActivity(intent);
    }
}
