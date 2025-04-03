package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private EditText emailFeild, passwordFeild;
    private MaterialButton loginButton;
    private SharedPreferences sharedPreferences;

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
        //register page navigation
        TextView register_text = findViewById(R.id.register_text);
        register_text.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        });
        //login functionalities
        emailFeild = findViewById(R.id.email);
        passwordFeild = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        loginButton.setOnClickListener(v -> userLogin());
    }

    private void userLogin(){
        String email = emailFeild.getText().toString().trim();
        String password = passwordFeild.getText().toString().trim();

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "All fields are require!", Toast.LENGTH_SHORT).show();
            return;
        }
        //storing user details
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();

        //home page navigation
        Intent intent = new Intent(MainActivity.this, Home.class);
        intent.putExtra("email",email);
        startActivity(intent);
        finish();
    }
}