package com.dailydose.restapicountries;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountriesRecyclerAdapter extends RecyclerView.Adapter<CountriesRecyclerAdapter.MyCountriesHolder> {
    static String lanuage = "Languages : ";
    static String borders = "Borders : ";
    Context context;
    List<Model> list;
    Activity activity;


    public CountriesRecyclerAdapter(Context context, List<Model> list, Activity activity) {
        this.context = context;
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyCountriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyCountriesHolder(LayoutInflater.from(context).inflate(R.layout.datashowcardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyCountriesHolder holder, int position) {
        Model model = list.get(position);
        holder.name.setText("Name : " + model.getName());
        holder.capital.setText("Capital : " + model.getCapital());
        holder.region.setText("Region : " + model.getRegion());
        holder.subRegion.setText("Sub-Region : " + model.getSubregion());
        Integer population = model.getPopulation();
        holder.population.setText("Population : " + population);

//        Glide.with(activity).load(model.getFlag()).into(holder.flag);
        Utils.fetchSvg(context, model.getFlag(), holder.flag);

        for (int i = 0; i < list.get(position).getLanguages().size(); i++) {
            lanuage = lanuage + " | " + model.getLanguages().get(i).getName();
        }
        for (int i = 0; i < list.get(position).getBorders().size(); i++) {
            borders = borders + " | " + model.getBorders().get(i);
        }

        holder.borders.setText(borders);
        holder.languages.setText(lanuage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyCountriesHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView capital, region, subRegion, population, languages, borders;
        ImageView flag;

        public MyCountriesHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.countryName);
            capital = itemView.findViewById(R.id.countryCapital);
            region = itemView.findViewById(R.id.countryRegion);
            subRegion = itemView.findViewById(R.id.countrySubregion);
            population = itemView.findViewById(R.id.countryPopulation);
            flag = itemView.findViewById(R.id.countryFlag);
            languages = itemView.findViewById(R.id.countryLanguages);
            borders = itemView.findViewById(R.id.countryBorders);
        }
    }
}

