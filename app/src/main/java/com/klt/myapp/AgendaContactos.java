package com.klt.myapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AgendaContactos extends AppCompatActivity {

    private EditText edt_nombre, edt_datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_contactos);

        edt_nombre = (EditText) findViewById(R.id.edtNombre);
        edt_datos = (EditText) findViewById(R.id.edtDatos);

    }

    //Método para el botón guardar
    public void Guardar(View view){

        String nombre = edt_nombre.getText().toString();
        String datos = edt_datos.getText().toString();

        SharedPreferences preferences = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferences.edit();
        obj_editor.putString(nombre, datos);
        obj_editor.commit();

        Toast.makeText(this, "El contacto ha sido guardado", Toast.LENGTH_SHORT).show();
    }

    //Método para buscar contacto
    public void Buscar(View view){

        String nombre = edt_nombre.getText().toString();

        SharedPreferences preferences2 = getSharedPreferences("agenda", Context.MODE_PRIVATE);

        String datos = preferences2.getString(nombre, "");

        if(datos.isEmpty()){

            Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();

        } else {

            edt_datos.setText(datos);

        }

    }

}