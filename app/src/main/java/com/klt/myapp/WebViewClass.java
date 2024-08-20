package com.klt.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class WebViewClass extends AppCompatActivity {

    private EditText edt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_web_view);

        edt1 = findViewById(R.id.edt_web);

    }

    public void Navegar(View view){

        Intent i = new Intent(this, Web.class);
        i.putExtra("sitioWeb", edt1.getText().toString());
        startActivity(i);
    }
}