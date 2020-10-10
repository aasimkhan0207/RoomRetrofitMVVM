package com.rood.roomretrofitmvvm.network;

import com.rood.roomretrofitmvvm.model.Actor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("data.php")
    Call<List<Actor>> getAllData();
}
