package com.klt.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner1;
    private EditText edt1, edt2;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
*/

        edt1 = (EditText) findViewById(R.id.edtPrimerValor);
        edt2 = (EditText) findViewById(R.id.edtSegundoValor);
        tv1  = (TextView) findViewById(R.id.tvResultado);
        spinner1 = (Spinner) findViewById(R.id.spinner);

        String [] opciones = {"sumar", "restar", "multiplicar", "dividir"};

        //comunica el arreglo con el spinner con la clase ArrayAdapter
        //ArrayAdapter<Tipo de dato> nombre_objeto = new ArrayAdapter<tipo de dato> (contexto donde mostrará, tipo de spinner, nombre del vecto o arreglo que tiene los datos);
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_operaciones, opciones);
        //se coloca dentro del spinner
        spinner1.setAdapter(adapter);
    }

    //Metodo del botón
    public void Calcular(View view){

        int valor1 = Integer.parseInt(edt1.getText().toString());
        int valor2 = Integer.parseInt(edt2.getText().toString());

        String seleccion = spinner1.getSelectedItem().toString();

        if(seleccion.equals("sumar")){

            int suma = valor1 + valor2;
            String resultado = String.valueOf(suma);

            tv1.setText(resultado);

        } else if(seleccion.equals("restar")){

            int resta = valor1 - valor2;
            String resultado = String.valueOf(resta);

            tv1.setText(resultado);

        } else if(seleccion.equals("multiplicar")){

            int multiplicar = valor1 * valor2;
            String resultado = String.valueOf(multiplicar);

            tv1.setText(resultado);

        } else if(seleccion.equals("dividir")){

            if(valor2 != 0){

                int dividir = valor1 / valor2;
                String resultado = String.valueOf(dividir);

                tv1.setText(resultado);

            } else {
                Toast.makeText(this, "No se puede dividir entre 0", Toast.LENGTH_SHORT).show();
            }

        }

    }
}