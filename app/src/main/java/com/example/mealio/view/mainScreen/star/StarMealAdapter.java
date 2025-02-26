package com.example.mealio.view.mainScreen.star;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.mealio.R;
import com.example.mealio.model.db.Meal;
import com.example.mealio.view.mainScreen.Home.OnMealClickListener;
import java.util.ArrayList;
import java.util.List;

public class StarMealAdapter extends RecyclerView.Adapter<StarMealAdapter.StarViewHolder> {
    private List<Meal> mealList = new ArrayList<>();
    private OnDeleteClickListener onDeleteClickListener;
    private OnMealClickListener onMealClickListener;

    public StarMealAdapter(OnDeleteClickListener onDeleteClickListener , OnMealClickListener onMealClickListener){
        this.onDeleteClickListener = onDeleteClickListener;
        this.onMealClickListener = onMealClickListener;
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.star_item, parent, false);
        return new StarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder holder, int position) {
        Meal meal = mealList.get(position);

        holder.mealName.setText(meal.getStrMeal());
        holder.mealLocation.setText(meal.getStrArea());
        holder.mealCategory.setText(meal.getStrCategory());

        Glide.with(holder.mealImage.getContext())
                .load(meal.getStrMealThumb())
                .into(holder.mealImage);


        holder.delete_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteClickListener.deleteFromStar(meal);
                holder.delete_img.setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorPrimaryDark));

            }
        });

        holder.mealImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMealClickListener.OnMealClicked(meal.getIdMeal());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public void updateData(List<Meal> meals) {
        mealList.clear();
        mealList.addAll(meals);
        notifyDataSetChanged();
    }

    static class StarViewHolder extends RecyclerView.ViewHolder {
        private ImageView mealImage , delete_img;
        private TextView mealName, mealCategory, mealLocation;

        public StarViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.meal_name);
            mealCategory = itemView.findViewById(R.id.meal_category);
            mealLocation = itemView.findViewById(R.id.meal_location);
            mealImage = itemView.findViewById(R.id.meal_img);
            delete_img = itemView.findViewById(R.id.colse_img);
        }
    }
}
