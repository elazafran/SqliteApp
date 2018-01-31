package com.example.elaza.sqliteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editTextName,editTextSurname,editTextMarks;
    Button btnAddData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //este contructor nos crea la base de datos con las constantes definidas en la clase
        myDb = new DatabaseHelper(this);
        editTextName  = findViewById(R.id.editText_name);
        editTextSurname  = findViewById(R.id.editText_surname);
        editTextMarks  = findViewById(R.id.editText_marks);
        btnAddData  = findViewById(R.id.button_add);

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(editTextName.getText().toString(),editTextSurname.getText().toString(),editTextMarks.getText().toString());
                if (isInserted=true) {
                    Toast.makeText(MainActivity.this, "Se ha insertado correctamente", Toast.LENGTH_LONG).show();
                    

                }else
                    Toast.makeText(MainActivity.this, "NO se ha podido insertar correctamente", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void resetEditText(){
        editTextSurname.setText("");
        editTextName.setText("");
        editTextMarks.setText("");
    }




}
