package com.example.shieldmaidens;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ActivitiesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<UserActivity> sessions = new ArrayList<>();

    public void setData(List<UserActivity> sessions) {
        this.sessions.clear();
        this.sessions.addAll(sessions);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ActivitiesVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_activity_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ActivitiesVH) holder).bind(sessions.get(position));
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }
}

class ActivitiesVH extends RecyclerView.ViewHolder {

    public ActivitiesVH(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(UserActivity session) {
        // tvCount.setText(session.getCount());
    }

}
