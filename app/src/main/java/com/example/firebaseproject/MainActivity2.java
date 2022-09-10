package com.example.firebaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

public class MainActivity2 extends AppCompatActivity {

    LinearLayout layout;
    ProgressBar progressBar;
    FireBase fb ;

    @Override
    protected void onStart() {
        super.onStart();
        progressBar = findViewById(R.id.progressBar3);
        layout = findViewById(R.id.lyout);
        fb = new FireBase(this);
        ListView lv = findViewById(R.id.lv);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                MyAdapter ma = new MyAdapter(fb.getCustomers(),MainActivity2.this);
                lv.setAdapter(ma);
            }
        }, 1000);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }
}