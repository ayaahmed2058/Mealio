package com.example.mealio.view.mealDetails;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.mealio.R;
import com.example.mealio.model.pojo.Meal;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import java.util.ArrayList;
import java.util.List;

public class MealDetailsAdapter extends RecyclerView.Adapter<MealDetailsAdapter.MealDetailsViewHolder> {
    private List<Meal> mealList = new ArrayList<>();

    @NonNull
    @Override
    public MealDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_details, parent, false);
        return new MealDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealDetailsViewHolder holder, int position) {
        Meal meal = mealList.get(position);

        holder.mealName.setText(meal.getStrMeal());
        holder.mealLocation.setText(meal.getStrArea());
        holder.mealCategory.setText(meal.getStrCategory());
        holder.mealInstructions.setText(meal.getStrInstructions());

        Glide.with(holder.mealImage.getContext())
                .load(meal.getStrMealThumb())
                .into(holder.mealImage);

        holder.ingredientMealDetailsAdapter.updateData(meal.getIngredientList());

        if (!meal.getStrYoutube().isEmpty() && meal.getStrYoutube() != null) {
            String[] split = meal.getStrYoutube().split("=");
            holder.youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = split[1];
                    youTubePlayer.cueVideo(videoId, 0);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public void updateData(List<Meal> newMeals) {
        mealList.clear();
        mealList.addAll(newMeals);
        notifyDataSetChanged();
    }

    static class MealDetailsViewHolder extends RecyclerView.ViewHolder {
        private ImageView mealImage;
        private TextView mealName, mealCategory, mealLocation, mealInstructions;
        private YouTubePlayerView youTubePlayerView;
        private RecyclerView ingredientRecyclerView;
        private IngredientMealDetailsAdapter ingredientMealDetailsAdapter;

        public MealDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.meal_name);
            mealCategory = itemView.findViewById(R.id.meal_category);
            mealLocation = itemView.findViewById(R.id.meal_location);
            mealInstructions = itemView.findViewById(R.id.meal_steps);
            mealImage = itemView.findViewById(R.id.meal_img);
            youTubePlayerView = itemView.findViewById(R.id.IngredientVideo);
            ingredientRecyclerView = itemView.findViewById(R.id.recycler_meal_ingredient);

            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), RecyclerView.HORIZONTAL, false);
            ingredientRecyclerView.setLayoutManager(layoutManager);
            ingredientMealDetailsAdapter = new IngredientMealDetailsAdapter();
            ingredientRecyclerView.setAdapter(ingredientMealDetailsAdapter);
        }
    }
}
