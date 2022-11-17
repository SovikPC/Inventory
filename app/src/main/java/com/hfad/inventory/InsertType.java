package com.hfad.inventory;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertType extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_type);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClick(View view){
        EditText type_insert = findViewById(R.id.new_type);
        String type_name = type_insert.getText().toString();
        if(type_name.equals("")){
            Toast toast = Toast.makeText(this,"Пожалуйсто заполните поле", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            InventoryDatabaseHelper databaseHelper = new InventoryDatabaseHelper(this);
            databaseHelper.insertType(type_name);
            Toast toast = Toast.makeText(this, "Тип оборудования добавлен в базу", Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(InsertType.this, EquipmentActivity.class);
            startActivity(intent);
        }
    }
}
