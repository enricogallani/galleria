package com.paulinhodelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.paulinhodelivery.data.User;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText etUser = findViewById(R.id.user);
        EditText etPassword = findViewById(R.id.password);
        EditText etName = findViewById(R.id.name);
        Button btSignup = findViewById(R.id.btsignup);

        btSignup.setOnClickListener(v -> {
            String userTxt = etUser.getText().toString();
            String passTxt = etPassword.getText().toString();
            String nameTxt = etName.getText().toString();

            if (userTxt.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Digite o e-mail!", Toast.LENGTH_LONG).show();
                return;
            }

            if (nameTxt.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Digite o nome!", Toast.LENGTH_LONG).show();
                return;
            }

            if (passTxt.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Digite a senha!", Toast.LENGTH_LONG).show();
                return;
            }

            Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso!!!", Toast.LENGTH_LONG).show();
        });
    }
}