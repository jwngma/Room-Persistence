package com.dagger.room_persistence.Activities;

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
import com.dagger.room_persistence.Utils.MyAppDatabase;
import com.dagger.room_persistence.Utils.User;

import java.util.List;

public class AddUserActivity extends AppCompatActivity {
    private static final String TAG = "AddUserActivity";
    public  static MyAppDatabase myAppDatabase;
    private EditText id_edt, username_edt, email_edt;
    private Button saveBtn,ViewBtn,updateBtn, deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        myAppDatabase= Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"userdb").allowMainThreadQueries().build();
        id_edt=findViewById(R.id.id);
        username_edt=findViewById(R.id.username);
        email_edt=findViewById(R.id.useremail);
        saveBtn=findViewById(R.id.saveBtn);
        ViewBtn=findViewById(R.id.viewBtn);
        initSave();
        initView();
        initUpdate();
        initDelete();
    }

    private void initDelete() {
        deleteBtn=findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=Integer.parseInt(id_edt.getText().toString());
                String username=username_edt.getText().toString();
                String email=email_edt.getText().toString();


                if (TextUtils.isEmpty(username)){
                    Toast.makeText(AddUserActivity.this, "User name is Empty", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(email)){
                    Toast.makeText(AddUserActivity.this, "Email is Empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    User user= new User();
                    user.setId(id);
                    user.setName(username);
                    user.setEmail(email);

                    myAppDatabase.myDao().DeleteUser(user);
                    Toast.makeText(AddUserActivity.this, "user has been removed", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void initUpdate() {
        updateBtn=findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=Integer.parseInt(id_edt.getText().toString());
                String username=username_edt.getText().toString();
                String email=email_edt.getText().toString();

                if (TextUtils.isEmpty(username)){
                    Toast.makeText(AddUserActivity.this, "User name is Empty", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(email)){
                    Toast.makeText(AddUserActivity.this, "Email is Empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    User user= new User();
                    user.setId(id);
                    user.setName(username);
                    user.setEmail(email);

                    myAppDatabase.myDao().UpdateUser(user);
                    Toast.makeText(AddUserActivity.this, "User detail has been updated", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }

    private void initView() {
        ViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<User> userList= myAppDatabase.myDao().getUsers();
                String info= "";

                for (User user:userList){
                    int id= user.getId();
                    String name= user.getName();
                    String email=user.getEmail();
                    info=info+"\n\n"+"id: "+id+"\nUsername: "+name+"\nEmail :"+email+"\n\n";
                }

                ShowMessage("result",info);

            }
        });

    }

    private void initSave() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id=Integer.parseInt(id_edt.getText().toString());
                String username=username_edt.getText().toString();
                String email=email_edt.getText().toString();
                if (TextUtils.isEmpty(username)){
                    Toast.makeText(AddUserActivity.this, "User name is Empty", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(email)){
                    Toast.makeText(AddUserActivity.this, "Email is Empty", Toast.LENGTH_SHORT).show();
                }
                else {

                    User user= new User();
                    user.setId(id);
                    user.setEmail(email);
                    user.setName(username);

                    myAppDatabase.myDao().AddUser(user);
                    Toast.makeText(AddUserActivity.this, "New User has been Added", Toast.LENGTH_SHORT).show();

                }



            }
        });
    }

    public  void ShowMessage(String title, String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }
}
