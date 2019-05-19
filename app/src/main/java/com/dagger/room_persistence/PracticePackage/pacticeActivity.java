package com.dagger.room_persistence.PracticePackage;

import android.arch.persistence.room.Room;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dagger.room_persistence.R;

import java.util.List;

public class pacticeActivity extends AppCompatActivity {
    private static final String TAG = "pacticeActivity";
    public AppDatabase appDatabase;
    private EditText id_edt, username_edt, email_edt, age_edt;
    private Button addBtn, ViewBtn, updateBtn, deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pactice);
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "userssdb").allowMainThreadQueries().build();
        initAll();

        initSave();
        Viewdata();


    }



    private void initAll() {

        id_edt = findViewById(R.id.idd);
        username_edt = findViewById(R.id.usernamee);
        email_edt = findViewById(R.id.useremaill);
        age_edt = findViewById(R.id.user_age);

        addBtn = findViewById(R.id.saveBtnn);
        ViewBtn = findViewById(R.id.viewBtnn);
        updateBtn = findViewById(R.id.updateBtnn);
        deleteBtn = findViewById(R.id.deleteBtnn);
    }

    private void initSave() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = Integer.parseInt(id_edt.getText().toString());
                String username = username_edt.getText().toString();
                String email = email_edt.getText().toString();
                int age=Integer.parseInt(age_edt.getText().toString());



                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(pacticeActivity.this, "User name is Empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(pacticeActivity.this, "email is Empty", Toast.LENGTH_SHORT).show();
                }  else {

                    User user= new User();
                    user.setId(id);
                    user.setUsername(username);
                    user.setUseremail(email);
                    user.setAge(age);

                    appDatabase.dao().AddUser(user);
                    Toast.makeText(pacticeActivity.this, "new User is Added", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }

    private void Viewdata() {
        ViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<User> userList= appDatabase.dao().getUsers();
                String info="";
                for (User user:userList){
                    int id= user.getId();
                    int age= user.getAge();
                    String name=user.getUsername();
                    String email= user.getUseremail();
                    info=info+"ID: "+id+"\nUsername: "+name+"\n Email: "+email+"\nAge: "+age+"\n\n";
                }
                ShowAlert("Result",info);

            }
        });

    }

    public  void ShowAlert(String title, String message){

        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
