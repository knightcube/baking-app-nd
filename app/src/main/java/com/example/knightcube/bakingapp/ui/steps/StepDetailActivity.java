package com.example.knightcube.bakingapp.ui.steps;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.example.knightcube.bakingapp.R;
import com.example.knightcube.bakingapp.models.Step;
import com.example.knightcube.bakingapp.ui.steps.mvp.StepListActivity;

import butterknife.BindView;

public class StepDetailActivity extends AppCompatActivity {

    private static final String TAG = "StepDetail" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Step selectedStep = getIntent().getExtras().getParcelable(StepDetailFragment.ARG_ITEM_ID);
        Log.i(TAG, "onCreate: "+selectedStep.getDescription());

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(StepDetailFragment.ARG_ITEM_ID,
                    getIntent().getExtras().getParcelable(StepDetailFragment.ARG_ITEM_ID));
            StepDetailFragment fragment = new StepDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.step_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
