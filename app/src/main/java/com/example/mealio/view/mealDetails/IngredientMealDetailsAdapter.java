package com.example.mealio.view.mealDetails;


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
import java.util.ArrayList;
import java.util.List;

public class IngredientMealDetailsAdapter extends RecyclerView.Adapter<IngredientMealDetailsAdapter.IngredientMealDetailsViewHolder> {
    private List<IngredientListItem> ingredientListItems = new ArrayList<>();

    public IngredientMealDetailsAdapter() {}

    @NonNull
    @Override
    public IngredientMealDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent, false);
        return new IngredientMealDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientMealDetailsViewHolder holder, int position) {
        IngredientListItem ingredient = ingredientListItems.get(position);
        holder.ingredientName.setText(ingredient.getStrIngredient());
        holder.ingredientMeasure.setText(ingredient.getStrDescription());

        String ingredientImageUrl = "https://www.themealdb.com/images/ingredients/" + ingredient.getStrIngredient() + ".png";
        Glide.with(holder.ingredientImage.getContext())
                .load(ingredientImageUrl)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(holder.ingredientImage);
    }

    @Override
    public int getItemCount() {
        return ingredientListItems.size();
    }

    public void updateData(List<IngredientListItem> ingredientListItems) {
        this.ingredientListItems = ingredientListItems;
        notifyDataSetChanged();
    }

    static class IngredientMealDetailsViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientName, ingredientMeasure;
        ImageView ingredientImage;

        public IngredientMealDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.tv_ingredientName);
            ingredientMeasure = itemView.findViewById(R.id.tv_ingredientMeasure);
            ingredientImage = itemView.findViewById(R.id.meal_img);
        }
    }
}
