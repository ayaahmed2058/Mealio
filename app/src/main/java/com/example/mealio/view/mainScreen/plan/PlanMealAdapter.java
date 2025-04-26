package com.example.mealio.view.mainScreen.plan;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mealio.R;
import com.example.mealio.model.db.WeekPlanner;

import java.util.ArrayList;
import java.util.List;

public class PlanMealAdapter extends RecyclerView.Adapter<PlanMealAdapter.PlanViewHolder> {
    private List<WeekPlanner> weekPlannerMeals = new ArrayList<>();
    private OnDeletePlanClickListener onDeletePlanClickListener;

    public PlanMealAdapter(OnDeletePlanClickListener onDeletePlanClickListener){
        this.onDeletePlanClickListener = onDeletePlanClickListener;
    }

    @NonNull
    @Override
    public PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_plan_item, parent, false);
        return new PlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder holder, int position) {
        WeekPlanner meal = weekPlannerMeals.get(position);

        holder.mealName.setText(meal.getStrMeal());
        holder.mealType.setText(meal.getMealType());
        holder.date.setText(meal.getDate());

        Glide.with(holder.mealImage.getContext())
                .load(meal.getMealThump())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mealImage);


        holder.delete_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeletePlanClickListener.deleteFromPlan(meal);
            }
        });
    }

    @Override
    public int getItemCount() {
        return weekPlannerMeals.size();
    }

    public void updateData(List<WeekPlanner> meals) {
        weekPlannerMeals.clear();
        weekPlannerMeals.addAll(meals);
        notifyDataSetChanged();
    }

    static class PlanViewHolder extends RecyclerView.ViewHolder {
        private ImageView mealImage , delete_img;
        private TextView mealName, date, mealType;

        public PlanViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.meal_name);
            date = itemView.findViewById(R.id.tv_date);
            mealType = itemView.findViewById(R.id.tv_mealType);
            mealImage = itemView.findViewById(R.id.meal_img);
            delete_img = itemView.findViewById(R.id.delete_img);
        }
    }
}
