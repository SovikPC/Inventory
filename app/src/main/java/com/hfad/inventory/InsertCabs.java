package com.hfad.inventory;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertCabs extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_cabs);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClick(View view){
        EditText cabs_insert = findViewById(R.id.new_cabs);
        String cabs_name = cabs_insert.getText().toString();
        if(cabs_name.equals("")){
            Toast toast = Toast.makeText(this,"Пожалуйсто заполните поле", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            InventoryDatabaseHelper databaseHelper = new InventoryDatabaseHelper(this);
            databaseHelper.insertCabs(cabs_name);
            Toast toast = Toast.makeText(this, "Кабинет добавлен в базу", Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(InsertCabs.this, EquipmentActivity.class);
            startActivity(intent);
        }
    }
}
