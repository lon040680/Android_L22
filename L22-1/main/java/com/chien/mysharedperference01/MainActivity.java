package com.chien.mysharedperference01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, password;
    Context context;
    SharedPreferences shared;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        name = findViewById(R.id.name);
        password = findViewById(R.id.pw);

        shared = getSharedPreferences("info", MODE_PRIVATE);
        name.setText(shared.getString("name", "noData"));
        password.setText(shared.getString("pw","noData"));
    }

    public void onClick(View view) {
        editor = shared.edit(); //開方法

        String s1 = name.getText().toString().trim();
        String s2 = password.getText().toString().trim();

        editor.putString("name", s1); //記憶帳號
        editor.putString("pw", s2); //記密碼
        editor.commit(); //送出

        Toast.makeText(context, "Comframe", Toast.LENGTH_LONG).show();
    }

    public void clean(View view) {
        editor = shared.edit();
        editor.clear().commit();
        Toast.makeText(context, "Clear OK", Toast.LENGTH_SHORT).show();
    }
}