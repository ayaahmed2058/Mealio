package com.example.mealio.view.mainScreen.Home.allCategories;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.mealio.R;
import com.example.mealio.model.pojo.Category;
import java.util.List;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {
    private final Context context;
    private List<Category> categories;
    private CategoryClickListener categoryClickListener;
    private int selectedPosition = RecyclerView.NO_POSITION;

    public CategoriesAdapter(Context context, List<Category> categories,CategoryClickListener categoryClickListener) {
        this.context = context;
        this.categories = categories;
        this.categoryClickListener = categoryClickListener;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.location.setText(category.getStrCategory());

        Glide.with(holder.categoryImage.getContext())
                .load(category.getStrCategoryThumb())
                .into(holder.categoryImage);

        if (position == selectedPosition) {
            holder.categoryCardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorPrimary));
        } else {
            holder.categoryCardView.setCardBackgroundColor(Color.WHITE);
        }

        holder.categoryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousPosition = selectedPosition;
                selectedPosition = holder.getAdapterPosition();

                notifyItemChanged(previousPosition);
                notifyItemChanged(selectedPosition);

                categoryClickListener.onCategoryClickListener(category.getStrCategory());
            }
        });
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
        CardView categoryCardView;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            location = itemView.findViewById(R.id.meal_location);
            categoryImage = itemView.findViewById(R.id.area_img);
            categoryCardView = itemView.findViewById(R.id.categoryCardView);

        }
    }

}