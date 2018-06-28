package com.example.knightcube.bakingapp.ui.detail.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.knightcube.bakingapp.R;
import com.example.knightcube.bakingapp.adapters.StepsAdapter;
import com.example.knightcube.bakingapp.models.Recipe;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeStepsFragment extends Fragment{

    private RecyclerView stepsRecyclerView;
    private Button nextStepBtn;
    private Button prevsStepBtn;
    private int count;
    public RecipeStepsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_steps, container, false);
        ButterKnife.bind(getActivity());
        Bundle data = getActivity().getIntent().getExtras();
        final Recipe recipe = (Recipe) data.getParcelable("recipe");
        StepsAdapter stepsAdapter = new StepsAdapter(recipe.getSteps(),getContext());
        final DiscreteScrollView scrollView = rootView.findViewById(R.id.steps_tracker);
        scrollView.setAdapter(new StepsAdapter(recipe.getSteps(),getContext()));
//        stepsRecyclerView = rootView.findViewById(R.id.steps_rv);
        count =0;
//        prevsStepBtn = rootView.findViewById(R.id.prevs_step_btn);
//        nextStepBtn = rootView.findViewById(R.id.next_step_btn);
//        nextStepBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                count++;
//                scrollView.smoothScrollToPosition(count);
//                if(count>recipe.getSteps().size()-1){
//                    count=0;
//                }
//            }
//        });
//        prevsStepBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                count--;
//                scrollView.smoothScrollToPosition(count);
//                if(count<0){
//                    count = recipe.getSteps().size()-1;
//                }
//            }
//        });
//


//        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
//        stepsRecyclerView.setHasFixedSize(true);
//        stepsRecyclerView.setAdapter(stepsAdapter);
        return rootView;
    }

}
