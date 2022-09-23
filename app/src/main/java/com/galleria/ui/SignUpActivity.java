package com.galleria.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.galleria.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();

        EditText etUser = findViewById(R.id.user);
        EditText etPassword = findViewById(R.id.password);
        EditText etName = findViewById(R.id.name);
        Button btSignup = findViewById(R.id.btsignup);

        btSignup.setOnClickListener(v -> {
            String userTxt = etUser.getText().toString();
            String passTxt = etPassword.getText().toString();
            String nameTxt = etName.getText().toString();

            if (userTxt.isEmpty()) {
                Toast.makeText(getApplicationContext(), getString(R.string.empty_email), Toast.LENGTH_LONG).show();
                return;
            }

            if (nameTxt.isEmpty()) {
                Toast.makeText(getApplicationContext(), getString(R.string.empty_name), Toast.LENGTH_LONG).show();
                return;
            }

            if (passTxt.isEmpty()) {
                Toast.makeText(getApplicationContext(), getString(R.string.empty_password), Toast.LENGTH_LONG).show();
                return;
            }

            Toast.makeText(getApplicationContext(), getString(R.string.correct_value), Toast.LENGTH_LONG).show();
        });
    }
}