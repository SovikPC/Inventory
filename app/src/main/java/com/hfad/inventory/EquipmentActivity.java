package com.hfad.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class EquipmentActivity extends AppCompatActivity {

    ListView equipmentList;
    TextView header;
    InventoryDatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor equipmentCursor;
    SimpleCursorAdapter equipmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);

        header = findViewById(R.id.header);
        equipmentList = findViewById(R.id.list);

        databaseHelper = new InventoryDatabaseHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.add(0,1,0,"Добавить");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onResume(){
        super.onResume();
        db = databaseHelper.getReadableDatabase();

        equipmentCursor = db.rawQuery("Select * from "+InventoryDatabaseHelper.TABLE_1+" join "+InventoryDatabaseHelper.TABLE_3+" on " +
                        ""+InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_ID_EQUIPMENT+" = "+InventoryDatabaseHelper.TABLE_3+"."+InventoryDatabaseHelper.COLUMN_ID_EQUIP+" " +
                        "join "+InventoryDatabaseHelper.TABLE_2+" on "+InventoryDatabaseHelper.TABLE_3+"."+InventoryDatabaseHelper.COLUMN_ID_CAB+" = "+InventoryDatabaseHelper.TABLE_2+"."+InventoryDatabaseHelper.COLUMN_ID_CABS+"" +
                        " join "+InventoryDatabaseHelper.TABLE_5+" on "+InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_ID_EQUIPMENT+" = "+InventoryDatabaseHelper.TABLE_5+"."+InventoryDatabaseHelper.COLUMN_ID_EQ+"" +
                        " join "+InventoryDatabaseHelper.TABLE_4+" on "+InventoryDatabaseHelper.TABLE_5+"."+InventoryDatabaseHelper.COLUMN_ID_ST+" = "+InventoryDatabaseHelper.TABLE_4+"."+InventoryDatabaseHelper.COLUMN_ID_STATE+"",
                null);
        String[] headers = new String[] {InventoryDatabaseHelper.COLUMN_NAME_CABS, InventoryDatabaseHelper.COLUMN_NAME_EQUIPMENT,InventoryDatabaseHelper.COLUMN_INV_EQUIPMENT, InventoryDatabaseHelper.COLUMN_SR_EQUIPMENT, InventoryDatabaseHelper.COLUMN_NAME_STATE};
        equipmentAdapter = new SimpleCursorAdapter(this, R.layout.list_items,
                equipmentCursor, headers, new int[]{R.id.text_view_cabs, R.id.text_view_name, R.id.text_view_invent, R.id.text_view_serial, R.id.text_view_state}, 0);
        equipmentList.setAdapter(equipmentAdapter);
    }

    public void onDestroy(){
        super.onDestroy();
        db.close();
        equipmentCursor.close();
    }
}
