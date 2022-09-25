package com.galleria.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.galleria.data.User;

public class SignUpViewModel extends ViewModel {

    private final MutableLiveData<Void> userEmpty;
    private final MutableLiveData<Void> passEmpty;
    private final MutableLiveData<Void> nameEmpty;
    private final MutableLiveData<User> signupSuccess;

    public SignUpViewModel() {
        userEmpty = new MutableLiveData<>();
        passEmpty = new MutableLiveData<>();
        nameEmpty = new MutableLiveData<>();
        signupSuccess = new MutableLiveData<>();
    }

    public void signup(String name, String email, String password) {
        if (email.isEmpty()) {
            userEmpty.setValue(null);
        } else if (name.isEmpty()) {
            nameEmpty.setValue(null);
        } else if (password.isEmpty()) {
            passEmpty.setValue(null);
        } else {
            signupSuccess.setValue(new User());
        }
    }

    public MutableLiveData<Void> getUserEmpty() {
        return userEmpty;
    }

    public MutableLiveData<Void> getPassEmpty() {
        return passEmpty;
    }

    public MutableLiveData<Void> getNameEmpty() {
        return nameEmpty;
    }

    public MutableLiveData<User> getSignupSuccess() {
        return signupSuccess;
    }
}