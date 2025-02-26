package com.example.mealio.view.mainScreen.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.mealio.R;
import com.example.mealio.model.pojo.MealSummary;
import com.example.mealio.view.mainScreen.Home.OnMealClickListener;

import java.util.ArrayList;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {
    private List<MealSummary> meals = new ArrayList<>();
    private OnMealClickListener onMealClickListener;

    public MealAdapter(OnMealClickListener onMealClickListener){
        this.onMealClickListener = onMealClickListener;
    }

    public void setMeals(List<MealSummary> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    @Override
    public MealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_item, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MealViewHolder holder, int position) {
        MealSummary meal = meals.get(position);
        holder.textViewName.setText(meal.getStrMeal());
        Glide.with(holder.imageView.getContext()).load(meal.getStrMealThumb()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMealClickListener.OnMealClicked(meal.getIdMeal());
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class MealViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        ImageView imageView;

        MealViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.meal_name);
            imageView = itemView.findViewById(R.id.meal_img);
        }
    }
}

