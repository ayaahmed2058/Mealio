package com.example.mealio.model.db;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.WriteBatch;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BackupMeals {
    private final FirebaseFirestore firestore;
    private final MealDAO myDao;
    private final WeekPlanDAO myWeekDao;
    private final FirebaseAuth auth;
    private final ExecutorService executorService;

    public BackupMeals(MealDAO myDao, WeekPlanDAO myWeekDao) {
        this.firestore = FirebaseFirestore.getInstance();
        this.myDao = myDao;
        this.myWeekDao = myWeekDao;
        this.auth = FirebaseAuth.getInstance();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void backupDataToFireStore() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();

            // Backup FavMeals
            CollectionReference collectionRef = firestore.collection("users")
                    .document(userId)
                    .collection("FavMeals");

            collectionRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    WriteBatch batch = firestore.batch();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        batch.delete(document.getReference());
                    }
                    batch.commit().addOnCompleteListener(deleteTask -> {
                        if (deleteTask.isSuccessful()) {
                            executorService.execute(() -> {
                                try {
                                    List<Meal> items = myDao.getStoredMeal().blockingFirst();
                                    for (Meal item : items) {
                                        collectionRef.document(item.getIdMeal()).set(item)
                                                .addOnSuccessListener(aVoid -> Log.d("FavMealsBackup", "Data backed up successfully - Meal : " + item.getIdMeal()))
                                                .addOnFailureListener(e -> Log.e("FavMealsBackup", "Error backing up data", e));
                                    }
                                } catch (Exception e) {
                                    Log.e("FavMealsBackup", "Error fetching local meals", e);
                                }
                            });
                        } else {
                            Log.e("FavMealsBackup", "Error deleting old data", deleteTask.getException());
                        }
                    });
                } else {
                    Log.e("FavMealsBackup", "Error getting documents for deletion: ", task.getException());
                }
            });

            // Backup WeekPlan
            CollectionReference collectionRefWeek = firestore.collection("users")
                    .document(userId)
                    .collection("WeekPlan");

            collectionRefWeek.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    WriteBatch batch = firestore.batch();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        batch.delete(document.getReference());
                    }
                    batch.commit().addOnCompleteListener(deleteTask -> {
                        if (deleteTask.isSuccessful()) {
                            executorService.execute(() -> {
                                try {
                                    List<WeekPlanner> itemsWeek = myWeekDao.getStoredPlanningMeal().blockingFirst();
                                    for (WeekPlanner item : itemsWeek) {
                                        collectionRefWeek.document(item.getIdMeal()).set(item)
                                                .addOnSuccessListener(aVoid -> Log.d("WeekPlanBackup", "Data backed up successfully - Meal : " + item.getIdMeal()))
                                                .addOnFailureListener(e -> Log.e("WeekPlanBackup", "Error backing up data", e));
                                    }
                                } catch (Exception e) {
                                    Log.e("WeekPlanBackup", "Error fetching local week plans", e);
                                }
                            });
                        } else {
                            Log.e("WeekPlanBackup", "Error deleting old data", deleteTask.getException());
                        }
                    });
                } else {
                    Log.e("WeekPlanBackup", "Error getting documents for deletion: ", task.getException());
                }
            });
        }
    }

    public void restoreDataFromFireStore() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();

            executorService.execute(() -> myDao.clearTable().subscribe());
            executorService.execute(() -> myWeekDao.clearTable().subscribe());

            CollectionReference collectionRef = firestore.collection("users")
                    .document(userId)
                    .collection("FavMeals");

            collectionRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Meal item = document.toObject(Meal.class);
                        executorService.execute(() -> myDao.insertMeal(item)
                                .subscribe(() -> Log.d("FavMealsRestore", "Meal restored: " + item.getIdMeal()),
                                        e -> Log.e("FavMealsRestore", "Error inserting meal", e)));
                    }
                } else {
                    Log.e("FavMealsRestore", "Error getting documents: ", task.getException());
                }
            });

            CollectionReference collectionRefWeek = firestore.collection("users")
                    .document(userId)
                    .collection("WeekPlan");

            collectionRefWeek.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        WeekPlanner item = document.toObject(WeekPlanner.class);
                        executorService.execute(() -> myWeekDao.insertPlanningMeal(item)
                                .subscribe(() -> Log.d("WeekPlanRestore", "Meal restored: " + item.getIdMeal()),
                                        e -> Log.e("WeekPlanRestore", "Error inserting week plan", e)));
                    }
                } else {
                    Log.e("WeekPlanRestore", "Error getting documents: ", task.getException());
                }
            });
        }
    }
}
