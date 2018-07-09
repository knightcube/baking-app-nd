package com.example.knightcube.bakingapp.ui.steps;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devbrackets.android.exomedia.ui.widget.VideoView;
import com.example.knightcube.bakingapp.R;
import com.example.knightcube.bakingapp.models.Step;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class StepDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    private static final String TAG = "StepDetail";

    private Step step;
    private PlayerView playerView;
    private TextView stepDetailTxt;
    private SimpleExoPlayer player;

    public StepDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.step_detail, container, false);
        stepDetailTxt = rootView.findViewById(R.id.step_detail);
        stepDetailTxt.setText("Step Instructions here");
        playerView = rootView.findViewById(R.id.player_view);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            step = getArguments().getParcelable(ARG_ITEM_ID);
            stepDetailTxt.setText(step.getDescription());
        }


        if (step.getVideoURL().length() > 0) {
            playerView.setVisibility(View.VISIBLE);
            player = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector());
            playerView.setPlayer(player);
            DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), "exo-demo"));
            ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(step.getVideoURL()));
            player.prepare(mediaSource);
            player.setPlayWhenReady(true);

        } else {
            playerView.setVisibility(View.GONE);
            stepDetailTxt.append("Video tutorial for this step will be uploaded soon. Check again after you are done with the cooking and the eating part as well :) .");
        }

        if (savedInstanceState != null && player != null) {
            player.seekTo(savedInstanceState.getLong("current_position"));
            player.setPlayWhenReady(savedInstanceState.getBoolean("play_state"));
        }
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (player != null) {
            outState.putLong("current_position", player.getCurrentPosition());
            outState.putBoolean("play_state", player.getPlayWhenReady());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop:called ");
        playerView.setPlayer(null);
        if (player != null)
            player.release();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy:called ");
        playerView.setPlayer(null);

    }

}
