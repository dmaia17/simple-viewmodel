package com.danielmaia.androidmvvm.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.danielmaia.androidmvvm.model.Hero;
import com.danielmaia.androidmvvm.service.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeroesViewModel extends ViewModel {

    private MutableLiveData<List<Hero>> heroList;

    //Método para buscar os dados
    public LiveData<List<Hero>> getHeroes() {
        if (heroList == null) {
            heroList = new MutableLiveData<List<Hero>>();
            loadHeroes(); //Ler os dados do server
        }
        return heroList;
    }

    //Usando retrofit, busca o json da url This
    private void loadHeroes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Hero>> call = api.getHeroes();

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {

                //Seta os dados recebidos do server na lista
                heroList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {

            }
        });
    }
}
