package com.example.itubeapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itubeapplication.R;
import com.example.itubeapplication.video.Video;
import com.example.itubeapplication.viewModels.VideoViewModel;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    // variables
    Button btn_my_playlist, btn_add_to_playlist, btn_play;
    EditText url_input;
    VideoViewModel videoViewModel;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_my_playlist = findViewById(R.id.btn_my_playlist);
        btn_add_to_playlist = findViewById(R.id.btn_add_to_playlist);
        btn_play = findViewById(R.id.btn_play);

        url_input = findViewById(R.id.url_input);

        Intent intent = getIntent();
        userId = intent.getIntExtra("User", -1);

        String url = intent.getStringExtra("url");
        url_input.setText(url);

        videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);

        // start video
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = url_input.getText().toString();

                if (!url.isEmpty()) {
                    //String newUrl = url.substring(32, 43);
                    Intent i = new Intent(HomeActivity.this, PlayVideoActivity.class);
                    i.putExtra("url", url);
                    startActivity(i);
                } else {
                    Toast.makeText(HomeActivity.this, "Please enter a url first!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // add to playlist
        btn_add_to_playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = url_input.getText().toString();

                if (url == null || url.trim().length() == 0) {
                    Toast.makeText(HomeActivity.this, "Enter valid URL to add", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Toast.makeText(HomeActivity.this, "Video Added to Video List", Toast.LENGTH_LONG).show();
                    Video video = new Video(0, url_input.getText().toString());
                    videoViewModel.insert(video);
                }
            }
        });

        // start playlist
        btn_my_playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PlaylistActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
                finish();
            }
        });
    }
}