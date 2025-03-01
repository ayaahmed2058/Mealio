package com.example.mealio.view.credentialScreen;

public interface AuthView {
    void onDataRestored();
    void showError(String message);
    void onLocalDataCleared();
    void onUserLoggedOut();

}

