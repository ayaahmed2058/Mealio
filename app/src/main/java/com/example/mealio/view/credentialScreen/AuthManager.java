package com.example.mealio.view.credentialScreen;

import android.util.Patterns;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class AuthManager {

    private FirebaseAuth firebaseAuth;
    private final String PASSWORD_PATTERN =
            "^" +                           //at least 1 digit
                    "(?=.*[0-9])" +         // "(?=.*[a-zA-Z])" +      //any letter
                    ".{8,}" +               //at least 8 characters
                    "$";

    public AuthManager() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public void login(String email, String password, AuthenticationCallback callback) {

        if (email.isEmpty() || password.isEmpty()) {
            callback.onFailure("Email and password cannot be empty.");
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess("Login Successful!");
                    } else {
                        callback.onFailure(task.getException().getLocalizedMessage());
                    }
                });
    }

    public void signUp(String email, String password, String confirmPassword, AuthenticationCallback callback) {

        if (!validateData(email, password, confirmPassword, callback)) {
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        firebaseAuth.getCurrentUser().sendEmailVerification();
                        firebaseAuth.signOut();
                        callback.onSuccess("Successfully created account. Check email to verify.");
                    } else {
                        callback.onFailure(task.getException().getLocalizedMessage());
                    }
                });
    }

    private boolean validateData(String email, String password, String confirmPassword, AuthenticationCallback callback) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            callback.onFailure("Invalid email format.");
            return false;
        }
        if (!password.matches(PASSWORD_PATTERN)) {
            callback.onFailure("Password must be at least 8 characters and contain at least one digit.");
            return false;
        }
        
        if (!password.equals(confirmPassword)) {
            callback.onFailure("Passwords do not match.");
            return false;
        }
        return true;
    }

    public interface AuthenticationCallback {
        void onSuccess(String message);
        void onFailure(String error);
    }
}

