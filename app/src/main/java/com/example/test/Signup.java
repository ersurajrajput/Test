package com.example.test;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.Model.DBHelper;
import com.example.test.Model.TestModel;

import java.util.Calendar;

public class Signup extends AppCompatActivity {
    EditText name,username,email,password,cpassword,dob;
    Button btn_signup,btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        name = findViewById(R.id.et_name);
        username = findViewById(R.id.et_username);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        cpassword = findViewById(R.id.et_cpassword);
        dob = findViewById(R.id.et_dob);
        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);




        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Signup.this,Login.class);
                startActivity(i);
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbHelper = new DBHelper(Signup.this);
                TestModel testModel = new TestModel(-1,name.getText().toString(),username.getText().toString(),email.getText().toString(),password.getText().toString(),dob.getText().toString());
                boolean inserted = dbHelper.insertUser(testModel);
                if (name.getText().toString().isEmpty()){
                    name.setError("Required");
                    return;
                }else if (username.getText().toString().isEmpty()){
                    username.setError("Required");
                    return;
                }else if (email.getText().toString().isEmpty()){
                    email.setError("Required");
                    return;
                }else if (password.getText().toString().isEmpty()){
                    password.setError("Required");
                    return;
                }else if (cpassword.getText().toString().isEmpty()){
                    cpassword.setError("Required");
                    return;
                }else{
                    if(!password.getText().toString().equals(cpassword.getText().toString())){
                        Toast.makeText(Signup.this,"Password not matching",Toast.LENGTH_SHORT).show();

                    }else {
                        if (inserted){
                            Toast.makeText(Signup.this,"Success",Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(Signup.this,MainActivity.class);
                            startActivity(i);


                        }else {
                            Toast.makeText(Signup.this,"user exsist",Toast.LENGTH_SHORT).show();

                        }
                    }
                }


            }
        });




    }
}