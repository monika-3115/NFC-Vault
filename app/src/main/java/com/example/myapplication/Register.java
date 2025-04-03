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

public class Register extends AppCompatActivity {

    private EditText nameFeild, emailFeild, passwordFeild, confirmPassFeild;
    private MaterialButton registerButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        nameFeild = findViewById(R.id.name);
        emailFeild = findViewById(R.id.email);
        registerButton = findViewById(R.id.register_button);
        passwordFeild = findViewById(R.id.password);
        confirmPassFeild = findViewById(R.id.confirmPass);
        TextView login_text = findViewById(R.id.login_text);
        sharedPreferences = getSharedPreferences("UserData",MODE_PRIVATE);
        registerButton.setOnClickListener(v -> registerUser());
        login_text.setOnClickListener(v -> {
            Intent intent = new Intent(Register.this, MainActivity.class);
            startActivity(intent);
        });
    }
    private void registerUser(){
        String email = emailFeild.getText().toString().trim();
        String password = passwordFeild.getText().toString().trim();
        String confirmPassword = confirmPassFeild.getText().toString().trim();
        String name = nameFeild.getText().toString().trim();

        if(email.isEmpty() || name.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!password.equals(confirmPassword)){
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            return;
        }
        //storing user details (name and email)
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",name);
        editor.putString("email",email);
        editor.putString("password",password);
        editor.apply();

        //home page navigation
        Intent intent = new Intent(Register.this, Home.class);
        intent.putExtra("name", name);
        startActivity(intent);
        finish();
    }
}