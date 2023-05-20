package com.example.itubeapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.itubeapplication.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class PlayVideoActivity extends AppCompatActivity {

    // variables
    String url;
    Button btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        // set to screen
        btn_home = findViewById(R.id.btn_home);

        // get video intent from previous activity
        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        // set youtube video
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = url;
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        // click button return home
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to sign up/ register page
                Intent intent = new Intent(PlayVideoActivity.this,
                        HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}