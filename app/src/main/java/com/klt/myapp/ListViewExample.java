package com.klt.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListViewExample extends AppCompatActivity {

    private TextView tv1;
    private ListView lv1;

    private String nombres[] = {"Samuel", "Valentina", "Marcos", "María"};
    private int edades[] = {18, 25, 32, 12};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view);

        tv1 = (TextView) findViewById(R.id.tv1);
        lv1 = (ListView) findViewById(R.id.listView1);

        //Objeto de tipo ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_example, nombres);
        lv1.setAdapter(adapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                tv1.setText("La edad de "+lv1.getItemAtPosition(i) + " es " + edades[i] + " años");
            }
        });

    }
}