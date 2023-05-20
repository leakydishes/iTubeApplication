package com.example.itubeapplication.viewModels;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.itubeapplication.video.Video;
import com.example.itubeapplication.video.VideoDAO;

import java.util.List;

public class VideoRepository {
    // variables
    LiveData<List<Video>> videoList;
    public LiveData<List<Video>> getVideoList(){ return videoList;}
    VideoDAO videoDAO;

    // constructor
    public VideoRepository(Application application) {
        VideoDatabase db = VideoDatabase.getDatabase(application);
        videoDAO = db.videoDAO();
        videoList = videoDAO.getVideoList();
    }

    // methods
    public void update(Video video)
    {
        VideoDatabase.databaseWriteExecutor.execute(()->{
            videoDAO.update(video);
        });
    }

    public void insert(Video video)
    {
        VideoDatabase.databaseWriteExecutor.execute(()->{
            videoDAO.insert(video);
        });
    }
}