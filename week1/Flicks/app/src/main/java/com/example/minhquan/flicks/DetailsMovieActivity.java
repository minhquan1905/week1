package com.example.minhquan.flicks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailsMovieActivity extends AppCompatActivity {
    TextView textView_Title, textView_ReleaseDay, textView_overview;
    RatingBar ratingBar;
    ImageView imageViewDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_movie_layout);

        imageViewDetails = findViewById(R.id.imgDetails);
        textView_Title = findViewById(R.id.tvTitle);
        textView_overview = findViewById(R.id.tvOverview);
        textView_ReleaseDay = findViewById(R.id.tvReleaseDate);
        ratingBar = findViewById(R.id.ratingBar);

//        Glide.with(this).load(movie.getPoster())
//                .into(holder.poster);

        Bundle bundle = getIntent().getExtras();
        textView_Title.setText(bundle.getString("Title"));
        textView_ReleaseDay.setText(bundle.getString("ReleaseDay"));
        ratingBar.setRating((float)bundle.getDouble("Rating"));
        textView_overview.setText(bundle.getString("Overview"));
    }
}
