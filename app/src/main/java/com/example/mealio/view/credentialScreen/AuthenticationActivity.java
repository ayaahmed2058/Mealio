package com.example.mealio.view.credentialScreen;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.Fade;
import androidx.transition.Scene;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import com.example.mealio.R;

public class AuthenticationActivity extends AppCompatActivity {

    private Scene loginScene;
    private Scene signUpScene;
    private boolean isSignUp = false;
    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

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
        if (isSignUp) {
            TransitionManager.go(loginScene, fadeTransition);
        } else {
            TransitionManager.go(signUpScene, fadeTransition);
        }
        isSignUp = !isSignUp;
        setupSceneSwitch();
    }
}