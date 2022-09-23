package com.galleria.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.galleria.databinding.ActivityLoginBinding;
import com.galleria.ui.viewmodel.LoginViewModel;
import com.galleria.R;
import com.galleria.data.User;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel viewModel;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btsignup.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
        });

        binding.btlogin.setOnClickListener(v -> {
            String user = binding.user.getText().toString();
            String password = binding.password.getText().toString();

            viewModel.login(user, password);
        });

        viewModel.getUserEmpty().observe(this, this::userEmpty);
        viewModel.getPassEmpty().observe(this, this::passwordEmpty);
        viewModel.getLoginInvalid().observe(this, this::loginInvalid);
        viewModel.getLoginSuccess().observe(this, this::loginSuccess);
    }

    private void userEmpty(Void avoid) {
        Toast.makeText(getApplicationContext(), getString(R.string.empty_user), Toast.LENGTH_LONG).show();
    }

    private void passwordEmpty(Void avoid) {
        Toast.makeText(getApplicationContext(), getString(R.string.empty_password), Toast.LENGTH_LONG).show();
    }

    private void loginInvalid(Void avoid) {
        Toast.makeText(getApplicationContext(), getString(R.string.incorrect_value), Toast.LENGTH_LONG).show();
    }

    private void loginSuccess(User user) {
        final Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.putExtra("user", user);

        startActivity(intent);
    }
}