package com.example.mealio.view.mealDetails;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mealio.R;
import com.example.mealio.model.MealRepository;
import com.example.mealio.model.Network.MealRemoteDataSourceImp;
import com.example.mealio.model.pojo.Meal;
import com.example.mealio.presenter.MealDetailsPresenter;
import java.util.List;


public class MealDetailsFragment extends Fragment implements MealDetailsView {
    private RecyclerView mealDetailsRecyclerView;
    private MealDetailsAdapter mealDetailsAdapter;
    private MealDetailsPresenter mealDetailsPresenter;

    public MealDetailsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String mealID = MealDetailsFragmentArgs.fromBundle(getArguments()).getMealID();

        mealDetailsRecyclerView = view.findViewById(R.id.meal_details);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mealDetailsRecyclerView.setLayoutManager(layoutManager);

        mealDetailsAdapter = new MealDetailsAdapter();
        mealDetailsRecyclerView.setAdapter(mealDetailsAdapter);

        mealDetailsPresenter = new MealDetailsPresenter(this, MealRepository.getInstance(MealRemoteDataSourceImp.getInstance()));
        mealDetailsPresenter.getMealDetails(mealID);
    }

    @Override
    public void setMealDetails(List<Meal> meals) {
        for (Meal meal : meals) {
            meal.extractIngredients();
        }
        mealDetailsAdapter.updateData(meals);
        mealDetailsAdapter.notifyDataSetChanged();
    }

    @Override
    public void setErrorMessage(String errorMessage) {

    }
}
