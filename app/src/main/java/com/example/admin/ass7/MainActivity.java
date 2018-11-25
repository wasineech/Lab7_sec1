package com.example.admin.ass7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText txtName,txtId,txtTel,txtEmail;
    private Button btnSave;
    private ListView dataView;
    private MySQLConnect mySQLConnect;
    private List<String> items;
    private ArrayAdapter<String> adt;
    String show = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        update();


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySQLConnect.sentData(txtId.getText().toString(),txtName.getText().toString(),txtTel.getText().toString(),txtEmail.getText().toString());
                items.add(txtId.getText().toString()+"\n"+ txtName.getText().toString()+"\n"+txtTel.getText().toString()+"\n"+txtEmail.getText().toString());
                adt.notifyDataSetChanged();
            }
        });
    }

    public void update(){
        items = mySQLConnect.getData();
        adt = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, items);
        dataView.setAdapter(adt);
    }

    public void init(){
        txtId = findViewById(R.id.txtId);
        txtName = findViewById(R.id.txtName);
        txtTel = findViewById(R.id.txtTel);
        txtEmail = findViewById(R.id.txtEmail);
        btnSave = findViewById(R.id.btnSave);
        dataView = findViewById(R.id.dataView);
        mySQLConnect = new MySQLConnect(MainActivity.this);
    }
}
