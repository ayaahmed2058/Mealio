package com.example.mealio.view.credentialScreen;

import android.util.Patterns;
import com.google.firebase.auth.FirebaseAuth;
import android.app.Activity;
import android.content.Intent;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.FirebaseUser;


public class AuthManager {

    private final FirebaseAuth firebaseAuth;
    private final GoogleSignInClient googleSignInClient;


    private final String PASSWORD_PATTERN =
            "^" +
                    "(?=.*[0-9])" +         // at least 1 digit
                    ".{8,}" +               // at least 8 characters
                    "$";

    public AuthManager(Activity activity) {
        this.firebaseAuth = FirebaseAuth.getInstance();

        // Configure Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("140999529852-52naf6ea7m3olbfeadi9u6d0t4m846bm.apps.googleusercontent.com")
                .requestEmail()
                .build();

        this.googleSignInClient = GoogleSignIn.getClient(activity, gso);
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

    public GoogleSignInClient getGoogleSignInClient() {
        return googleSignInClient;
    }

    public void handleGoogleSignInResult(Intent data, AuthenticationCallback callback) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            if (account != null) {
                firebaseAuthWithGoogle(account.getIdToken(), callback);
            }
        } catch (ApiException e) {
            callback.onFailure("Google Sign-In failed: " + e.getMessage());
        }
    }

    private void firebaseAuthWithGoogle(String idToken, AuthenticationCallback callback) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        callback.onSuccess("Google Sign-In Successful! Welcome, " + user.getDisplayName());
                    } else {
                        callback.onFailure(task.getException().getLocalizedMessage());
                    }
                });
    }

    public interface AuthenticationCallback {
        void onSuccess(String message);
        void onFailure(String error);
    }
}
