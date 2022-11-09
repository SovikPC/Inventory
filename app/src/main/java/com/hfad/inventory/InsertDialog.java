package com.hfad.inventory;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class InsertDialog extends AppCompatDialogFragment {
    private EditText cabs_text;
    private EditText equip_text;
    private EditText invent_num;
    private EditText serial_text;
    private Spinner spinner_state;
    @Override
    public Dialog  onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_signin, null);

        builder.setView(view)
                .setTitle("Добовление оборудования")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        cabs_text = view.findViewById(R.id.cabs);
        equip_text = view.findViewById(R.id.equip);
        invent_num = view.findViewById(R.id.inventory);
        serial_text = view.findViewById(R.id.serial);


        return builder.create();
    }
}

