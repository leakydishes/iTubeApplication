package com.example.itubeapplication.video;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.itubeapplication.user.User;

import java.util.List;

@Dao
public interface VideoDAO {
    // variables
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Video video);

    @Update
    void update(Video video);

    @Query("SELECT * FROM video_table ORDER BY name ASC")
    LiveData<List<Video>> getVideoList();
}
