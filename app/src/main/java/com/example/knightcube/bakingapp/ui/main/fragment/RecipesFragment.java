package com.example.knightcube.bakingapp.ui.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.knightcube.bakingapp.R;
import com.example.knightcube.bakingapp.adapters.RecipeAdapter;
import com.example.knightcube.bakingapp.models.Recipe;
import com.example.knightcube.bakingapp.ui.main.activity.MainActivity;
import com.example.knightcube.bakingapp.ui.main.activity.MainViewInterface;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipesFragment extends Fragment{


    private static final String TAG = "RecipesFragment" ;

    public RecipesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipes, container, false);

        return rootView;
    }

}
