package com.example.itubeapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.itubeapplication.R;
import com.example.itubeapplication.user.User;
import com.example.itubeapplication.user.UserDAO;
import com.example.itubeapplication.user.UserDataBase;

public class RegisterActivity extends AppCompatActivity {

    //variables
    private UserDAO userDAO; //declare user class
    EditText name_edit, username_edit, password_edit, cfm_Password_edit, phone_edit;
    Button btn_sign_up, btn_sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // set to screen
        btn_sign_up = findViewById(R.id.btn_sign_up);
        btn_sign_in = findViewById(R.id.btn_sign_in);
        name_edit = findViewById(R.id.name_edit);
        username_edit = findViewById(R.id.username_edit);
        password_edit = findViewById(R.id.password_edit);
        cfm_Password_edit = findViewById(R.id.cfm_Password_edit);
        phone_edit = findViewById(R.id.phone_edit);


        // click button sign in
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sign user up & start main
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });

        // set this user
        userDAO = Room.databaseBuilder(this, UserDataBase.class, "User")
                .allowMainThreadQueries().build().getUserDao();

        // click button sign up
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to string input
                String userName = username_edit.getText().toString().trim();
                String name = name_edit.getText().toString().trim();
                String password = password_edit.getText().toString().trim();
                String cfmPassword = cfm_Password_edit.getText().toString().trim();
                String phone = phone_edit.getText().toString().trim();

                if (userName == null || name == null || password == null || phone == null ){
                    Toast.makeText(RegisterActivity.this, "Enter all information",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // passwords match
                if (password.equals(cfmPassword)) {
                    User user = new User(userName, name, password, phone);
                    userDAO.insert(user);
                    Intent moveToLogin = new Intent(RegisterActivity.this,
                            MainActivity.class);
                    startActivity(moveToLogin);
                }
                else {
                    Toast.makeText(RegisterActivity.this,
                            "Passwords don't match, try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
