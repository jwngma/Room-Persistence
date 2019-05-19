package com.dagger.room_persistence.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dagger.room_persistence.R;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button add_user, viewBtn, delete_btn, update_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add_user=findViewById(R.id.add_user);
        add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(intent);

            }
        });


    }
}
