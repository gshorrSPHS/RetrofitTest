package com.mistershorr.retrofittest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by gshorr on 1/17/18.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {
    private Context context;
    private List<Recipe> recipes;
    private RecyclerViewClickListener recyclerViewClickListener;


    public RecipeAdapter(Context context, List<Recipe> recipes, RecyclerViewClickListener listener) {
        this.context = context;
        this.recipes = recipes;
        recyclerViewClickListener = listener;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    //creates the ViewHolder by inflating the CardView layout and returning it
    @Override
    public RecipeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_recipe, parent, false);

        return new MyViewHolder(itemView, recyclerViewClickListener);
    }

    //Assigns the appropriate information from the recipe object to each widget in the CardView
    //the holder is created as an inner class
    @Override
    public void onBindViewHolder(RecipeAdapter.MyViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.title.setText(recipe.getTitle());
        holder.ingredients.setText(recipe.getIngredients());


        Picasso.with(context).load(recipe.getThumbnail()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        private TextView title, ingredients;
        private ImageView thumbnail;

        private RecyclerViewClickListener recyclerViewClickListener;

        //wire the items in the CardView to instance variables
        public MyViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            title = itemView.findViewById(R.id.recipeTitle);
            ingredients = itemView.findViewById(R.id.ingredients);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            thumbnail.setOnClickListener(this);
            title.setOnClickListener(this);
            recyclerViewClickListener = listener;
            //itemView.setOnClickListener(this); //can go on any of the individual items instead.
        }


        @Override
        public void onClick(View view) {
            recyclerViewClickListener.onClick(view, getAdapterPosition());
        }
    }
}
