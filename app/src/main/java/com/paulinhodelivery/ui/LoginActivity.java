package com.paulinhodelivery.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.paulinhodelivery.R;
import com.paulinhodelivery.data.User;
import com.paulinhodelivery.ui.HomeActivity;
import com.paulinhodelivery.ui.SignUpActivity;

public class LoginActivity extends AppCompatActivity {

    String mockUSer = "{" +
            "user: 'enrico.gallani@gmail.com', " +
            "password: '12345', " +
            "name: 'Enrico Gallani' " +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        EditText etUser = findViewById(R.id.user);
        EditText etPassword = findViewById(R.id.password);
        Button btLogin = findViewById(R.id.btlogin);
        Button btSignup = findViewById(R.id.btsignup);

        btSignup.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
        });

        btLogin.setOnClickListener(v -> {
            String userTxt = etUser.getText().toString();
            String passTxt = etPassword.getText().toString();

            if (userTxt.isEmpty()) {
                Toast.makeText(getApplicationContext(), getString(R.string.empty_user), Toast.LENGTH_LONG).show();
                return;
            }

            if (passTxt.isEmpty()) {
                Toast.makeText(getApplicationContext(), getString(R.string.empty_password), Toast.LENGTH_LONG).show();
                return;
            }

            User user = new Gson().fromJson(mockUSer, User.class);

            if (userTxt.equals(user.getUser()) && passTxt.equals(user.getPassword())) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("user", mockUSer);

                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.incorrect_value), Toast.LENGTH_LONG).show();
            }
        });
    }
}