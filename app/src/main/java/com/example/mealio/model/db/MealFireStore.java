package com.example.mealio.model.db;


import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;


import com.google.firebase.firestore.*;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import java.util.ArrayList;
import java.util.List;

public class MealFireStore {

    private final FirebaseFirestore firestore;

    public MealFireStore() {
        firestore = FirebaseFirestore.getInstance();
    }

    public Completable addFavoritesToFireStore(String userId, List<Meal> favorites) {
        CollectionReference favoritesRef = firestore.collection("users").document(userId).collection("favorites");

        return Completable.create(emitter -> {
            WriteBatch batch = firestore.batch();
            for (Meal favorite : favorites) {
                DocumentReference docRef = favoritesRef.document(favorite.getIdMeal());
                batch.set(docRef, favorite);
            }

            batch.commit()
                    .addOnSuccessListener(aVoid -> emitter.onComplete())
                    .addOnFailureListener(emitter::onError);
        });
    }


    public Single<List<Meal>> getFavoritesFromFireStore(String userId) {
        CollectionReference favoritesRef = firestore.collection("users").document(userId).collection("favorites");

        return Single.create(emitter ->
                favoritesRef.get()
                        .addOnSuccessListener(queryDocumentSnapshots -> {
                            List<Meal> favorites = new ArrayList<>();
                            for (DocumentSnapshot document : queryDocumentSnapshots) {
                                Meal meal = document.toObject(Meal.class);
                                if (meal != null) {
                                    favorites.add(meal);
                                }
                            }
                            emitter.onSuccess(favorites);
                        })
                        .addOnFailureListener(emitter::onError)
        );
    }

    public Completable removeFavoriteFromFireStore(String userId, String mealId) {
        DocumentReference mealRef = firestore.collection("users").document(userId).collection("favorites").document(mealId);

        return Completable.create(emitter ->
                mealRef.delete()
                        .addOnSuccessListener(aVoid -> emitter.onComplete())
                        .addOnFailureListener(emitter::onError)
        );
    }
}

