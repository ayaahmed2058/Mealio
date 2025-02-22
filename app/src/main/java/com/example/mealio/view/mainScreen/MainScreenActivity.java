package com.example.mealio.view.mainScreen;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mealio.R;
import com.example.mealio.view.mainScreen.Home.HomeFragment;
import com.example.mealio.view.mainScreen.plan.PlanFragment;
import com.example.mealio.view.mainScreen.search.SearchFragment;
import com.example.mealio.view.mainScreen.star.StarFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainScreenActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.nav_home){

                    replaceFragment(new HomeFragment());
                }
                else if ((item.getItemId() == R.id.nav_search)){

                    replaceFragment(new SearchFragment());

                }else if ((item.getItemId() == R.id.nav_star)){

                    replaceFragment(new StarFragment());

                }else if(item.getItemId() == R.id.nav_week){

                    replaceFragment(new PlanFragment());

                }

                return true;
            }
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
        fragmentTransaction.commit();
    }
}