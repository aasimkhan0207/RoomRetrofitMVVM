package com.rood.roomretrofitmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.rood.roomretrofitmvvm.adapter.ActorAdapter;
import com.rood.roomretrofitmvvm.model.Actor;
import com.rood.roomretrofitmvvm.network.Api;
import com.rood.roomretrofitmvvm.viewmodel.ActorViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActorViewModel viewModel;

    private RecyclerView recyclerView;
    private ActorAdapter actorAdapter;

    private List<Actor> actorList;

    public static final String BASE_URL = "http://www.codingwithjks.tech/data.php/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        actorList = new ArrayList<>();
        actorAdapter = new ActorAdapter(this, actorList);
        recyclerView.setAdapter(actorAdapter);

        viewModel = new ViewModelProvider(this).get(ActorViewModel.class);
        viewModel.getGetAll().observe(this, new Observer<List<Actor>>() {
            @Override
            public void onChanged(List<Actor> list) {

                actorAdapter.setActorList(list);
                actorAdapter.notifyDataSetChanged();
            }
        });

        // Fetch data, Retrofit
        networkRequest();
    }

    private void networkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Actor>> call = api.getAllData();

        call.enqueue(new Callback<List<Actor>>() {
            @Override
            public void onResponse(Call<List<Actor>> call, Response<List<Actor>> response) {
                if (response.isSuccessful())
                    viewModel.insert(response.body());
            }

            @Override
            public void onFailure(Call<List<Actor>> call, Throwable t) {
                Log.e("ERROR ", t.getMessage());
                Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}