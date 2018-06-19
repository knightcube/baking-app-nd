package com.example.knightcube.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.knightcube.bakingapp.R;
import com.example.knightcube.bakingapp.models.Recipe;

import java.util.List;

/**
 * Created by Rajat Kumar Gupta on 19/06/2018.
 */
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipeList;
    private Context context;
    public RecipeAdapter(List<Recipe> recipeList, Context context){
        this.recipeList = recipeList;
        this.context = context;
    }


    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_recipes,parent,false);
        return new RecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {

        holder.recipeNameTxt.setText(recipeList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder{
        private TextView recipeNameTxt;
        public RecipeViewHolder(View itemView) {
            super(itemView);
            recipeNameTxt = itemView.findViewById(R.id.recipe_name_txt);
        }
    }
}
