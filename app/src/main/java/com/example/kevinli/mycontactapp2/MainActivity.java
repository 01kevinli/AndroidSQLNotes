package com.example.kevinli.mycontactapp2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editPhone;
    EditText editAddress;
    EditText editSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_Name);
        editPhone = findViewById(R.id.editText_Phone);
        editAddress = findViewById(R.id.editText_Address);
        editSearch = findViewById(R.id.editText_Search);
        myDb = new DatabaseHelper(this);
        Log.d("MyContactApp","MainActivity: instantiated MyDb");
    }

    public void addData(View view){
        Log.d("MyContactApp","MainActivity: Button Action Failed To Execute");

        boolean isInserted = myDb.insertData(editName.getText().toString(),editPhone.getText().toString(),editAddress.getText().toString());
        if (isInserted == true){
            Toast.makeText(MainActivity.this, "Success - contact inserted", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Failed - contact not inserted", Toast.LENGTH_LONG).show();

        }

    }

    public void viewData (View view){
        Cursor res = myDb.getAllData();
        Log.d("MyContactApp","MainActivity: viewData: received cursor");

        if(res.getCount() == 0){
            showMessage("Error", "No data found in database");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){

            //Append res column 0,1,2,3 to the buffer - see StringBuffer and Cursor api's
            //Delimit each of the 'appends" with line feed "\n";

            buffer.append("Name: " + res.getString(1) +"\n" +
                    "");
            buffer.append("Address:" + res.getString(3) + "\n");
            buffer.append("Number:" + res.getString(2) + "\n");
            buffer.append("\n\n");
        }

        showMessage("Data", buffer.toString());
    }

    private void showMessage(String title, String message) {

        Log.d("MyContactApp","MainActivity: showmessage: assembling AlertDialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public static final String EXTRA_MESSAGE = "com.example.kevinli.mycontactapp.MESSAGE";

    public void SearchRecord(View view){
       boolean noContacts = true;
        Log.d("myContactApp", "MainActivity: Launching SearchActivity");
        Intent intent = new Intent(this, SearchActivity.class);
        Log.d("myContactApp", "Intent has been intialized");
        Cursor res = myDb.getAllData();
        Log.d("myContactApp", "data has been got boi");
        StringBuffer buffer = new StringBuffer();
        Log.d("myContactApp", "buffer found");

        while(res.moveToNext()){
            if(res.getString(1).equals(editSearch.getText().toString())) {
                Log.d("myContactApp", "MainActivity: Equals");

                buffer.append(res.getString(1) + "\n");
                Log.d("myContactApp", "first get stirng");
                buffer.append(res.getString(2) + "\n");
                Log.d("myContactApp", "second get stirng");
                buffer.append(res.getString(3) + "\n" + "\n");
                Log.d("myContactApp", "third get stirng");
                noContacts = false;

            }
        }
        if(noContacts){
            Toast.makeText(MainActivity.this, "No Contacts Found", Toast.LENGTH_LONG).show();

        }
        else if (noContacts == false){
            intent.putExtra(EXTRA_MESSAGE, buffer.toString());
            Log.d("myContactApp", "almost there");
            startActivity(intent);
            Log.d("myContactApp", "there");
        }

    }




}