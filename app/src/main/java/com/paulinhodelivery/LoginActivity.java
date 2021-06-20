package com.paulinhodelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.paulinhodelivery.data.User;

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
                Toast.makeText(getApplicationContext(), "Digite o usuário!", Toast.LENGTH_LONG).show();
                return;
            }

            if (passTxt.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Digite a senha!", Toast.LENGTH_LONG).show();
                return;
            }

            User user = new Gson().fromJson(mockUSer, User.class);
            Log.d("LSDLSKD", "SLDKSLDKSLKSLDKSLDKSLDKSLDKSLDKLSD user " + user.getUser() + " pass " + user.getPassword());
            Log.d("LSDLSKD", "SLDKSLDKSLKSLDKSLDKSLDKSLDKSLDKLSD user edit " + userTxt + " pass " + passTxt);

            if (userTxt.equals(user.getUser()) && passTxt.equals(user.getPassword())) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("user", mockUSer);

                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Usuário ou senha inválidos!", Toast.LENGTH_LONG).show();
            }
        });
    }
}