package com.example.mealio.view.mainScreen.Home.getMealByCategory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealio.R;
import com.example.mealio.model.pojo.MealSummary;
import com.example.mealio.view.mainScreen.Home.OnMealClickListener;
import com.example.mealio.view.mainScreen.allAreas.AreasAdapter;

import java.util.List;


public class GetMealByCategoryAdapter extends RecyclerView.Adapter<GetMealByCategoryAdapter.MealByCategoryViewHolder> {
    private Context context;
    private List<MealSummary> mealSummaries;
    private OnMealClickListener onMealClickListener;

    public GetMealByCategoryAdapter(Context context, List<MealSummary> mealSummaries , OnMealClickListener onMealClickListener) {
        this.context = context;
        this.mealSummaries = mealSummaries;
        this.onMealClickListener = onMealClickListener;
    }

    @NonNull
    @Override
    public MealByCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.get_meal_by_category, parent, false);
        return new MealByCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealByCategoryViewHolder holder, int position) {
        MealSummary mealSummary = mealSummaries.get(position);
        holder.mealName.setText(mealSummary.getStrMeal());

        Glide.with(context)
                .load(mealSummary.getStrMealThumb()).apply(new RequestOptions().override(200,200))
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(holder.mealImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMealClickListener.OnMealClicked(mealSummary.getIdMeal());
            }
        });


    }

    @Override
    public int getItemCount() {
        return mealSummaries.size();
    }

    public void updateData (List<MealSummary> mealSummaries){
        this.mealSummaries = mealSummaries;
        notifyDataSetChanged();
    }

    public static class MealByCategoryViewHolder extends RecyclerView.ViewHolder {
        TextView mealName;
        ImageView mealImage;
        CardView cardView;

        public MealByCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.meal_name);
            mealImage = itemView.findViewById(R.id.meal_img);
            cardView = itemView.findViewById(R.id.meal_card);

        }

    }
}
