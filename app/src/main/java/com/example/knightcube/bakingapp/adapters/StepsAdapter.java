package com.example.knightcube.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.knightcube.bakingapp.R;
import com.example.knightcube.bakingapp.models.Step;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rajat Kumar Gupta on 27/06/2018.
 */
public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder>{

    private List<Step> stepList;
    private Context context;
    public StepsAdapter(List<Step> stepsList, Context context){
        this.stepList = stepsList;
        this.context = context;
    }

    @NonNull
    @Override
    public StepsAdapter.StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_recipes,parent,false);
        return new StepsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsAdapter.StepsViewHolder holder, int position) {
        Log.i("TAG", "onBindViewHolder: "+stepList.get(position).getDescription());
        holder.stepLongDescriptionTxt.setText(stepList.get(position).getDescription());
        if(stepList.get(position).getThumbnailURL().length()!=0)
            Picasso.get().load(stepList.get(position).getThumbnailURL()).into(holder.stepThumbnailImg);
        else
            holder.stepThumbnailImg.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder{
        private TextView stepShortDescriptionTxt;
        private TextView stepLongDescriptionTxt;
        private ImageView stepThumbnailImg;
        public StepsViewHolder(View itemView) {
            super(itemView);
//            stepShortDescriptionTxt = itemView.findViewById(R.id.);
            stepLongDescriptionTxt = itemView.findViewById(R.id.recipe_name_txt);
            stepThumbnailImg = itemView.findViewById(R.id.recipe_img);

        }
    }
}
