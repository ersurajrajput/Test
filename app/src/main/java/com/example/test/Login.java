package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.Model.DBHelper;

public class Login extends AppCompatActivity {
    EditText username,password;
    TextView forgetpassword;
    Button btn_login,btn_signup;
    String uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    username = findViewById(R.id.et_username);
    password = findViewById(R.id.et_password);
    btn_login = findViewById(R.id.btn_login);
    btn_signup = findViewById(R.id.btn_signup);

    btn_signup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent i = new Intent(Login.this,Signup.class);
            startActivity(i);
        }
    });
    btn_login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DBHelper db = new DBHelper(Login.this);
            boolean result = db.existingUser(username.getText().toString(),password.getText().toString());
            if(!result){
                Toast.makeText(Login.this,"Wrong Credentials",Toast.LENGTH_SHORT).show();
            }
            else {

                Intent i = new Intent(Login.this,MainActivity.class);
                startActivity(i);
            }
        }
    });



    }
}