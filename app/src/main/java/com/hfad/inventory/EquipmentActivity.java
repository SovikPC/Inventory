package com.hfad.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

public class EquipmentActivity extends AppCompatActivity{

    ListView equipmentList;
    InventoryDatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor equipmentCursor;
    SimpleCursorAdapter equipmentAdapter;
    EditText search_equipment;
    public static final String EXTRA_EQUIP = "id_equip";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);

        equipmentList = findViewById(R.id.list);

        databaseHelper = new InventoryDatabaseHelper(this);
        search_equipment = findViewById(R.id.search_bar);


        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(EquipmentActivity.this, InfoEquipActivity.class);
                intent.putExtra(EquipmentActivity.EXTRA_EQUIP, (int) id);
                startActivity(intent);
            }
        };
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(itemClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.add(0,1,0,"Добавить оборудование")
                .setIcon(R.drawable.add_equip_foreground);
        menu.add(0,2,0,"Добавить кабинет")
                .setIcon(R.drawable.add_equip_foreground);
        menu.add(0,3,0,"Добавить тип оборудования")
                .setIcon(R.drawable.add_equip_foreground);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                Intent intent = new Intent(this,InsertEquipment.class);
                startActivity(intent);
            return true;
            case 2:
                Intent intent2 = new Intent(this,InsertCabs.class);
                startActivity(intent2);
                return true;
            case 3:
                Intent intent3 = new Intent(this,InsertType.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onResume(){
        super.onResume();
        db = databaseHelper.getReadableDatabase();

        equipmentCursor = db.rawQuery("Select "+InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_ID_EQUIPMENT+" , "+InventoryDatabaseHelper.TABLE_2+"."+InventoryDatabaseHelper.COLUMN_NAME_CABS+", "+InventoryDatabaseHelper.TABLE_4+"."+InventoryDatabaseHelper.COLUMN_NAME_TYPE+", "+InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_INV_EQUIPMENT+", "+InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_SR_EQUIPMENT+", "+InventoryDatabaseHelper.TABLE_3+"."+InventoryDatabaseHelper.COLUMN_NAME_STATE+"  from "+InventoryDatabaseHelper.TABLE_1+"" +
                        " join "+InventoryDatabaseHelper.TABLE_3+" on "+InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_ID_ST+" = "+InventoryDatabaseHelper.TABLE_3+"."+InventoryDatabaseHelper.COLUMN_ID_STATE+" " +
                        "join "+InventoryDatabaseHelper.TABLE_2+" on "+InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_ID_CAB+" = "+InventoryDatabaseHelper.TABLE_2+"."+InventoryDatabaseHelper.COLUMN_ID_CABS+"" +
                        " join "+InventoryDatabaseHelper.TABLE_4+" on "+InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_ID_TYP+" = "+InventoryDatabaseHelper.TABLE_4+"."+InventoryDatabaseHelper.COLUMN_ID_TYPE+"",
                null);
        String[] headers = new String[] {InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_ID_EQUIPMENT,InventoryDatabaseHelper.COLUMN_NAME_CABS, InventoryDatabaseHelper.COLUMN_NAME_TYPE,InventoryDatabaseHelper.COLUMN_INV_EQUIPMENT,InventoryDatabaseHelper.COLUMN_SR_EQUIPMENT,InventoryDatabaseHelper.COLUMN_NAME_STATE};
        equipmentAdapter = new SimpleCursorAdapter(this, R.layout.list_items,
                equipmentCursor, headers, new int[]{R.id.text_id_equipment,R.id.text_view_cabs, R.id.text_view_name, R.id.text_view_invent, R.id.text_view_serial, R.id.text_view_state}, 0);
        if(!search_equipment.getText().toString().isEmpty()) {
            equipmentAdapter.getFilter().filter(search_equipment.getText().toString());
        }
            search_equipment.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    equipmentAdapter.getFilter().filter(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        equipmentAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                if(constraint == null||constraint.length() == 0
                ){
                    return db.rawQuery("Select "+InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_ID_EQUIPMENT+" , "+InventoryDatabaseHelper.TABLE_2+"."+InventoryDatabaseHelper.COLUMN_NAME_CABS+", "+InventoryDatabaseHelper.TABLE_4+"."+InventoryDatabaseHelper.COLUMN_NAME_TYPE+", "+InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_INV_EQUIPMENT+", "+InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_SR_EQUIPMENT+", "+InventoryDatabaseHelper.TABLE_3+"."+InventoryDatabaseHelper.COLUMN_NAME_STATE+"  from "+InventoryDatabaseHelper.TABLE_1+"" +
                                    " join "+InventoryDatabaseHelper.TABLE_3+" on "+InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_ID_ST+" = "+InventoryDatabaseHelper.TABLE_3+"."+InventoryDatabaseHelper.COLUMN_ID_STATE+" " +
                                    "join "+InventoryDatabaseHelper.TABLE_2+" on "+InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_ID_CAB+" = "+InventoryDatabaseHelper.TABLE_2+"."+InventoryDatabaseHelper.COLUMN_ID_CABS+"" +
                                    " join "+InventoryDatabaseHelper.TABLE_4+" on "+InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_ID_TYP+" = "+InventoryDatabaseHelper.TABLE_4+"."+InventoryDatabaseHelper.COLUMN_ID_TYPE+"",
                            null);
                }else{
                    return db.rawQuery("Select "+InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_ID_EQUIPMENT+" , "+InventoryDatabaseHelper.TABLE_2+"."+InventoryDatabaseHelper.COLUMN_NAME_CABS+", "+InventoryDatabaseHelper.TABLE_4+"."+InventoryDatabaseHelper.COLUMN_NAME_TYPE+", "+InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_INV_EQUIPMENT+", "+InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_SR_EQUIPMENT+", "+InventoryDatabaseHelper.TABLE_3+"."+InventoryDatabaseHelper.COLUMN_NAME_STATE+"  from "+InventoryDatabaseHelper.TABLE_1+"" +
                                    " join "+InventoryDatabaseHelper.TABLE_3+" on "+InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_ID_ST+" = "+InventoryDatabaseHelper.TABLE_3+"."+InventoryDatabaseHelper.COLUMN_ID_STATE+" " +
                                    "join "+InventoryDatabaseHelper.TABLE_2+" on "+InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_ID_CAB+" = "+InventoryDatabaseHelper.TABLE_2+"."+InventoryDatabaseHelper.COLUMN_ID_CABS+"" +
                                    " join "+InventoryDatabaseHelper.TABLE_4+" on "+InventoryDatabaseHelper.TABLE_1+"."+InventoryDatabaseHelper.COLUMN_ID_TYP+" = "+InventoryDatabaseHelper.TABLE_4+"."+InventoryDatabaseHelper.COLUMN_ID_TYPE+"" +
                                    " where "+InventoryDatabaseHelper.COLUMN_NAME_CABS+" like ?",
                            new String[]{"%"+constraint.toString()+"%"});
                }
            }
        });
        equipmentList.setAdapter(equipmentAdapter);
    }



    public void onDestroy(){
        super.onDestroy();
        db.close();
        equipmentCursor.close();
    }
}
