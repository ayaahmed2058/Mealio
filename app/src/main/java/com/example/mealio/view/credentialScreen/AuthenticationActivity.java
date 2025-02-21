package com.example.mealio.view.credentialScreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.Fade;
import androidx.transition.Scene;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import com.example.mealio.R;
import com.example.mealio.view.MainScreenActivity;
import com.google.android.material.snackbar.Snackbar;

public class AuthenticationActivity extends AppCompatActivity {

    private Scene loginScene;
    private Scene signUpScene;
    private boolean isSignUp = false;
    private FrameLayout container;
    private EditText emailField, passwordField, confirmPasswordField, usernameField;
    private Button btnAuth;
    private AuthManager authManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        authManager = new AuthManager();
        container = findViewById(R.id.container);

        loginScene = Scene.getSceneForLayout(container, R.layout.login_scene, this);
        signUpScene = Scene.getSceneForLayout(container, R.layout.sign_up_scene, this);

        loginScene.enter();
        setupSceneSwitch();
    }

    private void setupSceneSwitch() {
        container.post(() -> {
            TextView tvSwitch = container.findViewById(R.id.tvSwitch);
            tvSwitch.setOnClickListener(v -> switchScene());

        });
    }

    private void switchScene() {
        Transition fadeTransition = new Fade();
        isSignUp = !isSignUp;

        if (isSignUp) {
            TransitionManager.go(signUpScene, fadeTransition);
            setupSignUp();
        } else {
            TransitionManager.go(loginScene, fadeTransition);
            setupLogin();
        }

        setupSceneSwitch();
    }

    private void setupLogin() {
        container.post(() -> {
            emailField = container.findViewById(R.id.et_email);
            passwordField = container.findViewById(R.id.et_password);
            btnAuth = container.findViewById(R.id.btnAuth);

            if (btnAuth != null) {
                btnAuth.setOnClickListener(v -> handleLogin());
            }
        });
    }

    private void setupSignUp() {
        container.post(() -> {
            usernameField = container.findViewById(R.id.et_full_name);
            emailField = container.findViewById(R.id.et_email);
            passwordField = container.findViewById(R.id.et_password);
            confirmPasswordField = container.findViewById(R.id.et_confirmPassword);
            btnAuth = container.findViewById(R.id.btnAuth);

            if (btnAuth != null) {
                btnAuth.setOnClickListener(v -> handleSignUp());
            }
        });
    }

    private void handleLogin() {
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Snackbar.make(emailField,"Please enter email and password", Snackbar.LENGTH_LONG).show();
            return;
        }

        authManager.login(email, password, new AuthManager.AuthenticationCallback() {
            @Override
            public void onSuccess(String message) {
                Snackbar.make(emailField,message, Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent(AuthenticationActivity.this, MainScreenActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(String error) {
                Snackbar.make(emailField,error, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void handleSignUp() {
        String username = usernameField.getText().toString().trim();
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String confirmPassword = confirmPasswordField.getText().toString().trim();

        authManager.signUp( email, password, confirmPassword, new AuthManager.AuthenticationCallback() {
            @Override
            public void onSuccess(String message) {
                Snackbar.make(emailField,message, Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent(AuthenticationActivity.this, MainScreenActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(String error) {
                Snackbar.make(emailField,error, Snackbar.LENGTH_LONG).show();
            }
        });
    }

}
