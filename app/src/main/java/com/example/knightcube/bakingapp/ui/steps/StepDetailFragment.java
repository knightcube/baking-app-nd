package com.example.knightcube.bakingapp.ui.steps;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class StepDetailFragment extends Fragment  {

    public static final String ARG_ITEM_ID = "item_id";

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

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            step = getArguments().getParcelable(ARG_ITEM_ID);
            stepDetailTxt.setText(step.getDescription());
//            Activity activity = this.getActivity();
//            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
//            if (appBarLayout != null) {
//                appBarLayout.setTitle(step.getShortDescription());
//            }
        }

         player = ExoPlayerFactory.newSimpleInstance(getContext(),new DefaultTrackSelector());
         playerView= rootView.findViewById(R.id.player_view);
        // Make sure to use the correct VideoView import
//        videoView = rootView.findViewById(R.id.player_view);
//        videoView.setOnPreparedListener(this);
//
//        //For now we just picked an arbitrary item to play
//        videoView.setVideoURI(Uri.parse("https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9a6_2-mix-sugar-crackers-creampie/2-mix-sugar-crackers-creampie.mp4"));
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(step.getVideoURL().length()>0) {
            playerView.setVisibility(View.VISIBLE);
            player = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector());
            playerView.setPlayer(player);
            DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), "exo-demo"));
            ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(step.getVideoURL()));
            player.prepare(mediaSource);
            player.setPlayWhenReady(true);
        }else{
            playerView.setVisibility(View.GONE);
            stepDetailTxt.append("Video tutorial for this step will be uploaded soon. Check again after you are done with the cooking and the eating part as well :) .");
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        playerView.setPlayer(null);
        player.release();
    }

//    @Override
//    public void onPrepared() {
//        videoView.start();
//    }
}
