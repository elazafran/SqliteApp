package com.example.elaza.sqliteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //este contructor nos crea la base de datos con las constantes definidas en la clase
        myDb = new DatabaseHelper(this);

    }

}
