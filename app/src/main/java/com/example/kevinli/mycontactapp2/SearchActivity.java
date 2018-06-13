package com.example.kevinli.mycontactapp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("myContactApp", "SearchActivity: Retrieving Extra Message");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Get Intent that started the activity
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //SET THE STRING IN THE TEXTVIEW
        TextView textView = findViewById(R.id.textView2);
        textView.setText(message);
    }
}