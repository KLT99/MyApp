package com.klt.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AlmacenamientoExterno extends AppCompatActivity {

    private EditText edt_nombre, edt_contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almacenamiento_externo);

        edt_nombre = (EditText) findViewById(R.id.edtName);
        edt_contenido = (EditText) findViewById(R.id.edtContent);

    }

    //Método Guardar
    public void Guardar(View view){

        String nombre = edt_nombre.getText().toString();
        String contenido = edt_contenido.getText().toString();

        try {
            //permite guardar la ruta donde se guardara la SD
            File tarjetaSD = Environment.getExternalStorageDirectory(); //ayuda a recuperar la ruta
            Toast.makeText(this, tarjetaSD.getPath(), Toast.LENGTH_SHORT).show();

            File rutaArchivo = new File(tarjetaSD.getPath(), nombre);
            OutputStreamWriter crearArchivo = new OutputStreamWriter(openFileOutput(nombre, Activity.MODE_PRIVATE));

            crearArchivo.write(contenido);
            crearArchivo.flush();
            crearArchivo.close();

            Toast.makeText(this, "Guardado correctamente", Toast.LENGTH_SHORT).show();
            edt_nombre.setText("");
            edt_contenido.setText("");

        } catch (IOException e){
            Toast.makeText(this, "No se pudo guardar: "+e, Toast.LENGTH_SHORT).show();
        }
    }

    //Método Consultar
    public void Consultar(View view){

        String nombre = edt_nombre.getText().toString();

        try {

            File tarjetaSD = Environment.getExternalStorageDirectory();
            File rutaArchivo = new File(tarjetaSD.getPath(), nombre);
            InputStreamReader abrirArchivo = new InputStreamReader(openFileInput(nombre));

            BufferedReader leerArchivo = new BufferedReader(abrirArchivo);

            String linea = leerArchivo.readLine();
            String contenidoCompleto = "";

            while (linea.isEmpty()){
                contenidoCompleto = contenidoCompleto + linea + "\n";
                linea = leerArchivo.readLine();
            }

            leerArchivo.close();
            abrirArchivo.close();

            edt_contenido.setText(contenidoCompleto);

        } catch (IOException e){
            Toast.makeText(this, "Error al leer el archivo: "+e, Toast.LENGTH_SHORT).show();
        }

    }
}