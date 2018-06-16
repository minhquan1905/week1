package com.example.minhquan.flicks;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder> {

    private List<Movie> data;
    private Context context;
    private IClickListener         listener;

    /**
     * Constructs a new MovieRecyclerAdapter with a context
     *
     * @param ctx
     */
    public MovieRecyclerAdapter(Context ctx) {
        this.context = ctx;
    }

    /**
     * Replace movieList in the adapter
     *
     * @param data
     */
    public void setData(List<Movie> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    /**
     * Sets the click listener
     * @param listener
     */
    public void setListener(IClickListener listener) {
        this.listener = listener;
    }

    /**
     * Clear all movieList from adapter
     */
    public void clearData() {
        data.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_movies_row_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = data.get(position);

        holder.title.setText(movie.getTitle());
        holder.overview.setText(movie.getOverview());

        //Debug only
        //Picasso.with(context).setIndicatorsEnabled(true);
        //Picasso.with(context).setLoggingEnabled(true);
        if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            Glide.with(context).load(movie.getPoster())
                    .into(holder.poster);
        }
        else {
            Glide.with(context).load(movie.getBackdrop())
                    .into(holder.poster);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * Interface for click listeners for this adapter
     */
    public interface IClickListener {
        void onItemClick(Movie movie);
    }

    /**
     * List row members
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView title_original;
        public TextView overview;
        public ImageView poster;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title_details);
            overview = itemView.findViewById(R.id.movie_overview);
            poster = itemView.findViewById(R.id.poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(data.get(getAdapterPosition()));
        }
    }
}