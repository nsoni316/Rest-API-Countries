package com.dailydose.restapicountries;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesViewModel extends ViewModel {

    private MutableLiveData<List<Model>> countriesList;

    public LiveData<List<Model>> getcountries() {
        //if the list is null
        if (countriesList == null) {
            countriesList = new MutableLiveData<>();

            //we will load it asynchronously from server in this method
            loadHeroes();
        }
        return countriesList;
    }


    private void loadHeroes() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Model>> call = api.getcountries();
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                countriesList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {

            }
        });
    }
}
