package com.mistershorr.retrofittest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManger;
    private RecipeAdapter adapter;
    private List<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipes = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManger = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManger);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(view.getId() == R.id.recipeTitle) {
                    Toast.makeText(MainActivity.this, "You clicked on the title!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, recipes.get(position).toString(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        adapter = new RecipeAdapter(MainActivity.this, recipes, listener);
        recyclerView.setAdapter(adapter);


        //JSON request from server
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FoodApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FoodApi api = retrofit.create(FoodApi.class);

        Call<RecipeResponse> call = api.getRecipes("lettuce", "");

        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                RecipeResponse recipeResponse = response.body();
                Log.d(TAG, "onResponse: " + response.body().getResults().toString());
                recipes = recipeResponse.getResults();
                if(recipes.size() == 0) {
                    Toast.makeText(MainActivity.this, "No matches found!", Toast.LENGTH_SHORT).show();
                    //add in some fake recipes if it finds none for testing purposes
                    recipes.add(new Recipe("test1", "test description1", "https://i.imgur.com/62Pmk5i.png"));
                    recipes.add(new Recipe("test2", "test description1", "https://i.imgur.com/62Pmk5i.png"));
                    recipes.add(new Recipe("test3", "test description1", "https://i.imgur.com/62Pmk5i.png"));
                }

                for(Recipe r : recipes) {
                    Log.d(TAG, r.toString());
                }

                adapter.setRecipes(recipes);
                adapter.notifyDataSetChanged();
                //ListView listView = (ListView) findViewById(R.id.listView);
                //listView.setAdapter(new ArrayAdapter<Recipe>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,recipes));
              }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
