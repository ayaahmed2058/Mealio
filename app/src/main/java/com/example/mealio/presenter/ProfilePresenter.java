package com.example.mealio.presenter;

import com.example.mealio.model.MealRepository;
import com.example.mealio.view.mainScreen.profile.ProfileView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseUser;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfilePresenter {
    private final MealRepository mealRepository;
    private final ProfileView view;
    private final FirebaseDatabase firebaseDatabase;
    private final FirebaseAuth firebaseAuth;
    private final CompositeDisposable disposable = new CompositeDisposable();

    public ProfilePresenter(MealRepository mealRepository , ProfileView view) {
        this.mealRepository = mealRepository;
        this.view = view;
        this.firebaseDatabase = FirebaseDatabase.getInstance();
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public void saveDataToFirebase() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user == null) return;

        DatabaseReference userRef = firebaseDatabase.getReference("users").child(user.getUid());

        disposable.add(mealRepository.getStoredMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> userRef.child("meals").setValue(meals),
                        throwable -> view.showError("Failed to save meals"))
        );

        disposable.add(mealRepository.getStoredPlanningMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(plans -> userRef.child("plans").setValue(plans),
                        throwable -> view.showError("Failed to save plans"))
        );
    }

    public void logout() {
        saveDataToFirebase();
        firebaseAuth.signOut();
        view.onLogoutSuccess();
    }

    public void clear() {
        disposable.clear();
    }
}

