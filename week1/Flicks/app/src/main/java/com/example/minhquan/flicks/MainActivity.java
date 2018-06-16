package com.example.minhquan.flicks;

import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    ProgressBar progressBar;

    SwipeRefreshLayout swipeRefreshLayout;

    protected MovieRecyclerAdapter adapter;
    protected List<Movie> movieList;
    protected DTOModelEntitiesDataMapper mapper;
    private int page = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvMovies);
        //progressBar = findViewById(R.id.progressBar);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);

        if (movieList == null) {
            setUpListView();
            callAPI();

        } else {
            //there is already data? screen must be rotating or tab switching
            adapter.setData(movieList);
        }
    }

    private void callAPI() {
//        progressBar.setVisibility(View.VISIBLE);

        APIService.getInstance().getNowPlaying(APILink.API_KEY, page, Locale.getDefault().getLanguage()).enqueue(
                new Callback<MovieListingDTO>() {
                    @Override
                    public void onResponse(Call<MovieListingDTO> call, Response<MovieListingDTO> response) {
//                        progressBar.setVisibility(View.GONE);
                        swipeRefreshLayout.setRefreshing(false);
                        page++;
                        if (response.body() != null) {
                            movieList = mapper.transform(response.body());
                            adapter.setData(movieList);

                        } else
                            Toast.makeText(MainActivity.this, response.message() != null ? response.message() : "Empty", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<MovieListingDTO> call, Throwable t) {
                        //progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }

    private void setUpListView() {
        mapper = new DTOModelEntitiesDataMapper();
        movieList = new ArrayList<>();
        //recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        adapter = new MovieRecyclerAdapter(MainActivity.this);
        adapter.setData(movieList);
        adapter.setListener(new MovieRecyclerAdapter.IClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Toast.makeText(MainActivity.this, movie.getOriginalTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        this.recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callAPI();
            }
        });
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt("listPosition", recyclerView.getScrollState());
//        outState.putParcelableArrayList("listMovie",new ArrayList<Parcelable>(movieList));
//    }
}
