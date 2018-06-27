package com.example.knightcube.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.knightcube.bakingapp.R;
import com.example.knightcube.bakingapp.models.Recipe;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

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
    public void onBindViewHolder(@NonNull final RecipeViewHolder holder, int position) {

        holder.recipeNameTxt.setText(recipeList.get(position).getName());
        holder.recipeNameTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.expandableLayout.toggle();
            }
        });
        Picasso.get().load("https://best-wallpaper.net/wallpaper/2560x1600/1604/Colorful-cream-cakes-pastries-sweet-food_2560x1600.jpg").into(holder.recipeImageView);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView recipeNameTxt;
        private ImageView recipeImageView;
        private ExpandableLayout expandableLayout;
        public RecipeViewHolder(View itemView) {
            super(itemView);
            recipeNameTxt = itemView.findViewById(R.id.recipe_name_txt);
            recipeImageView = itemView.findViewById(R.id.recipe_img);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
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
