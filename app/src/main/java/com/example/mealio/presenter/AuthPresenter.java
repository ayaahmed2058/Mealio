package com.example.mealio.presenter;

import com.example.mealio.model.MealRepository;
import com.example.mealio.model.db.Meal;
import com.example.mealio.model.db.WeekPlanner;
import com.example.mealio.view.credentialScreen.AuthView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AuthPresenter {
    private final MealRepository mealRepository;
    private final AuthView view;
    private final FirebaseDatabase firebaseDatabase;
    private final CompositeDisposable disposable = new CompositeDisposable();

    public AuthPresenter(MealRepository mealRepository, AuthView view) {
        this.mealRepository = mealRepository;
        this.view = view;
        this.firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public void loadDataFromFirebase(FirebaseUser user) {
        if (user == null) return;

        DatabaseReference userRef = firebaseDatabase.getReference("users").child(user.getUid());

        userRef.child("meals").get().addOnSuccessListener(dataSnapshot -> {
            List<Meal> meals = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                meals.add(snapshot.getValue(Meal.class));
            }
            disposable.add(mealRepository.insertAllMeal(meals)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> view.onDataRestored(),
                            throwable -> view.showError("Failed to restore meals"))
            );
        });

        userRef.child("plans").get().addOnSuccessListener(dataSnapshot -> {
            List<WeekPlanner> plans = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                plans.add(snapshot.getValue(WeekPlanner.class));
            }
            disposable.add(mealRepository.insertAllPlanningMeal(plans)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> view.onDataRestored(),
                            throwable -> view.showError("Failed to restore plans"))
            );
        });
    }

    public void clearLocalData() {
        disposable.add(
                Completable.mergeArray(
                                mealRepository.clearAllStarMeals(),
                                mealRepository.clearAllPlanningMeals()
                        )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> view.onLocalDataCleared(),
                                throwable -> view.showError("Failed to clear local data")
                        )
        );
    }

    public void signOut() {
        clearLocalData();
        FirebaseAuth.getInstance().signOut();
        view.onUserLoggedOut();
    }


    public void clear() {
        disposable.clear();
    }
}

