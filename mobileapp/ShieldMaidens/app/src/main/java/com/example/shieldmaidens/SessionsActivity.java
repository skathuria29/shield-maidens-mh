package com.example.shieldmaidens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SessionsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    SessionAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessions);

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SessionAdapter();
        recyclerView.setAdapter(adapter);
        fetchSessions();

    }

    private void fetchSessions() {
        SessionsAPI api = RemoteDataSource.getInstance().createApiService3(SessionsAPI.class);
        Observable<List<Session>> observable = api.getSessions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


        observable.subscribe(new Consumer<List<Session>>() {
            @Override
            public void accept(List<Session> sessions) throws Exception {
                adapter.setData(sessions);
            }
        });
    }
}
