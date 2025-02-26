package com.example.mealio.view.mainScreen.search;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.mealio.R;
import com.example.mealio.model.MealRepository;
import com.example.mealio.model.Network.MealRemoteDataSourceImp;
import com.example.mealio.model.db.MealLocalDataSourceImp;
import com.example.mealio.model.pojo.MealSummary;
import com.example.mealio.presenter.SearchPresenter;
import com.example.mealio.view.mainScreen.Home.HomeFragmentDirections;
import com.example.mealio.view.mainScreen.Home.OnMealClickListener;
import java.util.List;
import java.util.concurrent.TimeUnit;
import androidx.appcompat.widget.SearchView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class SearchFragment extends Fragment implements SearchViewIn, OnMealClickListener {

    private SearchView searchView;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MealAdapter adapter;
    private SearchPresenter presenter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView = view.findViewById(R.id.search_input);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MealAdapter(this);
        recyclerView.setAdapter(adapter);

        presenter = new SearchPresenter(this, MealRepository.getInstance(MealLocalDataSourceImp.getInstance(getContext()),
                MealRemoteDataSourceImp.getInstance()));

        Observable<String> searchObservable = Observable.create(emitter -> {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    emitter.onNext(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    emitter.onNext(newText);
                    return true;
                }
            });
        });

        searchObservable
                .debounce(500, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query -> presenter.searchMeal(query));
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMeals(List<MealSummary> meals) {
        adapter.setMeals(meals);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnMealClicked(String id) {
        SearchFragmentDirections.ActionSearchFragmentToMealDetailsFragment action =
                SearchFragmentDirections.actionSearchFragmentToMealDetailsFragment(id,"SearchFragment", "DetailsFragment");
        Navigation.findNavController(searchView).navigate(action);
    }
}
