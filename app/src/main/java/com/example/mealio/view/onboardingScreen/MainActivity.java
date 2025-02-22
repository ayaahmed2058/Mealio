package com.example.mealio.view.onboardingScreen;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mealio.R;
import com.example.mealio.view.credentialScreen.AuthenticationActivity;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicator;
    private MaterialButton buttonOnboardingAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutOnboardingIndicator = findViewById(R.id.layoutOnboardingIndicators);
        buttonOnboardingAction = findViewById(R.id.buttonOnboardingAction);

        setupOnboardingItems();

        ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicators();

        setCurrentOnboardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });

        buttonOnboardingAction.setOnClickListener(v -> {
            if(onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()){
                onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
            }else {
                startActivity(new Intent(getApplicationContext(), AuthenticationActivity.class));
                finish();
            }
        });

    }

    private void setupOnboardingItems (){

        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem projectName = new OnboardingItem();
        projectName.setTitle(getString(R.string.app_name));
        projectName.setDescription(getString(R.string.plan_explore_enjoy_your_favorite_meals));
        projectName.setImage(R.raw.animation_mealio);
        projectName.setLottie(true);

        OnboardingItem mealDetails = new OnboardingItem();
        mealDetails.setTitle(getString(R.string.a_delicious_journey_of_flavors));
        mealDetails.setDescription(getString(R.string.discover_your_meal_s_ingredients_step_by_step_preparation_and_watch_the_video_effortlessly));
        mealDetails.setImage(R.drawable.chef);

        OnboardingItem mealPlanning = new OnboardingItem();
        mealPlanning.setTitle(getString(R.string.your_meals_your_way));
        mealPlanning.setDescription(getString(R.string.organize_your_weekly_schedule_with_a_single_tap_and_stay_prepared));
        mealPlanning.setImage(R.drawable.chef1);

        onboardingItems.add(projectName);
        onboardingItems.add(mealPlanning);
        onboardingItems.add(mealDetails);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);
    }

    private void setupOnboardingIndicators(){
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );

        layoutParams.setMargins(8,0,8,0);

        for(int i=0 ; i<indicators.length ; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicator.addView(indicators[i]);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setCurrentOnboardingIndicator (int index){
        int childCount = layoutOnboardingIndicator.getChildCount();
        for(int i=0 ; i<childCount ; i++){
            ImageView imageView = (ImageView) layoutOnboardingIndicator.getChildAt(i);
            if(i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active)
                );
            }else{
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive)
                );
            }
        }

        if (index == onboardingAdapter.getItemCount() - 1){
            buttonOnboardingAction.setText(R.string.start);
        }else{
            buttonOnboardingAction.setText(R.string.next);
        }
    }

}

