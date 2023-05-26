package com.example.perpusdesa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perpusdesa.R;
import com.example.perpusdesa.adapter.HomeListAdapter;
import com.example.perpusdesa.model.PepusModel;
import com.example.perpusdesa.viewmodel.PerpusListViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements HomeListAdapter.ItemClickListener {

    private List<PepusModel> pepusModelList;
    private HomeListAdapter adapter;
    private PerpusListViewModel viewModel;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.home) {
                    return true;
                } else if (item.getItemId() == R.id.bookmark) {
                    startActivity(new Intent(getApplicationContext(), BookmarkActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (item.getItemId() == R.id.profile) {
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter =  new HomeListAdapter(this, pepusModelList, this);
        recyclerView.setAdapter(adapter);


        viewModel = ViewModelProviders.of(this).get(PerpusListViewModel.class);
        viewModel.getPerpusListObserver().observe(this, new Observer<List<PepusModel>>() {
            @Override
            public void onChanged(List<PepusModel> pepusModels) {
                if(pepusModels != null) {
                    pepusModelList = pepusModels;
                    adapter.setPerpusList(pepusModels);
                }
            }
        });
        viewModel.makeApiCall();
    }

    @Override
    public void onPerpusClick(PepusModel book) {
        Toast.makeText(this, "Clicked Book Name is : " +book.getId(), Toast.LENGTH_SHORT).show();
    }
}