package com.example.mealio.view.mainScreen.Home.randomMeal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealio.R;
import com.example.mealio.model.pojo.MealSummary;
import com.example.mealio.view.mainScreen.Home.OnMealClickListener;
import com.example.mealio.view.mainScreen.allAreas.AreasAdapter;
import java.util.List;


public class RandomMealAdapter extends RecyclerView.Adapter<RandomMealAdapter.HomeViewHolder> {
    private Context context;
    private List<MealSummary> mealSummaries;
    private OnMealClickListener onMealClickListener;

    public RandomMealAdapter(Context context, List<MealSummary> mealSummaries , OnMealClickListener onMealClickListener) {
        this.context = context;
        this.mealSummaries = mealSummaries;
        this.onMealClickListener = onMealClickListener;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_item, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        MealSummary mealSummary = mealSummaries.get(position);
        holder.mealName.setText(mealSummary.getStrMeal());
        holder.location.setText(mealSummary.getStrArea());
        holder.category.setText(mealSummary.getStrCategory());

        Glide.with(context)
                .load(mealSummary.getStrMealThumb()).apply(new RequestOptions().override(200,200))
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(holder.mealImage);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMealClickListener.OnMealClicked(mealSummary.getIdMeal());
            }
        });


        String countryCode = new AreasAdapter().getCountryCode(mealSummary.getStrArea());

        if (countryCode != null) {
            String flagUrl = "https://flagcdn.com/w320/" + countryCode + ".png";

            Glide.with(context)
                    .load(flagUrl)
                    .placeholder(R.drawable.flag_unknown)
                    .error(R.drawable.error)
                    .into(holder.locationImage);
        } else {
            holder.locationImage.setImageResource(R.drawable.flag_unknown);
        }

    }

    @Override
    public int getItemCount() {
        return mealSummaries.size();
    }

    public void updateData (List<MealSummary> mealSummaries){
        this.mealSummaries = mealSummaries;
        notifyDataSetChanged();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        TextView mealName, location,category;
        ImageView mealImage, locationImage;
        ConstraintLayout constraintLayout;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.meal_name);
            location = itemView.findViewById(R.id.meal_location);
            mealImage = itemView.findViewById(R.id.meal_img);
            category = itemView.findViewById(R.id.meal_category);
            locationImage = itemView.findViewById(R.id.meal_locationImg);
            constraintLayout = itemView.findViewById(R.id.meal_layout);

        }

    }
}
