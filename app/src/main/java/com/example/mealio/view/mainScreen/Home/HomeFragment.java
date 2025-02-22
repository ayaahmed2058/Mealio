package com.example.mealio.view.mainScreen.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealio.R;
import com.example.mealio.model.MealRepository;
import com.example.mealio.model.Network.MealRemoteDataSourceImp;
import com.example.mealio.model.pojo.AreaListItem;
import com.example.mealio.model.pojo.MealSummary;
import com.example.mealio.presenter.RandomMealPresenter;
import com.example.mealio.view.mainScreen.AllMealView;
import com.example.mealio.view.mainScreen.Home.allAreas.AreaView;
import com.example.mealio.view.mainScreen.Home.allAreas.AreasAdapter;
import com.example.mealio.view.mainScreen.Home.randomMeal.RandomMealAdapter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements AllMealView, AreaView {

    private RandomMealAdapter randomMealAdapter;
    private AreasAdapter areasAdapter;
    private static final String TAG = "RandomMeal";
    private RandomMealPresenter randomMealPresenter;
    private RecyclerView randomRecyclerView, areasRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    public HomeFragment() {


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        randomRecyclerView = view.findViewById(R.id.recycler_randomMeal);
        areasRecyclerView = view.findViewById(R.id.recycler_Areas);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        randomMealAdapter = new RandomMealAdapter(getActivity(), new ArrayList<>() );
        areasAdapter = new AreasAdapter(getActivity(), new ArrayList<>() );

        randomMealPresenter  = new RandomMealPresenter(this , this,
                MealRepository.getInstance(MealRemoteDataSourceImp.getInstance()));

        randomRecyclerView.setLayoutManager(linearLayoutManager);
        randomRecyclerView.setAdapter(randomMealAdapter);

        areasRecyclerView.setAdapter(areasAdapter);

        randomMealPresenter.getRandomMeal();
        randomMealPresenter.getAllAreas();
    }

    @Override
    public void setMeals(List<MealSummary> mealSummaries) {
        randomMealAdapter.updateData(mealSummaries);
        randomMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void setAreas(List<AreaListItem> areas) {
        areasAdapter.updateData(areas);
        areasAdapter.notifyDataSetChanged();
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        Toast.makeText(getActivity() , errorMessage , Toast.LENGTH_LONG).show();

    }
}