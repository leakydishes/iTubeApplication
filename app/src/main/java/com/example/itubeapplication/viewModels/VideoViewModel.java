package com.example.itubeapplication.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.itubeapplication.video.Video;

import java.util.List;

public class VideoViewModel extends AndroidViewModel {
    // variables
    VideoRepository videoRepository;
    LiveData<List<Video>> videoList;

    // constructor
    public VideoViewModel(@NonNull Application application) {
        super(application);
        videoRepository = new VideoRepository(application);
        videoList = videoRepository.getVideoList();
    }

    // methods
    public LiveData<List<Video>> getUsers() {
        return videoList;
    }
    public void update(Video video){
        videoRepository.update(video);
    }
    public void insert(Video video){
        videoRepository.insert(video);
    }
}