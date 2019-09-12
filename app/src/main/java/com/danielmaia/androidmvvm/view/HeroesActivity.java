package com.danielmaia.androidmvvm.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.danielmaia.androidmvvm.R;
import com.danielmaia.androidmvvm.adapter.HeroesAdapter;
import com.danielmaia.androidmvvm.model.Hero;
import com.danielmaia.androidmvvm.viewModel.HeroesViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeroesActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    HeroesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fillList();
    }

    private void fillList(){
        HeroesViewModel model = ViewModelProviders.of(this).get(HeroesViewModel.class);

        model.getHeroes().observe(this, new Observer<List<Hero>>() {
            @Override
            public void onChanged(@Nullable List<Hero> heroList) {
                adapter = new HeroesAdapter(HeroesActivity.this, heroList);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}
