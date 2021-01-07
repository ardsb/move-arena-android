package com.example.demorestful.adapter;


import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.demorestful.R;
import com.example.demorestful.model.Movie;
import com.example.demorestful.view.MoviesActivity;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private int rowLayout;
    private Context context;
    public final String IMAGE_URL_BASE_PATH = "https://image.tmdb.org/t/p/w342/";

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
//        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;


        ImageView poster;


        public MovieViewHolder(View v) {
            super(v);
//            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);

            movieTitle = v.findViewById(R.id.txtmovieName);
            data =  v.findViewById(R.id.txtMovieReleaseDate);
            movieDescription =  v.findViewById(R.id.txtMovieDescription);
            rating =  v.findViewById(R.id.txtMovieRating);
            poster= v.findViewById(R.id.imageView);


        }
    }

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {


        String item = movies.get(position).getTitle();
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.movieTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, MoviesActivity.class);

//              get the item
                Movie salesItem = movies.get(position);

//              adding into the intent
                intent.putExtra("Selected Item",salesItem);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, MoviesActivity.class);

//              get the item
                Movie salesItem = movies.get(position);

//              adding into the intent
                intent.putExtra("Selected Item",salesItem);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.movieDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(context, MoviesActivity.class);

//              get the item
                Movie salesItem = movies.get(position);

//              adding into the intent
                intent.putExtra("Selected Item",salesItem);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);




            }
        });
        holder.rating.setText(movies.get(position).getVoteAverage().toString());


        String image_url = IMAGE_URL_BASE_PATH + movies.get(position).getPosterPath();
        Picasso.get().load(image_url).into(holder.poster);
        holder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MoviesActivity.class);

//              get the item
                Movie salesItem = movies.get(position);

//              adding into the intent
                intent.putExtra("Selected Item",salesItem);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}