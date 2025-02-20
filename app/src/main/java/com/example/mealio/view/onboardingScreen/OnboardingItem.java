package com.example.mealio.view.onboardingScreen;

public class OnboardingItem {

    private int image;
    private String title;
    private String description;
    private boolean isLottie;

    public boolean isLottie() {
        return isLottie;
    }

    public void setLottie(boolean lottie) {
        isLottie = lottie;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
