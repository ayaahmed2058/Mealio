plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.mealio"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mealio"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //dependency for onboarding screen
    implementation ("androidx.viewpager2:viewpager2:1.0.0")
    implementation ("com.google.android.material:material:1.9.0")

    //lottie animation
    implementation("com.airbnb.android:lottie:5.2.0")

    //Navigation component
    implementation ("androidx.navigation:navigation-fragment:2.5.3")
    implementation ("androidx.navigation:navigation-ui:2.5.3")

    //firebase
    implementation(platform("com.google.firebase:firebase-bom:33.9.0"))
    implementation("com.google.firebase:firebase-auth")




}