package com.rood.roomretrofitmvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rood.roomretrofitmvvm.R;
import com.rood.roomretrofitmvvm.model.Actor;

import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ActorViewHolder> {

    List<Actor> actorList;
    Context context;

    public ActorAdapter(Context context, List<Actor> actorList){
        this.context = context;
        this.actorList = actorList;
    }

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.single_item_layout, parent, false);
        return new ActorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {
        Actor actor = actorList.get(position);

        holder.idView.setText("ID: " + actor.getId());
        holder.nameView.setText("Name: " +actor.getImage());
        holder.ageView.setText("Age: " +actor.getAge());

        Glide.with(context).load(actor.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return actorList.size();
    }

    // Set List, use in onChange() method of liveData observer
    public void setActorList(List<Actor> actorList){
        this.actorList = actorList;
    }

    // ViewHolder Class
    public static class ActorViewHolder extends RecyclerView.ViewHolder{
        TextView ageView, nameView, idView;
        ImageView imageView;

        public ActorViewHolder(@NonNull View itemView) {
            super(itemView);

            ageView = itemView.findViewById(R.id.age);
            idView = itemView.findViewById(R.id.id);
            nameView = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
