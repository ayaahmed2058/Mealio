package com.example.mealio.view.mainScreen.allIngredient;

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
import com.example.mealio.model.pojo.IngredientListItem;

import java.util.List;


public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {
    private final Context context;
    private List<IngredientListItem> ingredientListItems;

    public IngredientsAdapter(Context context, List<IngredientListItem> ingredientListItems) {
        this.context = context;
        this.ingredientListItems = ingredientListItems;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        IngredientListItem ingredientListItem = ingredientListItems.get(position);
        holder.ingredientName.setText(ingredientListItem.getStrIngredient());

        String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredientListItem.getStrIngredient() + "-Small.png";
        Glide.with(context).load(imageUrl).into(holder.ingredientImage);
    }


    @Override
    public int getItemCount() {
        return ingredientListItems.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData (List<IngredientListItem> ingredientListItems){
        this.ingredientListItems = ingredientListItems;
        notifyDataSetChanged();
    }

    public static class IngredientsViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientName;
        ImageView ingredientImage;

        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.meal_location);
            ingredientImage = itemView.findViewById(R.id.area_img);

        }
    }

}