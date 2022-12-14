package com.galleria.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.galleria.data.User;
import com.google.gson.Gson;

public class LoginViewModel extends ViewModel {

    String mockUSer = "{" +
            "user: 'enrico.gallani@gmail.com', " +
            "password: '12345', " +
            "name: 'Enrico Gallani' " +
            "}";

    private final MutableLiveData<Void> userEmpty;
    private final MutableLiveData<Void> passEmpty;
    private final MutableLiveData<Void> loginInvalid;
    private final MutableLiveData<User> loginSuccess;

    public LoginViewModel() {
        userEmpty = new MutableLiveData<>();
        passEmpty = new MutableLiveData<>();
        loginInvalid = new MutableLiveData<>();
        loginSuccess = new MutableLiveData<>();
    }

    public void login(String email, String password) {
        if (email.isEmpty()) {
            userEmpty.setValue(null);
        } else if (password.isEmpty()) {
            passEmpty.setValue(null);
        } else {
            final User user = new Gson().fromJson(mockUSer, User.class);

            if (email.equals(user.getUser()) && password.equals(user.getPassword())) {
                loginSuccess.setValue(null);
            } else {
                loginInvalid.setValue(null);
            }
        }
    }

    public MutableLiveData<Void> getUserEmpty() {
        return userEmpty;
    }

    public MutableLiveData<Void> getPassEmpty() {
        return passEmpty;
    }

    public MutableLiveData<Void> getLoginInvalid() {
        return loginInvalid;
    }

    public MutableLiveData<User> getLoginSuccess() {
        return loginSuccess;
    }
}