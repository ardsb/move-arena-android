package com.example.demorestful.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demorestful.R;
import com.example.demorestful.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesActivity extends AppCompatActivity {
    public final String IMAGE_URL_BASE_PATH = "https://image.tmdb.org/t/p/w342/";
    private List<Movie> movies;

    ImageView img;
    TextView movieName,releaseDate,description,rating;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);





        Movie item = (Movie) getIntent().getSerializableExtra("Selected Item");

        img=findViewById(R.id.imgMoviePoster);

        String image_url = IMAGE_URL_BASE_PATH + item.getPosterPath();
        Picasso.get().load(image_url).into(img);

        movieName=findViewById(R.id.txtMovieName);
        movieName.setText(item.getTitle());

        setTitle(item.getTitle());



        releaseDate=findViewById(R.id.txtReleaseDate);
        releaseDate.setText(item.getReleaseDate());

        description=findViewById(R.id.txtDescription);
        description.setText(item.getOverview());

        rating=findViewById(R.id.txtRating);
        rating.setText(item.getVoteAverage().toString());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public boolean onOptionsItemSelected(MenuItem item){
//      Back action
        onBackPressed();
        return true;


    }
}