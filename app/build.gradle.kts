plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
    id ("androidx.navigation.safeargs")
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
    implementation(libs.firebase.firestore)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //dependency for onboarding screen
    implementation ("androidx.viewpager2:viewpager2:1.0.0")
    implementation("com.google.android.material:material:1.11.0")



    //lottie animation
    implementation("com.airbnb.android:lottie:5.2.0")

    //Navigation component
    implementation ("androidx.navigation:navigation-fragment:2.5.3")
    implementation ("androidx.navigation:navigation-ui:2.5.3")

    //firebase
    implementation(platform("com.google.firebase:firebase-bom:33.9.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation ("com.google.android.gms:play-services-auth:20.5.0")
    implementation ("com.google.firebase:firebase-storage:20.3.0")



    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

    //youtube
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.1.0")
    

    //Room
    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")

    //Rx android
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation ("io.reactivex.rxjava3:rxjava:3.1.6")

    //Rx Room
    implementation ("androidx.room:room-rxjava3:2.6.1")
    //Rx RetroFit
    implementation ("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0")

    implementation ("com.google.code.gson:gson:2.8.9")


        implementation ("com.google.firebase:firebase-database:20.2.2")
        implementation ("com.google.firebase:firebase-auth:22.1.1")


    

}