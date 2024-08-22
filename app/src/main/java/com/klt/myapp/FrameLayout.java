package com.klt.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class FrameLayout extends AppCompatActivity {

    private ImageView iv1;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);

        iv1 = findViewById(R.id.imageView);
        button = findViewById(R.id.btnHidden);

    }

    public void Ocultar(View view){

        button.setVisibility(View.INVISIBLE);
        iv1.setVisibility(View.VISIBLE);

    }

}