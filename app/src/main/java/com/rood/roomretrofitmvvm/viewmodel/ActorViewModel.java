package com.rood.roomretrofitmvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rood.roomretrofitmvvm.model.Actor;
import com.rood.roomretrofitmvvm.repository.ActorRepository;

import java.util.List;

public class ActorViewModel extends AndroidViewModel {

    ActorRepository repository;
    LiveData<List<Actor>> getAllActors;

    // constructor
    public ActorViewModel(@NonNull Application application) {
        super(application);

        repository = new ActorRepository(application);
        getAllActors = repository.getGetAllActors();
    }

    // Insert method
    public void insert(List<Actor> list){
        repository.insert(list);
    }

    // GetAll (LiveData)
    public LiveData<List<Actor>> getGetAll(){
        return getAllActors;
    }
}
