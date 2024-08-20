package com.klt.myapp;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;


public class Web extends AppCompatActivity {

    WebView webV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webV = (WebView) findViewById(R.id.wb1);

        String url = getIntent().getStringExtra("sitioWeb");
        webV.setWebViewClient(new WebViewClient());
        webV.loadUrl("https://www."+url);
    }

    public void Close(View view){

        finish();
    }

}