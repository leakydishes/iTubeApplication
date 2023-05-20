package com.example.itubeapplication.viewModels;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.itubeapplication.video.Video;
import com.example.itubeapplication.video.VideoDAO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Video.class}, version = 1,exportSchema = false)
public abstract class VideoDatabase extends RoomDatabase {

    // variables
    public abstract VideoDAO videoDAO();
    private static volatile VideoDatabase INSTANCE;
    static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static VideoDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (VideoDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    VideoDatabase.class, "video_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    public static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                VideoDAO dao = INSTANCE.videoDAO();
                dao.getVideoList();
            });
        }
    };
}