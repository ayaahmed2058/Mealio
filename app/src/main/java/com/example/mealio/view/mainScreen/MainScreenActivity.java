package com.example.mealio.view.mainScreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.example.mealio.R;
import com.example.mealio.view.credentialScreen.AuthenticationActivity;
import com.example.mealio.view.mainScreen.star.StarFragment;
import com.example.mealio.view.mealDetails.MealDetailsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainScreenActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FirebaseAuth firebaseAuth;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        builder = new AlertDialog.Builder(this);

        firebaseAuth = FirebaseAuth.getInstance();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        restrictGuestAccess();
    }

    private void restrictGuestAccess() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (firebaseAuth.getCurrentUser() != null && firebaseAuth.getCurrentUser().isAnonymous()) {
                if (itemId == R.id.starFragment || itemId == R.id.planFragment) {
                    signupToGetMoreFeature();
                    Toast.makeText(this, "Please sign in to access favorites", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            return NavigationUI.onNavDestinationSelected(item,
                    Navigation.findNavController(this, R.id.nav_host_fragment));
        });
    }

    public void signupToGetMoreFeature() {

        builder.setTitle("Sign up for more features!")
                .setMessage("Save your favorite dishes \n and plan your meals")
                .setCancelable(true)
                .setPositiveButton("Sign up", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainScreenActivity.this, AuthenticationActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}