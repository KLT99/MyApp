package com.klt.myapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;


public class SharedPreferencesClass extends AppCompatActivity {

    private EditText edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        edtEmail = (EditText) findViewById(R.id.edt_email);

        //Crea un objeto de tipo SharedPreferences   //obtiene la preferencia que se allá guardado en el metodo getSharedPreferences
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        edtEmail.setText(preferences.getString("mail", ""));

    }

    //Método para el boton guardar
    public void GuardarDato(View view){

        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        //con este objeto se guardan los datos
        SharedPreferences.Editor obj_editor = preferences.edit();
        obj_editor.putString("mail", edtEmail.getText().toString());
        //confirma que el valor que recuperamos queremos guardarlo en el shared preferences
        obj_editor.commit();
        finish();

    }

}