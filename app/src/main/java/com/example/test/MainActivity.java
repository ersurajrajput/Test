package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.Model.DBHelper;
import com.example.test.Model.TestModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ListView userlist;
    DBHelper dbHelper = new DBHelper(MainActivity.this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        userlist = findViewById(R.id.lv_userlist);
        textView = findViewById(R.id.tv_username);
        showrecords();


        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                String[] s = selectedItem.split(" ");
                dbHelper.deleteUser(s[1]);
                Toast.makeText(MainActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                showrecords();
            }
        });



    }
    public  void showrecords(){
        List<TestModel> userListModel  = dbHelper.getAll();
        ArrayList<String> dataList = new ArrayList<>();
        for (int i = 0; i<userListModel.size();i++){
            String s = "";
            String id,name,uname,email;
            id = String.valueOf(userListModel.get(i).getId());
            name = userListModel.get(i).getName();
            uname = userListModel.get(i).getUsername();
            email = userListModel.get(i).getEmail();
            s = "id:- "+id+" name:- "+name+" uname:- "+uname+" email:- "+email;
            dataList.add(s);

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,dataList);
        userlist.setAdapter(adapter);
    }
}