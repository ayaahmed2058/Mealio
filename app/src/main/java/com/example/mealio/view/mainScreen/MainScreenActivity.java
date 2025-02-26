package com.example.mealio.view.mainScreen;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.example.mealio.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainScreenActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }

}