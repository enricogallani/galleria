package com.galleria.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.galleria.R;
import com.galleria.data.User;
import com.galleria.databinding.ActivitySignUpBinding;
import com.galleria.ui.viewmodel.SignUpViewModel;

public class SignUpActivity extends AppCompatActivity {

    private SignUpViewModel viewModel;
    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());

        getSupportActionBar().hide();

        binding.btSignup.setOnClickListener(v -> {
            String user = binding.user.getText().toString();
            String pass = binding.password.getText().toString();
            String name = binding.name.getText().toString();

            viewModel.signup(name, user, pass);

            viewModel.getUserEmpty().observe(this, this::userEmpty);
            viewModel.getPassEmpty().observe(this, this::passwordEmpty);
            viewModel.getNameEmpty().observe(this, this::nameEmpty);
            viewModel.getSignupSuccess().observe(this, this::signUpSuccess);
        });
    }

    private void userEmpty(Void avoid) {
        Toast.makeText(getApplicationContext(), getString(R.string.empty_user), Toast.LENGTH_LONG).show();
    }

    private void passwordEmpty(Void avoid) {
        Toast.makeText(getApplicationContext(), getString(R.string.empty_password), Toast.LENGTH_LONG).show();
    }

    private void nameEmpty(Void avoid) {
        Toast.makeText(getApplicationContext(), getString(R.string.empty_name), Toast.LENGTH_LONG).show();
    }

    private void signUpSuccess(User user) {
        Toast.makeText(getApplicationContext(), getString(R.string.correct_value), Toast.LENGTH_LONG).show();
    }
}