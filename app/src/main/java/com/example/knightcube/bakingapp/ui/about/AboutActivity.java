package com.example.knightcube.bakingapp.ui.about;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.example.knightcube.bakingapp.R;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.about_me_container)
    FrameLayout aboutMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        AboutView view = AboutBuilder.with(this)
                .setPhoto(R.drawable.profile_1)
                .setCover(R.drawable.profile_2)
                .setName("Rajat Kumar Gupta")
                .setSubTitle("Android Developer")
                .setBrief("This app has been developed as a part of the Udacity Android Developer Nanodegree project. Connect with me on Twitter and LinkedIn.")
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addGitHubLink("https://github.com/knightcube")
                .addLinkedInLink("https://www.linkedin.com/in/rajatkumargupta2209/")
                .addEmailLink("rajatcube@gmail.com")
                .addTwitterLink("knightcube")
                .addShareAction(R.string.app_name)
                .addGooglePlayStoreLink("7302200831125595425")
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();
        aboutMe.addView(view);

    }

}
