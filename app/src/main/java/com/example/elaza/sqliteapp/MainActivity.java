package com.example.elaza.sqliteapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editTextName,editTextSurname,editTextMarks,editTextId;
    Button btnAddData,btnViewAll,btnUpdate,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //este contructor nos crea la base de datos con las constantes definidas en la clase
        myDb = new DatabaseHelper(this);
        editTextName  = findViewById(R.id.editText_name);
        editTextSurname  = findViewById(R.id.editText_surname);
        editTextMarks  = findViewById(R.id.editText_marks);
        editTextId = findViewById(R.id.editText_id);
        btnAddData  = findViewById(R.id.button_add);
        btnViewAll = findViewById(R.id.button_view);
        btnUpdate = findViewById(R.id.button_update);
        btnDelete = findViewById(R.id.button_delete);

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

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount()==0){
                    //mostramos un mensaje
                    showMessage("Error","No se encontró datos");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id : "+res.getString(0)+"\n");
                    buffer.append("Name : "+res.getString(1)+"\n");
                    buffer.append("Surname : "+res.getString(2)+"\n");
                    buffer.append("Marks : "+res.getString(3)+"\n");
                }
                //mostramos toda la información
                showMessage("Data",buffer.toString());
            }

        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateData(editTextId.getText().toString(),editTextName.getText().toString(),editTextSurname.getText().toString(),editTextMarks.getText().toString());
                if (isUpdate==true){
                    Toast.makeText(MainActivity.this, "Se ha actualizado correctamente", Toast.LENGTH_LONG).show();


                }else
                    Toast.makeText(MainActivity.this, "NO se ha podido actualizar correctamente", Toast.LENGTH_LONG).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer isDelete = myDb.deleteData(editTextId.getText().toString());
                if (isDelete>0){
                    Toast.makeText(MainActivity.this, "Se han borrado ", Toast.LENGTH_LONG).show();


                }else
                    Toast.makeText(MainActivity.this, "NO se ha podido borrar", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.show();
    }
    //vaciar los edit text
    public void resetEditText(){
        editTextSurname.setText("");
        editTextName.setText("");
        editTextMarks.setText("");
    }




}
