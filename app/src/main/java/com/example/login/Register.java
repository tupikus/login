package com.example.login;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button bRegister;
    EditText etName, etAge, etUsername, etPassword;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bRegister = (Button) findViewById(R.id.bRegister);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        bRegister.setOnClickListener(this);
        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View v) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        switch (v.getId()){
            case R.id.bRegister:
                Cursor cursor = database.query(DBHelper.TABLE_INFO, null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_USERNAME);
                    int passlIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);
                } else
                    Log.d("mLog","0 rows");
                contentValues.put(DBHelper.KEY_USERNAME, username);
                contentValues.put(DBHelper.KEY_PASSWORD, password);
                database.insert(DBHelper.TABLE_INFO, null, contentValues);
                startActivity(new Intent(this, MainActivity.class));

                break;
        }
        dbHelper.close();
    }
}
