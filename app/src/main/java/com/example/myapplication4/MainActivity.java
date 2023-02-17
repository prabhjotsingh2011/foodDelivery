package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ContactModel> arrContacts=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.RecyclerContact);
        FloatingActionButton btnOpenDialog=findViewById(R.id.btnOpenDialog);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        arrContacts.add(new ContactModel(R.drawable.ic_launcher_background,"Aman","2542547859"));
        arrContacts.add(new ContactModel(R.drawable.ic_launcher_background,"raman","125474858"));
        arrContacts.add(new ContactModel(R.drawable.ic_launcher_background,"kamal","916276584"));
        arrContacts.add(new ContactModel(R.drawable.ic_launcher_background,"piyush","58418415258"));
        arrContacts.add(new ContactModel(R.drawable.ic_launcher_background,"kuran","2542547857459"));
        arrContacts.add(new ContactModel(R.drawable.ic_launcher_background,"Manish","5478578578785"));
        arrContacts.add(new ContactModel(R.drawable.ic_launcher_background,"Aabab","57758378387"));
        arrContacts.add(new ContactModel(R.drawable.ic_launcher_background,"ankit","778838538638"));
        arrContacts.add(new ContactModel(R.drawable.ic_launcher_background,"Prabhjot","8388388638"));
        arrContacts.add(new ContactModel(R.drawable.ic_launcher_background,"Abhay","8888838383686"));
        arrContacts.add(new ContactModel(R.drawable.ic_launcher_background,"simran","88886538"));
        arrContacts.add(new ContactModel(R.drawable.ic_launcher_background,"neha","2542547888659"));
        arrContacts.add(new ContactModel(R.drawable.ic_launcher_background,"sohan","8988989893"));
        arrContacts.add(new ContactModel(R.drawable.ic_launcher_background,"karan","8968958565"));


        RecyclerContactAdapter adapter= new RecyclerContactAdapter(this,arrContacts);
        recyclerView.setAdapter(adapter);


        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_update_lay);

                EditText editText=dialog.findViewById(R.id.edtName);
                EditText edtNumber=dialog.findViewById(R.id.edtNumber);
                Button btnAction= dialog.findViewById(R.id.edtAction);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name="",number="";
                        if(!editText.getText().toString().equals("") && !edtNumber.getText().toString().equals("")){
                             name= editText.getText().toString();
                             number=edtNumber.getText().toString();
                        }else {
                            Toast.makeText(MainActivity.this, "Please enter all the details ", Toast.LENGTH_SHORT).show();
                        }
                        
                        arrContacts.add(new ContactModel(name,number));
                        adapter.notifyItemInserted(arrContacts.size()-1);
                        recyclerView.smoothScrollToPosition(arrContacts.size()-1);

                        dialog.dismiss();
                    }
                });

                        dialog.show();
                Window window= dialog.getWindow();
                window.setLayout(1000,1000);
            }
        });


    }
}