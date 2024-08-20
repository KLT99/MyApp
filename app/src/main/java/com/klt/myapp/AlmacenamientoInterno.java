package com.klt.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AlmacenamientoInterno extends AppCompatActivity {

    private EditText edt_Bitacora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almacenamiento_interno);

        edt_Bitacora = (EditText) findViewById(R.id.edtBitacora);

        String archivos[] = fileList();

        if (ArchivoExiste(archivos, "bitacora.txt")){

            try {

                InputStreamReader archivo = new InputStreamReader(openFileInput("bitacora.txt"));
                //la clase buffer nos permiter leer un archivo que hemos abierto previamente
                BufferedReader br = new BufferedReader(archivo);
                //.readline lee la primer linea que se encuentra
                String linea = br.readLine();
                String bitacoraCompleta = "";

                //pone todo_el texto que se encuentre dentro del archivo.txt en la variable linea
                while(linea !=null){

                    bitacoraCompleta = bitacoraCompleta + linea + "\n";
                    linea = br.readLine();

                }
                br.close();
                archivo.close();
                edt_Bitacora.setText(bitacoraCompleta);

            } catch (IOException e) {
                Toast.makeText(this, "Error InputStreamReader: "+e, Toast.LENGTH_SHORT).show();
            }
        }

    }

    private boolean ArchivoExiste(String[] archivos, String NombreArchivo) {

        for (int i=0; i<archivos.length; i++)

            if (NombreArchivo.equals(archivos[i]))
                return true;
        return false;
    }

    //Método para el boton guardar
    public void Guardar(View view){

        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("bitacora.txt", Activity.MODE_PRIVATE));
            archivo.write(edt_Bitacora.getText().toString());
            //metodo flush limpia los datos de buffer
            archivo.flush();
            archivo.close();

        } catch (IOException e) {
            Toast.makeText(this, "Error OutputStreamWriter: "+e, Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, "Se ha guardado correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }
}