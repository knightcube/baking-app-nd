package com.example.knightcube.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private final ListItemClickListener listItemClickListener;
    public RecipeAdapter(List<Recipe> recipeList, Context context, ListItemClickListener listItemClickListener){
        this.recipeList = recipeList;
        this.context = context;
        this.listItemClickListener = listItemClickListener;
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

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView recipeNameTxt;
        public RecipeViewHolder(View itemView) {
            super(itemView);
            recipeNameTxt = itemView.findViewById(R.id.recipe_name_txt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPostion = getAdapterPosition();
            listItemClickListener.onListItemClick(clickedPostion,recipeList);
        }
    }

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex,List<Recipe> recipeList);
    }

}
