package com.example.mealio.view.mainScreen.Home.allAreas;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealio.R;
import com.example.mealio.model.pojo.AreaListItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AreasAdapter extends RecyclerView.Adapter<AreasAdapter.AreasViewHolder> {
    private Context context;
    private List<AreaListItem> areaListItems;

    public AreasAdapter(Context context, List<AreaListItem> areaListItems) {
        this.context = context;
        this.areaListItems = areaListItems;
    }

    @NonNull
    @Override
    public AreasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.area_item, parent, false);
        return new AreasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreasViewHolder holder, int position) {
        AreaListItem areaListItem = areaListItems.get(position);
        holder.location.setText(areaListItem.getStrArea());

        // تحويل اسم الدولة إلى رمز دولة مناسب
        String countryCode = getCountryCode(areaListItem.getStrArea());

        if (countryCode != null) {
            String flagUrl = "https://flagcdn.com/w320/" + countryCode + ".png";

            Glide.with(context)
                    .load(flagUrl)
                    .placeholder(R.drawable.flag_unknown)
                    .error(R.drawable.error)
                    .into(holder.areaImage);

            Log.d("FlagDebug", "Loading flag for: " + areaListItem.getStrArea() + " (" + countryCode + ")");
        } else {
            Log.e("FlagError", "No country code found for: " + areaListItem.getStrArea());
            holder.areaImage.setImageResource(R.drawable.flag_unknown);
        }
    }


    @Override
    public int getItemCount() {
        return areaListItems.size();
    }

    public void updateData (List<AreaListItem> areaListItems){
        this.areaListItems = areaListItems;
        notifyDataSetChanged();
    }

    public static class AreasViewHolder extends RecyclerView.ViewHolder {
        TextView location;
        ImageView areaImage;

        public AreasViewHolder(@NonNull View itemView) {
            super(itemView);
            location = itemView.findViewById(R.id.meal_location);
            areaImage = itemView.findViewById(R.id.area_img);

        }
    }

    private String getCountryCode(String countryName) {
        Map<String, String> countryCodes = new HashMap<>();
        countryCodes.put("American", "us");
        countryCodes.put("British", "gb");
        countryCodes.put("Canadian", "ca");
        countryCodes.put("Chinese", "cn");
        countryCodes.put("Croatian", "hr");
        countryCodes.put("Dutch", "nl");
        countryCodes.put("Egyptian", "eg");
        countryCodes.put("Filipino", "ph");
        countryCodes.put("French", "fr");
        countryCodes.put("Greek", "gr");
        countryCodes.put("Indian", "in");
        countryCodes.put("Irish", "ie");
        countryCodes.put("Italian", "it");
        countryCodes.put("Jamaican", "jm");
        countryCodes.put("Japanese", "jp");
        countryCodes.put("Kenyan", "ke");
        countryCodes.put("Malaysian", "my");
        countryCodes.put("Mexican", "mx");
        countryCodes.put("Moroccan", "ma");
        countryCodes.put("Norwegian", "no");
        countryCodes.put("Polish", "pl");
        countryCodes.put("Portuguese", "pt");
        countryCodes.put("Russian", "ru");
        countryCodes.put("Spanish", "es");
        countryCodes.put("Thai", "th");
        countryCodes.put("Tunisian", "tn");
        countryCodes.put("Turkish", "tr");
        countryCodes.put("Ukrainian", "ua");
        countryCodes.put("Uruguayan", "uy");
        countryCodes.put("Vietnamese", "vn");

        return countryCodes.getOrDefault(countryName, null);
    }

}