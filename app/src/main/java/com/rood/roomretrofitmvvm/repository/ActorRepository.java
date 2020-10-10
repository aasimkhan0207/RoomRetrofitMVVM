package com.rood.roomretrofitmvvm.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.rood.roomretrofitmvvm.dao.ActorDao;
import com.rood.roomretrofitmvvm.database.ActorDatabase;
import com.rood.roomretrofitmvvm.model.Actor;

import java.util.List;

public class ActorRepository {

    private ActorDatabase database;
    private LiveData<List<Actor>> getAllActors;

    public ActorRepository(Application application){

        database = ActorDatabase.getInstance(application);
        getAllActors = database.actorDao().getAll();

    }

    public void insert(List<Actor> actorList){
        new InsertAsyncTask(database).execute(actorList);
    }

    public LiveData<List<Actor>> getGetAllActors(){
        return getAllActors;
    }

    static class InsertAsyncTask extends AsyncTask<List<Actor>, Void, Void>{

        private ActorDao actorDao;
        InsertAsyncTask(ActorDatabase database){
            actorDao = database.actorDao();
        }

        @Override
        protected Void doInBackground(List<Actor>... lists) {
            actorDao.insert(lists[0]);
            return null;
        }
    }

}
