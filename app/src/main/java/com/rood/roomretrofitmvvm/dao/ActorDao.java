package com.rood.roomretrofitmvvm.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rood.roomretrofitmvvm.model.Actor;

import java.util.List;

@Dao
public interface ActorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Actor> list);

    @Query("SELECT * FROM actor")
    LiveData<List<Actor>> getAll();

    @Query("DELETE FROM actor")
    void deleteAll();
}
