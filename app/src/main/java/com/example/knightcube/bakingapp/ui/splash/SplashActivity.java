package com.example.knightcube.bakingapp.ui.splash;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.knightcube.bakingapp.R;
import com.example.knightcube.bakingapp.ui.main.MainActivity;
import com.example.knightcube.bakingapp.utils.ConnectionUtils;
import com.tomer.fadingtextview.FadingTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.fading_text_view)
    FadingTextView appSubtitle;
    @BindView(R.id.open_main_activity_btn)
    Button launchBtn;
    @BindView(R.id.splash_layout)
    LinearLayout splashLayout;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        String[] texts = {"If you can smellll!!","what the app...","is..","cooking!"};
        appSubtitle.setTexts(texts);
        if(!ConnectionUtils.isNetworkConnected(this)) {
            launchBtn.setEnabled(false);
            launchBtn.setText("You are offline");
            launchBtn.setBackgroundColor(Color.GRAY);
            Snackbar.make(splashLayout, "No internet connection", Snackbar.LENGTH_LONG).show();
            Toast.makeText(this, "Connect to internet and restart the app", Toast.LENGTH_LONG).show();
        }
        else {
            launchBtn.setEnabled(true);
            launchBtn.setText("Tap To Continue");
            launchBtn.setBackgroundColor(getColor(R.color.colorAccent));
        }
        launchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

}
