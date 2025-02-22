package com.example.mealio.view.mainScreen.Home.allCategoriesView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealio.R;
import com.example.mealio.model.pojo.Category;
import java.util.List;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {
    private final Context context;
    private List<Category> categories;

    public CategoriesAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.area_item, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.location.setText(category.getStrCategory());

        Glide.with(holder.categoryImage.getContext())
                .load(category.getStrCategoryThumb())
                .into(holder.categoryImage);
    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData (List<Category> categories){
        this.categories = categories;
        notifyDataSetChanged();
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder {
        TextView location;
        ImageView categoryImage;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            location = itemView.findViewById(R.id.meal_location);
            categoryImage = itemView.findViewById(R.id.area_img);

        }
    }

}