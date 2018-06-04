package com.example.kevinli.mycontactapp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SearchMethod extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search);

            Intent intent = getIntent();
            String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

            TextView textView = findViewById(R.id.editText_Search);
            textView.setText(message);


    }
}
