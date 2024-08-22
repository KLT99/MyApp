package com.klt.myapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.klt.myapp.BD.AdminSQLiteOpenHelper;

public class SQLiteClass extends AppCompatActivity {

    private EditText edt_codigo, edt_descripcion, edt_precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_class);

        edt_codigo = (EditText) findViewById(R.id.edt_codigo);
        edt_descripcion = (EditText) findViewById(R.id.edtDesc);
        edt_precio = (EditText) findViewById(R.id.edtPrice);

    }

    //Método para registrar los productos
    public void Insert(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BasedeDatos = admin.getWritableDatabase(); //metodo para abrir la bd para modo lectura y escritura

        String codigo = edt_codigo.getText().toString();
        String descripcion = edt_descripcion.getText().toString();
        String precio = edt_precio.getText().toString();

        //Valida que el usuario haya registrado datos en los campos
        if (!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){

            //guardar los datos en la BD que el usuario a escrito
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            //inserta los datos en la tabla
            BasedeDatos.insert("articulos", null, registro);

            BasedeDatos.close();

            edt_codigo.setText("");
            edt_descripcion.setText("");
            edt_precio.setText("");

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(this, "LLene todos los campos", Toast.LENGTH_SHORT).show();

        }
    }
    //Método para registrar los productos

    //Método para consultar los productos
    public void Select(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BasedeDatos = admin.getWritableDatabase();

        String codigo = edt_codigo.getText().toString();
        /*Cursor fila2 = BasedeDatos.rawQuery("SELECT * FROM articulos", null);

        if (fila2.moveToFirst()){

            edt_codigo.setText(fila2.getString(0));
            edt_descripcion.setText(fila2.getString(1));
            edt_precio.setText(fila2.getString(2));

            BasedeDatos.close();

        } else {

            Toast.makeText(this, "No existe el artículo", Toast.LENGTH_SHORT).show();
            BasedeDatos.close();
        }*/
        if (!codigo.isEmpty()){

            //nos apoya al momento de seleccionar un codigo
            Cursor fila = BasedeDatos.rawQuery("SELECT descripcion, precio FROM articulos WHERE codigo=" + codigo, null);

            //verifica que si la consulta tiene valores
            if (fila.moveToFirst()){

                edt_descripcion.setText(fila.getString(0));
                edt_precio.setText(fila.getString(1));

                BasedeDatos.close();

            } else {

                Toast.makeText(this, "No existe el artículo", Toast.LENGTH_SHORT).show();
                BasedeDatos.close();
            }

        } else {
            Toast.makeText(this, "Ingrese código del producto", Toast.LENGTH_SHORT).show();
        }

    }
    //metodo buscar

    //Método eliminar
    public void Delete(View view){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase base_datos = admin.getWritableDatabase();

        String codigo = edt_codigo.getText().toString();

        if(!codigo.isEmpty()){

            int cantidad = base_datos.delete("articulos", "codigo="+codigo, null); //returna la cantidad de articulos borrados
            base_datos.close();

            edt_codigo.setText("");
            edt_descripcion.setText("");
            edt_precio.setText("");

            if(cantidad == 1){

                Toast.makeText(this, "Artículo eliminado correctamente", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, "El artículo no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Introduce el código del artículo", Toast.LENGTH_SHORT).show();
        }

    }
    //metodo eliminar


    //Método modificar
    public void Update(View view){

        AdminSQLiteOpenHelper adminUpdate = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase database = adminUpdate.getWritableDatabase();

        String codigo = edt_codigo.getText().toString();
        String descripcion = edt_descripcion.getText().toString();
        String precio = edt_precio.getText().toString();

        //Valida que el usuario haya registrado datos en los campos
        if (!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){

            ContentValues registro = new ContentValues();

            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            int cantidad = database.update("articulos", registro, "codigo=" + codigo,null);
            database.close();

            if (cantidad == 1){
                Toast.makeText(this, "Artículo modificado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El artículo no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "LLene todos los campos", Toast.LENGTH_SHORT).show();
        }

    }
    //Método modificar

}