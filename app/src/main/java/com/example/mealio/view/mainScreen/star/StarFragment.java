package com.example.mealio.view.mainScreen.star;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealio.R;
import com.example.mealio.model.MealRepository;
import com.example.mealio.model.Network.MealRemoteDataSourceImp;
import com.example.mealio.model.db.Meal;
import com.example.mealio.model.db.MealLocalDataSourceImp;
import com.example.mealio.presenter.StarPresenter;
import com.example.mealio.view.credentialScreen.AuthenticationActivity;
import com.example.mealio.view.mainScreen.Home.HomeFragmentDirections;
import com.example.mealio.view.mainScreen.Home.OnMealClickListener;
import com.example.mealio.view.mainScreen.Utils;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;

public class StarFragment extends Fragment implements OnDeleteClickListener, StarMealView, OnMealClickListener {

    private RecyclerView starMealsRecyclerView;
    private StarMealAdapter starMealAdapter;
    private StarPresenter starPresenter;

    public StarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_star, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        starMealsRecyclerView  = view.findViewById(R.id.recycler_starMeals);
        starMealAdapter = new StarMealAdapter(this,this);
        starPresenter = new StarPresenter(this, MealRepository.getInstance(MealLocalDataSourceImp.getInstance(getContext()),
                MealRemoteDataSourceImp.getInstance()));

       LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        starMealsRecyclerView.setLayoutManager(linearLayoutManager);
        starMealsRecyclerView.setAdapter(starMealAdapter);

        starPresenter.getStoredMeal();

    }

    @Override
    public void deleteFromStar(Meal meal) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Confirm Deletion")
                .setMessage("Are you sure you want to delete this meal?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    starPresenter.deleteFromFav(meal);
                    starMealAdapter.notifyDataSetChanged();
                    Snackbar.make(starMealsRecyclerView, "Meal was deleted successfully", Snackbar.LENGTH_LONG).show();
                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }


    @Override
    public void setMeals(List<Meal> meals) {
        starMealAdapter.updateData(meals);
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        Snackbar.make(starMealsRecyclerView,errorMessage, Snackbar.LENGTH_LONG).show();;
    }

    @Override
    public void OnMealClicked(String id) {

        if (Utils.isNetworkAvailable(requireContext())) {
            StarFragmentDirections.ActionStarFragmentToMealDetailsFragment action =
                    StarFragmentDirections.actionStarFragmentToMealDetailsFragment(id,"StarFragment","MealDetailsFragment");

            Navigation.findNavController(starMealsRecyclerView).navigate(action);
        } else {
            StarFragmentDirections.ActionStarFragmentToStarMealDetailsFragment action =
                    StarFragmentDirections.actionStarFragmentToStarMealDetailsFragment(id);

            Navigation.findNavController(starMealsRecyclerView).navigate(action);
        }

    }


}