package com.example.demorestful.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.demorestful.R;
import com.example.demorestful.adapter.MoviesAdapter;
import com.example.demorestful.model.Movie;
import com.example.demorestful.model.MovieResults;
import com.example.demorestful.model.MoviesResponse;
import com.example.demorestful.network.ApiClient;
import com.example.demorestful.network.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final static String API_KEY = "6c4b5b3faa7b3ebf1704acd6c592d29a";
    String TAG = MainActivity.class.getSimpleName();
    LinearLayout linearLayout;


    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setTitle("Movie Arena");

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(MainActivity.this,InfoActivity.class);
                startActivity(main);




            }
        });

//      Calling the movie service
        getTopRatedMovie();
        getUpcomingMovies();
        getNowPlayingMovies();
        getMostPopularMovie();

//      Setting the recycler
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.topRated) {



            Toast.makeText(this, "Top Rated Movies", Toast.LENGTH_SHORT).show();
            getTopRatedMovie();
            setTitle("Top Rated Movies");
        }

        if (id == R.id.mostPopular) {
            Toast.makeText(this, "Most Popular Movies", Toast.LENGTH_SHORT).show();
            getMostPopularMovie();
            setTitle("Most Popular Movies");
        }

        if (id == R.id.upComing) {
            Toast.makeText(this, "Up Coming Movies", Toast.LENGTH_SHORT).show();
            getUpcomingMovies();
            setTitle("Upcoming Movies");
        }

        if (id == R.id.nowPlaying) {
            Toast.makeText(this, "Now Playing Movies", Toast.LENGTH_SHORT).show();
            getNowPlayingMovies();
            setTitle("Now Playing Movies");
        }


        return super.onOptionsItemSelected(item);
    }

    // get top rated movies
    public void getTopRatedMovie() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
        ((Call) call).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                if (response.isSuccessful() && response.body().getResults().size() > 0) {
                    List<Movie> movies = response.body().getResults();

                    MoviesAdapter adapter = new MoviesAdapter(movies, R.layout.movies_layout, getApplicationContext());

                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                Toast.makeText(MainActivity.this, "Error occured chech your internet connection", Toast.LENGTH_SHORT).show();

            }
        });


    }

//   Get most popular movies

    public void getMostPopularMovie() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getMostPopularMovies(API_KEY);
        ((Call) call).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                if (response.isSuccessful() && response.body().getResults().size() > 0) {
                    List<Movie> movies = response.body().getResults();


                    MoviesAdapter adapter = new MoviesAdapter(movies, R.layout.movies_layout, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                Toast.makeText(MainActivity.this, "Error occurred check your internet connection", Toast.LENGTH_SHORT).show();

            }
        });


    }

    // Get upcoming movies
    public void getUpcomingMovies() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getUpcomingMovies(API_KEY);
        ((Call) call).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                if (response.isSuccessful() && response.body().getResults().size() > 0) {
                    List<Movie> movies = response.body().getResults();


                    MoviesAdapter adapter = new MoviesAdapter(movies, R.layout.movies_layout, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                Toast.makeText(MainActivity.this, "Error occured chech your internet connection", Toast.LENGTH_SHORT).show();

            }
        });


    }

    //  Get now playing movies
    public void getNowPlayingMovies() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getNowPlayingMovies(API_KEY);
        ((Call) call).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                if (response.isSuccessful() && response.body().getResults().size() > 0) {
                    List<Movie> movies = response.body().getResults();


                    MoviesAdapter adapter = new MoviesAdapter(movies, R.layout.movies_layout, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                Toast.makeText(MainActivity.this, "Error occured chech your internet connection", Toast.LENGTH_SHORT).show();

            }
        });

    }
}