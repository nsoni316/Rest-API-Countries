package com.dailydose.restapicountries;

import android.app.Activity;
import android.os.Bundle;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CountriesRecyclerAdapter countriesRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.countriesShowRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CountriesViewModel countriesViewModel = ViewModelProviders.of(this).get(CountriesViewModel.class);
        countriesViewModel.getcountries().observe(this, new Observer<List<Model>>() {
            @Override
            public void onChanged(List<Model> models) {
                countriesRecyclerAdapter = new CountriesRecyclerAdapter(MainActivity.this, models, MainActivity.this);
                recyclerView.setAdapter(countriesRecyclerAdapter);
            }
        });
    }
}