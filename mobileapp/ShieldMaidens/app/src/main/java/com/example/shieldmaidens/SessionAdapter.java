package com.example.shieldmaidens;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Session> sessions = new ArrayList<>();

    public void setData(List<Session> sessions) {
        this.sessions.clear();
        this.sessions.addAll(sessions);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SessionVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SessionVH) holder).bind(sessions.get(position));
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }
}

class SessionVH extends RecyclerView.ViewHolder {

    private final TextView sessionName;
    private final TextView doctor;
    private final TextView tvCount;

    public SessionVH(@NonNull View itemView) {
        super(itemView);
        sessionName = itemView.findViewById(R.id.sessionName);
        doctor = itemView.findViewById(R.id.doctor);
        tvCount = itemView.findViewById(R.id.peoplecount);
    }

    public void bind(Session session) {
        sessionName.setText(session.getName());
        doctor.setText(session.getProfessional());
       // tvCount.setText(session.getCount());
    }

}
