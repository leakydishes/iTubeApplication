package com.example.itubeapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import com.example.itubeapplication.R;
import com.example.itubeapplication.video.Video;
import com.example.itubeapplication.viewModels.VideoAdapter;
import com.example.itubeapplication.viewModels.VideoViewModel;

public class PlaylistActivity extends AppCompatActivity {
    // variables
    VideoAdapter videoAdapter;
    RecyclerView recyclerView;
    VideoViewModel videoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        // set recycler view
        recyclerView = findViewById(R.id.recyclerview);

        // get data
        getData();
    }

    private void getData() {
        // set view model
        videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);

        // set adapter
        videoAdapter = new VideoAdapter(new VideoAdapter.videoDiff(),
                this, videoViewModel);

        recyclerView.setAdapter(videoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // android lifecycle
    protected void onResume() {
        super.onResume();
        videoViewModel.getUsers().observe(this, links -> {
            videoAdapter.submitList(links); //update stored links
        });
    }
}