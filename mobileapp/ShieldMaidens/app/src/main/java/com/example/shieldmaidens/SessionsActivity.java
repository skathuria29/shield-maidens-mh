package com.example.shieldmaidens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SessionsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    SessionAdapter adapter;
    private RecyclerView activitieslist;
    private ActivitiesAdapter activitieslistAdapter;
    private View loadingScreen;
    private Group group;
    private TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessions);

        recyclerView = findViewById(R.id.recyclerView);
        activitieslist = findViewById(R.id.activitieslist);
        loadingScreen = findViewById(R.id.loadingScreen);
        name = findViewById(R.id.name);
        group = findViewById(R.id.group);

        name.setText(SensumApp.username);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SessionAdapter();
        recyclerView.setAdapter(adapter);
        fetchSessions();


        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        activitieslist.setLayoutManager(layoutManager1);
        activitieslistAdapter = new ActivitiesAdapter();
        activitieslist.setAdapter(activitieslistAdapter);
        fetchActivities();

    }

    private void fetchActivities() {
        ActivitiesApi api = RemoteDataSource.getInstance().createApiService3(ActivitiesApi.class);
        Observable<List<UserActivity>> observable = api.getActivities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


        observable.subscribe(new Consumer<List<UserActivity>>() {
            @Override
            public void accept(List<UserActivity> userActivity) throws Exception {
                loadingScreen.setVisibility(View.GONE);
                group.setVisibility(View.VISIBLE);
                userActivity.add(new UserActivity());
                userActivity.add(new UserActivity());
                userActivity.add(new UserActivity());
                userActivity.add(new UserActivity());
                activitieslistAdapter.setData(userActivity);
            }
        });
    }

    private void fetchSessions() {
        SessionsAPI api = RemoteDataSource.getInstance().createApiService3(SessionsAPI.class);
        Observable<List<Session>> observable = api.getSessions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


        observable.subscribe(new Consumer<List<Session>>() {
            @Override
            public void accept(List<Session> sessions) throws Exception {
                loadingScreen.setVisibility(View.GONE);
                group.setVisibility(View.VISIBLE);
                adapter.setData(sessions);
            }
        });
    }
}
