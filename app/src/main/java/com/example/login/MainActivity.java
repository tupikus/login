package com.example.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bLogin;
    EditText etUsername, etPassword;
    TextView tvRegisterLink;

    DBHelper dbHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);
        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View view) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_INFO, null, null
                , null, null, null, null);
        String LOGIN_NAME = etUsername.getText().toString();
        String LOGIN_PASSWORD = etPassword.getText().toString();
        int username_index = cursor.getColumnIndex(DBHelper.KEY_USERNAME);
        int password_index = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);
        Toast oshibka = Toast.makeText(MainActivity.this, "Неверно введены данные", Toast.LENGTH_SHORT);
        String Y = dbHelper.getDatabaseName();
        switch (view.getId()) {
            case R.id.bLogin:
                cursor.moveToFirst();
                while (cursor.moveToNext()) {
                    String a = cursor.getString(username_index);
                    String b = cursor.getString(password_index);
                    if (a.equals(LOGIN_NAME) && b.equals(LOGIN_PASSWORD)
                            && !LOGIN_NAME.equals("") && !LOGIN_PASSWORD.equals("")){
                        Log.d("mLog",Y + " " + Y);
                        startActivity(new Intent(this, User.class));
                    }
                    else {
                        Log.d("mLog",Y + " " + Y);
                    }
                }
                cursor.close();
                break;
            case R.id.tvRegisterLink:
                startActivity(new Intent(this, Register.class));
                break;
            }
        dbHelper.close();
    }
    public void onDestroy(){
        super.onDestroy();
    }
}
